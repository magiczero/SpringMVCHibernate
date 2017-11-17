package com.cngc.pm.service.computer;

import java.util.List;

import com.cngc.pm.model.computer.UsbAlarm;

public interface UsbAlarmService {
	void save(UsbAlarm usb);
	List<UsbAlarm> getAll();
	boolean delById(Long id);
}
