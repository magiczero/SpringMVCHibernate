package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.AssetDAO;
import com.cngc.pm.model.Asset;

@Repository
public class AssetDAOImpl extends BaseDAOImpl<Asset, Long> implements AssetDAO {

	@Override
	public boolean isExsitsAssetNum(String assetNum) {
		String hql = "from Asset a where a.assetNum=:num";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("num", assetNum);
		
		@SuppressWarnings("unchecked")
		List<Asset> list = query.list();
		if(list.size() == 0)
			return false;
		
		return true;
	}


}
