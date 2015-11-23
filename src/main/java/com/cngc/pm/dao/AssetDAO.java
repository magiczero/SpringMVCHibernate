package com.cngc.pm.dao;

import com.cngc.pm.model.Asset;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface AssetDAO extends GenericDAO<Asset, Long> {

	boolean isExsitsAssetNum(String assetNum);

}
