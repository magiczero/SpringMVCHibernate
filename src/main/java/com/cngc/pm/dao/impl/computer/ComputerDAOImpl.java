package com.cngc.pm.dao.impl.computer;

import static com.cngc.utils.Common.isNumeric;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.computer.ComputerDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.computer.Computer;

@Repository
public class ComputerDAOImpl extends BaseDAOImpl<Computer,Long> implements ComputerDAO{

	
	@Override
	public Computer getComputerByMac(String mac){
		String hql = "from Computer where mac = ?";
		 Query query = this.getSession().createQuery(hql);
		 
		 query.setParameter(0, mac);
		 @SuppressWarnings("unchecked")
		List<Computer> list = query.list();
		 if(list.size() > 0) {
			 Computer computer = (Computer)query.list().get(0);
			 return computer;
		 }
		return null;
	}

	@Override
	public Set<Computer> getComputerByIds(String ids) {
		// TODO 自动生成的方法存根
		Set<Computer> set = new HashSet<>();
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

	@Override
	public boolean removeByIds(String ids) {
		// TODO 自动生成的方法存根
		String sql = "delete from Computer where id in("+ids+")";

		Query query = this.getSession().createQuery(sql);
		query.executeUpdate();
		
		return true;
	}

	@Override
	public boolean setDepartment(String ids, Long groupid) {
		// TODO 自动生成的方法存根
		String sql = "update Computer set group_id="+groupid+",update_date='"
				+ new Timestamp(System.currentTimeMillis()) + "' where id in("+ids+")";

		Query query = this.getSession().createQuery(sql);
		query.executeUpdate();
		
		return true;
	}
	
}
