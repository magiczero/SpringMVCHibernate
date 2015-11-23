package com.cngc.pm.dao.impl.cms;

import static com.cngc.utils.Common.isNumeric;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.cms.PropertyDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.cms.Property;

@Repository
public class PropertyDAOImpl extends BaseDAOImpl<Property, Long> implements PropertyDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.cms.PropertyDAO#getSet(java.lang.String)
	 */
	@Override
	public Set<Property> getSet(String ids) {
		Set<Property> set = new HashSet<>();
		String id[] = ids.split(",");
		int j = id.length;
		Long[] idss = new Long[j];
		for (int i = 0; i < id.length; i++) {
			String str = id[i];
			if (!isNumeric(str)) {
				return null;
			}
			idss[i] = Long.valueOf(str);
		}
		try {
			for (Long k : idss) {
				set.add(this.find(k));
			}
		} catch (Exception e) {
			return null;
		}

		return set;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.cms.PropertyDAO#getSetByNIds(java.util.List)
	 */
	@Override
	public List<Property> getSetByNIds(List<Long> ids) {
		List<Property> properties;

		if (ids.size() > 0) {
			Query query = this.getSession().createQuery("from Property a where a.id not in(:ids)")
					.setParameterList("ids", ids);
			properties = query.list();
		} else
			properties = this.findAll();

		return properties;
	}
}