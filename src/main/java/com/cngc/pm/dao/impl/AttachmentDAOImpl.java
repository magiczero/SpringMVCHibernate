package com.cngc.pm.dao.impl;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.AttachmentDAO;
import com.cngc.pm.model.Attachment;

@Repository
public class AttachmentDAOImpl extends BaseDAOImpl<Attachment, Long> implements
		AttachmentDAO {
	private static final Logger log = LoggerFactory.getLogger(AttachmentDAOImpl.class);
	
	@Override
	public Attachment create(Attachment attach) {
		// TODO Auto-generated method stub
		log.debug("保存并创建附件");
		this.getSession().save(attach);
		return attach;
	}

	@Override
	public Set<Attachment> getSet(String... filenames) {
		// TODO Auto-generated method stub
		Set<Attachment> set = new HashSet<>();
		for(String name : filenames) {
			Attachment attach = getByFilename(name);
			set.add(attach);
		}
		return set;
	}
	
	Attachment getByFilename(String filename) {
		String hql = "from Attachment  a where a.newFilename =:name";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("name", filename);
		
		return (Attachment)query.list().get(0);
	}

	@Override
	public void del(Attachment attach) {
		// TODO Auto-generated method stub
		File file = new File(attach.getPath()+File.separator+attach.getNewFilename());
		if(file.exists())
			file.delete();
		
		this.remove(attach);
	}

}
