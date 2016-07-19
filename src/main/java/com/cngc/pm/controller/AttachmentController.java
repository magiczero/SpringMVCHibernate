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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cngc.exception.BusinessException;
import com.cngc.pm.model.AttachType;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.service.AttachService;
import com.cngc.utils.Common;
import com.cngc.utils.Uploader;

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
	
	private boolean isExtension(String ext, String... exts) {
		boolean flag = false;
		for(String s : exts) {
			if(StringUtils.equalsIgnoreCase(ext, s)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@RequestMapping(value = "/upload", produces="text/html;charset=UTF-8",method = RequestMethod.POST)
	public String upload(Model model,MultipartHttpServletRequest request,
			HttpServletResponse response) {
		log.debug("上传附件……");
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf;
		String type = request.getParameter("type");

		//List<Attachment> list = new LinkedList<>();

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			log.debug("Uploading {}", mpf.getOriginalFilename());

			String newFilenameBase = UUID.randomUUID().toString(); // 重命名文件名
			String origFilename =  mpf.getOriginalFilename();
//			String originalFileExtension = mpf.getOriginalFilename().substring(
//					mpf.getOriginalFilename().lastIndexOf(".")); // 扩展名
			String originalFileExtension = StringUtils.substring(origFilename,  StringUtils.lastIndexOf(origFilename, "."));
			
			if(StringUtils.isEmpty(originalFileExtension)) {
				throw new BusinessException("非法文件");
			}
			//判断是否是指定扩展名
			if(isExtension(originalFileExtension, ".doc",".docx",".xls",".xlsx",".pdf",".ppt",".pptx",".jpg",".png",".zip",".rar")) {
				String newFilename = newFilenameBase + originalFileExtension; // 重命名
	//			String storageDirectory = request.getSession().getServletContext()
	//					.getRealPath("")
	//					+ fileUploadDirectory;
				String storageDirectory = fileUploadDirectory;
				File folder = new File(storageDirectory);
				if (!folder.exists())
					folder.mkdirs();
	
				File newFile = new File(storageDirectory + "/" + newFilename);
				try {
					mpf.transferTo(newFile);
	
					 Attachment attach = new Attachment();
					 attach.setName(origFilename);
					 //attach.setThumbnailFilename(thumbnailFilename);
					 attach.setNewFilename(newFilename);
					 attach.setContentType(mpf.getContentType());
					 attach.setSize(mpf.getSize());
					 attach.setPath(folder.getPath());
					 switch(type) {
					 	case "1":
					 		attach.setType(AttachType.doc);
					 		break;
					 	case "2" :
					 		attach.setType(AttachType.event);
					 		break;
					 	case "3" :
					 		attach.setType(AttachType.ci);
					 		break;
					 	case "4" :
					 		attach.setType(AttachType.knowledge);
					 		break;
					 	case "5":
					 		attach.setType(AttachType.secjob);
					 		break;
					 	case "6":
					 		attach.setType(AttachType.uefile);
					 	case "7":
					 		attach.setType(AttachType.feedback);
					 }
					 attach = attachService.create(attach);
					
					 //list.add(attach);
					 model.addAttribute("fileId", attach.getId()+",");
				} catch (IOException e) {
					e.printStackTrace();
					log.error("不能上传文件 " + mpf.getOriginalFilename(), e);
				}
			} else {
				throw new BusinessException("非法文件");
			}

		}

		//model.addAttribute("attachList", list);
		
		return "attachment/success";
	}
	
	@RequestMapping(value="/imageup", method = RequestMethod.POST)
	public @ResponseBody String imageUp(HttpServletRequest request) {
		Uploader up = new Uploader(request);
		
		up.setSavePath("upload");
	    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	    up.setAllowFiles(fileType);
	    up.setMaxSize(10000); //单位KB
	    try {
			up.upload();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    //String callback = request.getParameter("callback");

	    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";

	    result = result.replaceAll( "\\\\", "\\\\" );
	    
	    return result;
	}
	
	@RequestMapping(value="/picload", method = RequestMethod.POST)
	public @ResponseBody  String picLoad(MultipartHttpServletRequest request) {
		String msg = "";
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf;

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			log.debug("Uploading {}", mpf.getOriginalFilename());

			String newFilenameBase = UUID.randomUUID().toString(); // 重命名文件名
			String origFilename =  mpf.getOriginalFilename();
//			String originalFileExtension = mpf.getOriginalFilename().substring(
//					mpf.getOriginalFilename().lastIndexOf(".")); // 扩展名
			String originalFileExtension = StringUtils.substring(origFilename,  StringUtils.lastIndexOf(origFilename, "."));
			
			if(StringUtils.isEmpty(originalFileExtension)) {
				throw new BusinessException("非法文件");
			}
			//判断是否是指定扩展名
				String newFilename = newFilenameBase + originalFileExtension; // 重命名
				String storageDirectory = request.getSession().getServletContext()
						.getRealPath("")
						+ "/resources/ueuploadfiles/";
				File folder = new File(storageDirectory);
				if (!folder.exists())
					folder.mkdirs();
	
				File newFile = new File(storageDirectory + "/" + newFilename);
				try {
					mpf.transferTo(newFile);
	
					 Attachment attach = new Attachment();
					 attach.setName(origFilename);
					 attach.setNewFilename(newFilename);
					 attach.setContentType(mpf.getContentType());
					 attach.setSize(mpf.getSize());
					 attach.setPath(folder.getPath());
					 attach.setType(AttachType.uefile);
					 attach = attachService.create(attach);
					
					//msg = "{\"original\":\""+attach.getName()+"\",\"url\":\"../resources/ueuploadfiles/"+attach.getNewFilename()+"\",\"title\":\"\",\"state\":\"\"}";
					msg = "{\"name\":\""+ attach.getNewFilename() +"\", \"originalName\": \""+ attach.getName() +"\", \"size\": "+ attach.getSize() +", \"state\": \"SUCCESS\", \"type\": \""+ originalFileExtension +"\", \"url\": \"resources/ueuploadfiles/"+attach.getNewFilename()+"\"}";
					msg = msg.replaceAll( "\\\\", "\\\\" );
				} catch (IOException e) {
					e.printStackTrace();
					log.error("不能上传文件 " + mpf.getOriginalFilename(), e);
				}
				break;
		}
		return msg;
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
