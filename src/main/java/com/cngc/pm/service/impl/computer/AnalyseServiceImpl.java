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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.computer.ComputerDAO;
import com.cngc.pm.dao.computer.ComputerTaskDAO;
import com.cngc.pm.dao.computer.InspectionDataDAO;
import com.cngc.pm.dao.computer.InspectionTaskDAO;
import com.cngc.pm.dao.opr.ComplianceRuleDAO;
import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.ComputerTask;
import com.cngc.pm.model.computer.InspectionData;
import com.cngc.pm.model.computer.InspectionTask;
import com.cngc.pm.model.opr.ComplianceRule;
import com.cngc.pm.service.computer.AnalyseService;

import static com.cngc.utils.Common._fileUploadPath;

@Service
public class AnalyseServiceImpl implements AnalyseService {

	@Autowired
	private InspectionTaskDAO taskDao;
	@Autowired
	private ComputerTaskDAO computerTaskDao;
	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private InspectionDataDAO inspectionDataDao;
	@Autowired
	private ComplianceRuleDAO ruleDao;
	
	/*
	 * 解析检查结果数据
	 * @see com.cngc.pm.service.computer.AnalyseService#analyse(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public void analyse(Long computerId, Long taskId) {
		// TODO 自动生成的方法存根
		Computer computer = computerDao.find(computerId);
		InspectionTask task = taskDao.find(taskId);
		
		if(computer==null||task==null)
			return;
		
		ComputerTask t = computerTaskDao.getByComputerAndTask(computer, task);
		if(t==null)
			return;
		
		String file = t.getFile();
		//解压文件
		String outfile = DecompressFile(file);
		//解析文件
		parseFile(outfile,t.getId());
		//统计
		dataStat(t.getId());
		//删除文件
//		File ffile = new File(outfile);
//		if(ffile.exists())
//			ffile.delete();
		//设置状态
		t.setStatus("04");
		computerTaskDao.save(t);
	}

	/*
	 * 文件解压缩
	 * @see com.cngc.pm.service.computer.AnalyseService#DecompressFile(java.lang.String)
	 */
	@Override
	public String DecompressFile(String file) {
		// TODO 自动生成的方法存根
		String outfilename = null;
		try {  
            ZipInputStream Zin=new ZipInputStream(new FileInputStream(_fileUploadPath + File.pathSeparator + file));
            BufferedInputStream Bin=new BufferedInputStream(Zin);    
            File Fout=null;  
            ZipEntry entry;  
            
            try {  
                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
                	if(!entry.getName().equals("1.chk"))
                		continue;
                	outfilename = _fileUploadPath + File.pathSeparator + file.substring(0, file.indexOf('.'))+".chk";
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
	/*
	 * 数据解析
	 * @see com.cngc.pm.service.computer.AnalyseService#parseFile(java.lang.String, java.lang.Long)
	 */
	@Override
	public void parseFile(String filename,Long computerTaskId) {
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
            	case "BEGIN COMPUTERINFO": getData(reader,computerTaskId,"COMPUTERINFO");	break;
            	case "BEGIN DISK": getData(reader,computerTaskId,"DISK");					break;
            	case "BEGIN NETINFO": getData(reader,computerTaskId,"NETINFO");				break;
            	case "BEGIN DISKPARTITIONINFO": getData(reader,computerTaskId,"DISKPARTITIONINFO");
            		saveStatDisk(computerTaskId);break;
            	case "BEGIN OSINFO": getData(reader,computerTaskId,"OSINFO");				break;
            	case "BEGIN USERINFO": getData(reader,computerTaskId,"USERINFO");			
            		saveStatSreensave(computerTaskId); break;
            	case "BEGIN DEVICEINFO": getData(reader,computerTaskId,"DEVICEINFO");		
            		saveStatVideo(computerTaskId); break;
            	case "BEGIN USBSTORAGE": getData(reader,computerTaskId,"USBSTORAGE");		
            		saveStatUSBStorage(computerTaskId); break;
            	case "BEGIN USBDEVICE": getData(reader,computerTaskId,"USBDEVICE");			
            		saveStatUSBDevice(computerTaskId); break;
            	case "BEGIN IEHISTORY": getData(reader,computerTaskId,"IEHISTORY");			break;
            	case "BEGIN BROWSERHISTORY": getData(reader,computerTaskId,"BROWSERHISTORY");	break;
            	case "BEGIN WEBMAIL": getData(reader,computerTaskId,"WEBMAIL");					break;
            	case "BEGIN IECOOKIE": getData(reader,computerTaskId,"IECOOKIE");				break;
            	case "BEGIN IECACHE": getData(reader,computerTaskId,"IECACHE");					break;
            	case "BEGIN INTERNET": getData(reader,computerTaskId,"INTERNET");				
            		saveStatInternet(computerTaskId); break;
            	case "BEGIN INSTALLEDSOFTWARE": getData(reader,computerTaskId,"INSTALLEDSOFTWARE");	
            		saveStatShy(computerTaskId);saveStatAntivirus(computerTaskId);saveStatInstalledSoftware(computerTaskId);break;
            	case "BEGIN FILEVHD": getData(reader,computerTaskId,"FILEVHD");					break;
            	case "BEGIN EXPLORERRECENT": getData(reader,computerTaskId,"EXPLORERRECENT");	break;
            	case "BEGIN POWERON": getData(reader,computerTaskId,"POWERON");					break;
            	case "BEGIN NETSHAREINFO": getData(reader,computerTaskId,"NETSHAREINFO");		
            		saveStatNetshare(computerTaskId); break;
            	case "BEGIN WIRELESS": getData(reader,computerTaskId,"WIRELESS");				
            		saveStatWireless(computerTaskId); break;
            	case "BEGIN SYSTEMPATCH": getData(reader,computerTaskId,"SYSTEMPATCH");			break;
            	case "BEGIN PROCESSINFO": getData(reader,computerTaskId,"PROCESSINFO");			break;
            	case "BEGIN ACCOUNT": getData(reader,computerTaskId,"ACCOUNT");					break;
            	case "BEGIN POLICYINFO": getData(reader,computerTaskId,"POLICYINFO");			break;
            	case "BEGIN AUDIT": getData(reader,computerTaskId,"AUDIT");						break;
            	case "BEGIN PRIVILEGE": getData(reader,computerTaskId,"PRIVILEGE");				break;
            	case "BEGIN SERVICEINFO": getData(reader,computerTaskId,"SERVICEINFO");			break;
            	case "BEGIN PORTSINFO": 
            		getData(reader,computerTaskId,"PORTSINFO");				
            		saveStatPorts(computerTaskId); break;
            	case "BEGIN MAILAPP": getData(reader,computerTaskId,"MAILAPP");					break;
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

	/*
	 * 分析一行数据并存入数据库
	 * @see com.cngc.pm.service.computer.AnalyseService#getData(java.io.BufferedReader, java.lang.Long, java.lang.String)
	 */
	@Override
	public void getData(BufferedReader reader, Long computerTaskId,
			String itemName) throws IOException {
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
				InspectionData data = new InspectionData();
				data.setCompliance(true);
				data.setComputerTaskId(computerTaskId);
				data.setItemName(itemName);
				data.setAuto(true);							//自动判定
				Map<String,String> parameters = new HashMap<String,String>();
				for(int i=0;i<infos.length;i++)
					parameters.put(String.valueOf(i+1), infos[i]);
				data.setData(mapper.writeValueAsString(parameters));
				//判断记录是否合规
				dataCompliance(data,parameters);
				inspectionDataDao.save(data);
			}
		}	 
		return;
	}

	/*
	 * 分析记录是否合规
	 * @see com.cngc.pm.service.computer.AnalyseService#dataCompliance(com.cngc.pm.model.computer.InspectionData, java.util.Map)
	 */
	@Override
	public void dataCompliance(InspectionData data,Map<String,String> parameters) {
		// TODO 自动生成的方法存根
		switch(data.getItemName())
		{
		case "USBDEVICE":
		case "USBSTORAGE":
		case "INSTALLEDSOFTWARE":	//软件安装
		case "PORTSINFO":			//端口
			data.setCompliance(false);
		case "DISKPARTITIONINFO": 	//隐藏分区
		case "INTERNET":		  	//终端互联
		case "DEVICEINFO":			//硬件
		case "NETSHAREINFO":		//系统共享
		case "USERINFO":			//账户信息
			dataComplianceDefault(data,parameters);
			break;
		case "WIRELESS":			//无线
			data.setCompliance(false);
			break;
		//case "PORTSINFO":			//端口
		//	dataCompliancePorts(data,parameters);
		//	break;
		}
	}

	/*
	 * 根据规则判断记录是否合规（默认方式：关键字匹配）
	 * @see com.cngc.pm.service.computer.AnalyseService#dataComplianceDefault(com.cngc.pm.model.computer.InspectionData, java.util.Map)
	 */
	@Override
	public void dataComplianceDefault(InspectionData data,
			Map<String, String> parameters) {
		// TODO 自动生成的方法存根
		List<ComplianceRule> rules = ruleDao.getRuleByItem(data.getItemName());
		for(int i=0;i<rules.size();i++)
		{
			String s = rules.get(i).getValue();
			String s1 = parameters.get( Integer.toString(rules.get(i).getIndexOfData()) );
			String[] keys = s.split(";");
			for(int nindex=0;nindex<keys.length;nindex++)
			{	
				if(rules.get(i).isEqual())
				{	
					//精确匹配
					if( s1.equals(keys[nindex]) )
					{
						data.setMark(rules.get(i).getMark());
						data.setCompliance(rules.get(i).isCompliance());
						break;
					}
				}
				else
				{
					//模糊匹配
					if( s1.indexOf(keys[nindex])>=0 )
					{
						data.setMark(rules.get(i).getMark());
						data.setCompliance(rules.get(i).isCompliance());
						break;
					}
				}
				//合规即返回
				if(data.isCompliance())
					break;
			}
		}
	}
	/*
	 * 根据规则判定端口信息是否合规（主要涉及端口的获取方式不同于默认方式）
	 * @see com.cngc.pm.service.computer.AnalyseService#dataCompliancePorts(com.cngc.pm.model.computer.InspectionData, java.util.Map)
	 */
	@Override
	public void dataCompliancePorts(InspectionData data,
			Map<String, String> parameters) {
		//默认不合规
		data.setCompliance(false);
		List<ComplianceRule> rules = ruleDao.getRuleByItem(data.getItemName());
		for(int i=0;i<rules.size();i++)
		{
			String s = rules.get(i).getValue();
			String s1 = parameters.get( Integer.toString(rules.get(i).getIndexOfData()) );
			String sport = "";
			//获取端口
			if(s1.indexOf(":")>=0)
				sport = s1.substring(s1.indexOf(":")+1);
			String[] ports = s.split(";");
			for(int n=0;n<ports.length;n++)
			{
				if(sport.equals(ports[i]))
				{
					data.setMark(rules.get(i).getMark());
					data.setCompliance(rules.get(i).isCompliance());
					break;
				}
			}
		}//end for
	}//end datacompliance
	/*
	 * 数据统计
	 * @see com.cngc.pm.service.computer.AnalyseService#dataStat(java.lang.Long)
	 */
	@Override
	public void dataStat(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		boolean bcompliance = true;
		List<InspectionData> list = inspectionDataDao.getData(ids, null, false, null, true);
		if(list.size()>0)
			bcompliance = false;
		//综合判定本次结果
		ComputerTask t =computerTaskDao.find(computerTaskId);
		t.setCompliance(bcompliance);
		computerTaskDao.save(t);
		//反馈到终端
		Computer c = t.getComputer();
		c.setCompliance(bcompliance);
		c.setLastTaskId(t.getTask().getId());
		computerDao.save(c);
	}

	@Override
	public void saveStat(Long computerTaskId, String itemName,int sum) {
		// TODO 自动生成的方法存根
		InspectionData sdata = inspectionDataDao.getStatData(computerTaskId, itemName);
		if(sdata==null)
			sdata = new InspectionData();
		sdata.setComputerTaskId(computerTaskId);
		sdata.setItemName(itemName);
		sdata.setGather(true);
		if(sum==0)
			sdata.setCompliance(true);
		else
			sdata.setCompliance(false);
		sdata.setSum(sum);
		inspectionDataDao.save(sdata);
	}

	@Override
	public void saveStatWireless(Long computerTaskId) {
		// TODO 自动生成的方法存根
		//无线通信
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		List<InspectionData> ls = inspectionDataDao.getData(ids, "WIRELESS", false, null, false);
		saveStat(computerTaskId,"WIRELESS",ls.size());
	}

	@Override
	public void saveStatVideo(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//音视频
		List<InspectionData> ls = inspectionDataDao.getData(ids, "DEVICEINFO", false, "VIDEO", false);
		saveStat(computerTaskId,"VIDEO",ls.size());
	}

	@Override
	public void saveStatInternet(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//互联网
		List<InspectionData> ls = inspectionDataDao.getData(ids, "INTERNET", false, null, false);
		saveStat(computerTaskId,"INTERNET",ls.size());
	}

	@Override
	public void saveStatShy(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//三合一
		List<InspectionData> ls = inspectionDataDao.getData(ids, "INSTALLEDSOFTWARE", true, "SHY", false);
		saveStat(computerTaskId,"SHY",ls.size()>0?0:1);
	}

	@Override
	public void saveStatAntivirus(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//防病毒
		List<InspectionData> ls = inspectionDataDao.getData(ids, "INSTALLEDSOFTWARE", true, "ANTIVIRUS", false);
		saveStat(computerTaskId,"ANTIVIRUS",ls.size()>0?0:1);
	}

	@Override
	public void saveStatDisk(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//隐藏分区
		List<InspectionData> ls = inspectionDataDao.getData(ids, "DISKPARTITIONINFO", false, null, false);
		saveStat(computerTaskId,"DISKPARTITIONINFO",ls.size());
	}

	@Override
	public void saveStatNetshare(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//系统共享
		List<InspectionData> ls = inspectionDataDao.getData(ids, "NETSHAREINFO", false, null, false);
		saveStat(computerTaskId,"NETSHAREINFO",ls.size());
	}

	@Override
	public void saveStatPorts(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//端口
		List<InspectionData> ls = inspectionDataDao.getData(ids, "PORTSINFO", false, null, false);
		saveStat(computerTaskId,"PORTSINFO",ls.size());
	}

	@Override
	public void saveStatSreensave(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//屏保
		List<InspectionData> ls = inspectionDataDao.getData(ids, "USERINFO", false, "SCREENSAVE", false);
		saveStat(computerTaskId,"SCREENSAVE",ls.size());
	}

	@Override
	public List<InspectionData> getStats(List<Long> ids, String itemName,
			boolean bCompliance) {
		// TODO 自动生成的方法存根
		return inspectionDataDao.getStats(ids, itemName, bCompliance);
	}

	@Override
	public void saveStatUSBDevice(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//USB设备
		List<InspectionData> ls = inspectionDataDao.getData(ids, "USBDEVICE", false, null, false);
		saveStat(computerTaskId,"USBDEVICE",ls.size());
	}

	@Override
	public void saveStatUSBStorage(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//USB存储
		List<InspectionData> ls = inspectionDataDao.getData(ids, "USBSTORAGE", false, null, false);
		saveStat(computerTaskId,"USBSTORAGE",ls.size());
	}

	@Override
	public void saveStatInstalledSoftware(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		//软件安装
		List<InspectionData> ls = inspectionDataDao.getData(ids, "INSTALLEDSOFTWARE", false, null, false);
		saveStat(computerTaskId,"INSTALLEDSOFTWARE",ls.size());
	}
	
}
