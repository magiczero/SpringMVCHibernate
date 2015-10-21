package com.cngc.pm.service;

import com.cngc.pm.model.Attachment;

public interface AttachService {

	Attachment create(Attachment attach);

	Attachment get(long id);

}
