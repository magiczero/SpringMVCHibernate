package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.FeedbackDAO;
import com.cngc.pm.model.Feedback;
import com.cngc.pm.service.FeedbackService;
import com.googlecode.genericdao.search.Search;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDAO feedbackDao;

	@Override
	@Transactional
	public void save(Feedback feedback) {
		// TODO Auto-generated method stub
		feedbackDao.save(feedback);
	}

	@Override
	@Transactional
	public List<Feedback> getNotFinished() {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterNotEqual("status", "03");
		search.addSortAsc("status");
		search.addSortDesc("createTime");

		return feedbackDao.search(search);
	}

	@Override
	@Transactional(readOnly=true)
	public Feedback getById(Long id) {
		// TODO Auto-generated method stub
		return feedbackDao.find(id);
	}
}
