package com.cngc.pm.service.impl.computer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.computer.UsbAlarmDAO;
import com.cngc.pm.model.computer.UsbAlarm;
import com.cngc.pm.service.computer.UsbAlarmService;

@Service
public class UsbAlarmServiceImpl implements UsbAlarmService{

	@Autowired
	private UsbAlarmDAO usbDao;
	
	@Override
	@Transactional
	public void save(UsbAlarm usb) {
		// TODO 自动生成的方法存根
		usbDao.save(usb);
	}

	@Override
	public List<UsbAlarm> getAll() {
		// TODO 自动生成的方法存根
		return usbDao.findAll();
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		// TODO 自动生成的方法存根
		return usbDao.removeById(id);
	}

}
