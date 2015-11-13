package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.AttachmentDAO;
import com.cngc.pm.dao.DocumentDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.Document;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDAO docDao;
	
	@Autowired
	private AttachmentDAO attachDao;
	
	@Autowired
	private StyleDAO styleDao;

	@Override
	@Transactional(readOnly=true)
	public Set<Attachment> getSetAttach(String... filenames) {
		// TODO Auto-generated method stub
		return attachDao.getSet(filenames);
	}

	@Override
	@Transactional
	public void save(Document document) {
		// TODO Auto-generated method stub
		docDao.save(document);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Document> getAll(Long userid) {
		// TODO Auto-generated method stub
		//return docDao.findAll();
		return docDao.getListBySelfAndOpen(userid);
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, String> getMapStyle() {
		// TODO Auto-generated method stub
		List<Style> list = styleDao.getListByType(10);
		
		Map<String , String> styleMaps = new HashMap<String, String>();
		for(Style style : list) {
			styleMaps.put(String.valueOf(style.getId()), style.getName());
		}
		
		return styleMaps;
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Style> getSetStyle(Long... ids) {
		// TODO Auto-generated method stub
		return styleDao.getSet(ids);
	}

	@Override
	@Transactional(readOnly=true)
	public Style loadStyleById(Long id) {
		// TODO Auto-generated method stub
		return styleDao.find(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Style> getListStyle() {
		// TODO Auto-generated method stub
		return styleDao.getListByType(10);
	}

	@Override
	@Transactional
	public boolean delByIds(String ids) {
		// TODO Auto-generated method stub
		String id[] = ids.split(",");
		int j = id.length;
		Long[] idss = new Long[j];
		for(int i=0; i<id.length; i++) {
			String str = id[i];
			 if (!isNumeric(str)) {
				 return false;
			 }
			 idss[i] = Long.valueOf(str);
		}
		try {
			for(Long k : idss) {
				Document doc = docDao.find(k);
				
				for(Attachment attach : doc.getAttachs()) {
					attachDao.del(attach);
				}
				
				docDao.remove(doc);
				
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Document> getAllByStyle(Long userId, Long styleId) {
		// TODO Auto-generated method stub
		return docDao.getListBySelfAndOpenAndStyle(userId, styleId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Document> getAllByPrivate(Long userId) {
		// TODO Auto-generated method stub
		return docDao.getListBySelf(userId);
	}

	@Override
	@Transactional
	public boolean delById(Long docid, Long userid) {
		// TODO Auto-generated method stub
		Document doc = docDao.find(docid);
		if(doc.getUser().getId() == userid) {
			for(Attachment attach : doc.getAttachs()) {
				attachDao.del(attach);
			}
			
			docDao.remove(doc);
			
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Document> getAllLastVersion() {
		// TODO Auto-generated method stub
		return docDao.getListByLastVersion();
	}

	@Override
	@Transactional(readOnly=true)
	public Document getById(long docid) {
		// TODO Auto-generated method stub
		return docDao.find(docid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Style> getAllCheckItems() {
		// TODO Auto-generated method stub
		return styleDao.getListByType(47);
	}

	@Override
	@Transactional
	public void update(Document document) {
		// TODO Auto-generated method stub
		docDao.update(document);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Document> getByStyle(long styleid) {
		// TODO Auto-generated method stub
		return docDao.getByStyle(styleid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Document> getByItem(long itemid) {
		// TODO Auto-generated method stub
		return docDao.getByItem(itemid);
	}
	
}
