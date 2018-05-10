package com.cngc.pm.service.impl.computer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.MessageDAO;
import com.cngc.pm.dao.computer.ComputerDAO;
import com.cngc.pm.dao.computer.UsbAlarmDAO;
import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.UsbAlarm;
import com.cngc.pm.service.computer.AnalyseUSBService;

import static com.cngc.utils.Common._fileUploadPath;

@Service
public class AnalyseUSBServiceImpl implements AnalyseUSBService{

	@Autowired
	private UsbAlarmDAO usbDao;
	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private MessageDAO messageDao;
	
//	private String storageDirectory = "/Users/andy/Documents/workspace/attachment";

	@Override
	public void analyse(String file) {
		// TODO 自动生成的方法存根
		String[] skeys = file.split("_");
		if(skeys.length!=4)
			return;
		Long computerId = Long.parseLong(skeys[1]);
		//解压文件
		String outfile = DecompressFile(file);
		//解析文件
		parseFile(outfile,computerId);
	}

	@Override
	public String DecompressFile(String file) {
		// TODO 自动生成的方法存根
		String outfilename = null;
		try {  
            ZipInputStream Zin=new ZipInputStream(new FileInputStream(_fileUploadPath + file));
            BufferedInputStream Bin=new BufferedInputStream(Zin);    
            File Fout=null;  
            ZipEntry entry;  
            
            try {  
                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
                	if(!entry.getName().equals("1.chk"))
                		continue;
                	outfilename = _fileUploadPath + file.substring(0, file.indexOf('.'))+".chk";
                    Fout=new File(outfilename);  
                    if(!Fout.exists()){  
                        (new File(Fout.getParent())).mkdirs();  
                    }  
                    FileOutputStream out=new FileOutputStream(Fout);  
                    BufferedOutputStream Bout=new BufferedOutputStream(out);  
                    int b;  
                    while((b=Bin.read())!=-1){  
                        Bout.write(b);  
                    }  
                    Bout.close();  
                    out.close();      
                }  
                Bin.close();  
                Zin.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
		return outfilename;
	}

	@Override
	public void parseFile(String filename, Long computerId) {
		// TODO 自动生成的方法存根
		File file = new File(filename);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader( new FileInputStream(file),"GB2312"));
 
            String strtemp = null;
            // 一次读入一行，直到读入null为文件结束
            while ((strtemp = reader.readLine()) != null) {
                //处理记录
            	switch(strtemp)
            	{
            	case "BEGIN USBALARM": getData(reader,computerId,"USBALARM");		
            	break;
            	default:break;
            	}
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}

	@Override
	public void getData(BufferedReader reader, Long computerId, String itemName)
			throws IOException {
		// TODO 自动生成的方法存根
		 String strtemp = null;
		 ObjectMapper mapper = new ObjectMapper();
		while ((strtemp = reader.readLine()) != null) {
			// 处理记录
			if (strtemp.indexOf("END "+itemName) != -1) 
				break;
			if(strtemp.isEmpty())
				continue;
			//拆分字段
			String[] infos = strtemp.split("\\|");
			if(infos.length>0)
			{
				UsbAlarm data = new UsbAlarm();
				Computer c = computerDao.find(computerId);
				data.setComputer(c);
				data.setItemName(itemName);
				Map<String,String> parameters = new HashMap<String,String>();
				for(int i=0;i<infos.length;i++)
					parameters.put(String.valueOf(i+1), infos[i]);
				data.setData(mapper.writeValueAsString(parameters));
				
				usbDao.save(data);
				//发送消息
				String scontent = "发现用户终端["+c.getUserName()+"]有USB插入记录.";
				messageDao.sendMessage("系统", "系统", "songle", "宋乐", scontent, "#");
			}
		}	 
		return;
	}
	
	
}
