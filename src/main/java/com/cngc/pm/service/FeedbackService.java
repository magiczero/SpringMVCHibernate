package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Feedback;

public interface FeedbackService {

	void save(Feedback feedback);

	List<Feedback> getNotFinished();
}
