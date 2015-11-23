package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.LeaderTask;

public interface LeaderTaskService {

	void save(LeaderTask leaderTask);
	boolean delById(Long id);
	LeaderTask getById(Long id);
	List<LeaderTask> getAll();
}
