package com.cngc.pm.dao.impl.computer;

import static com.cngc.utils.Common.isNumeric;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.computer.InspectionItemDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.computer.InspectionItem;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class InspectionItemDAOImpl extends BaseDAOImpl<InspectionItem,Long> implements InspectionItemDAO{

	@Override
	public Set<InspectionItem> getSet(String ids){
		Set<InspectionItem> set = new HashSet<>();
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionItem> getSetByNIds(List<Long> ids){
		List<InspectionItem> items;

		if (ids.size() > 0) {
			Query query = this.getSession().createQuery("from InspectionItem a where a.itemId not in(:ids)")
					.setParameterList("ids", ids);
			items = query.list();
		} else
			items = this.findAll();

		return items;
	}
	
	@Override
	public SearchResult<InspectionItem> getByIds(String ids){
		Search search = new Search();
		String items[] = ids.split(",");
		List<String> list = new LinkedList<String>();
		for(String s:items)
		{
			list.add(s);
		}
		search.addFilterIn("itemId", list);
		
		return this.searchAndCount(search);
	}
}
