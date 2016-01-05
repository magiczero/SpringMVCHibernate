package com.cngc.pm.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cngc.pm.model.AttachType;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.service.AttachService;
import com.cngc.utils.Common;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

	private static final Logger log = LoggerFactory
			.getLogger(AttachmentController.class);

	@Resource
	private AttachService attachService;

	private String fileUploadDirectory = Common._fileUploadPath;

//	@RequestMapping
//	public String index() {
//		log.debug("attachment home");
//		return "attachment/index";
//	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request,
			HttpServletResponse response) {

		log.debug("上传附件……");
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf;

		// List<Attachment> list = new LinkedList<>();

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			log.debug("Uploading {}", mpf.getOriginalFilename());

			String newFilenameBase = UUID.randomUUID().toString(); // 重命名文件名
			String originalFileExtension = mpf.getOriginalFilename().substring(
					mpf.getOriginalFilename().lastIndexOf(".")); // 扩展名
			String newFilename = newFilenameBase + originalFileExtension; // 重命名
//			String storageDirectory = request.getSession().getServletContext()
//					.getRealPath("")
//					+ fileUploadDirectory;
			String storageDirectory = fileUploadDirectory;
			// String contentType = mpf.getContentType();
			File folder = new File(storageDirectory);
			if (!folder.exists())
				folder.mkdirs();

			File newFile = new File(storageDirectory + "/" + newFilename);
			try {
				mpf.transferTo(newFile);
				// String thumbnailFilename = "";
				// File thumbnailFile = null;
				// if(originalFileExtension.toLowerCase().matches("png|jpe?g|gif"))
				// { //如果是图片，则设置缩略图等
				// BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile),
				// 290);
				// thumbnailFilename = newFilenameBase + "-thumbnail.png";
				// thumbnailFile = new File(storageDirectory + "/" +
				// thumbnailFilename);
				// ImageIO.write(thumbnail, "png", thumbnailFile);
				// }

				// Attachment attach = new Attachment();
				// attach.setName(mpf.getOriginalFilename());
				// attach.setThumbnailFilename(thumbnailFilename);
				// attach.setNewFilename(newFilename);
				// attach.setContentType(contentType);
				// attach.setSize(mpf.getSize());
				// attach.setThumbnailSize(thumbnailFile==null?0:thumbnailFile.length());
				// attach = attachService.create(attach);
				//
				// attach.setUrl("/picture/"+attach.getId());
				// attach.setThumbnailUrl("/thumbnail/"+attach.getId());
				// attach.setDeleteUrl("/delete/"+attach.getId());
				// attach.setDeleteType("DELETE");
				//
				// list.add(attach);

			} catch (IOException e) {
				e.printStackTrace();
				log.error("不能上传文件 " + mpf.getOriginalFilename(), e);
			}

		}

		return "";
	}

	/**
	 * 文档类附件上传地址
	 * @param file
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "plupload", method = RequestMethod.POST)
	public String plupload(@RequestParam MultipartFile file,
			HttpServletRequest request, HttpSession session) {
		try {
			String name = request.getParameter("name");
			Integer chunk = 0, chunks = 0;
			if (null != request.getParameter("chunk")
					&& !request.getParameter("chunk").equals("")) {
				chunk = Integer.valueOf(request.getParameter("chunk"));
			}
			if (null != request.getParameter("chunks")
					&& !request.getParameter("chunks").equals("")) {
				chunks = Integer.valueOf(request.getParameter("chunks"));
			}
			log.info("chunk:[" + chunk + "] chunks:[" + chunks + "]");
			// 检查文件目录，不存在则创建
			String relativePath = fileUploadDirectory;
			//String realPath = session.getServletContext().getRealPath("");
			//File folder = new File(realPath + relativePath);
			File folder = new File(relativePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			// 目标文件
			File destFile = new File(folder, name);
			// 文件已存在删除旧文件（上传了同名的文件）
			if (chunk == 0 && destFile.exists()) {
				destFile.delete();
				destFile = new File(folder, name);
			}
			// 合成文件
			//appendFile(file.getInputStream(), destFile);
			file.transferTo(destFile);
			if (chunk == chunks - 1) {
				log.info("上传完成");
			} else {
				log.info("还剩[" + (chunks - 1 - chunk) + "]个块文件");
			}
			// 信息保存在数据库中
			Attachment attach = new Attachment();
			attach.setName(file.getOriginalFilename());
			attach.setNewFilename(name);
			attach.setContentType(file.getContentType());
			attach.setSize(file.getSize());
			attach.setPath(folder.getPath());
			attach.setType(AttachType.doc);			//如何灵活判断
			attach = attachService.create(attach); // 保存

		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return "attachment/success";
	}
	
	@RequestMapping("download/{id}")
	public void downloadFile(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		Attachment attach = attachService.get(id);
		String path = fileUploadDirectory + attach.getNewFilename();
		File file = new File(path);
		
//		String fileName = new String(attach.getName().getBytes("UTF-8"),
//				"iso-8859-1");// 为了解决中文名称乱码问题
		
		String fileName = attach.getName();
		OutputStream os = response.getOutputStream();  
	    try {  
	    	response.reset();  
	    	response.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode(fileName, "UTF-8"));  
	    	response.setContentType("application/octet-stream; charset=utf-8");  
	        os.write(FileUtils.readFileToByteArray(file));  
	        os.flush();  
	    } finally {  
	        if (os != null) {  
	            os.close();  
	        }  
	    }  
	}

	@RequestMapping("downloadFile/{id}")
	public ResponseEntity<byte[]> download(@PathVariable("id") long id)
			throws IOException {
		Attachment attach = attachService.get(id);
		String path = fileUploadDirectory + attach.getNewFilename();
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		if(file.exists()) {
			
			String fileName = new String(attach.getName().getBytes("UTF-8"),
					"iso-8859-1");// 为了解决中文名称乱码问题
			headers.setContentDispositionFormData("attachment", fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
					headers, HttpStatus.CREATED);
		}
		headers.setContentType(MediaType.TEXT_HTML);
		return new ResponseEntity<byte[]>("没有此文件".getBytes(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
