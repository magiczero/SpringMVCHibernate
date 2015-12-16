package com.cngc.pm.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.cngc.pm.service.ResourcesService;

public class MyInvocationSecurityMetadataSource implements
				FilterInvocationSecurityMetadataSource {
	//由spring调用
	Logger log=Logger.getLogger(MyInvocationSecurityMetadataSource.class);
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private ApplicationContext context;

	@Resource
	private ResourcesService resourcesService;
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MyInvocationSecurityMetadataSource() {
		loadResourceDefine();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
	//加载所有资源与权限的关系
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		context = new ClassPathXmlApplicationContext(
				"classpath:servlet-context.xml");
		SessionFactory sessionFactory = (SessionFactory) context
				.getBean("hibernate4AnnotatedSessionFactory");
		
		Session session = sessionFactory.openSession();
		String sql = "";
		sql="select resource_path from sys_resources";
		@SuppressWarnings("unchecked")
		List<String> urls = session.createSQLQuery(sql).list();
		for (String url : urls) {
		    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
			sql = "select sr.role_name from sys_resources res, sys_authorities_resources sar, sys_authorities sa, sys_roles_authorities sra, sys_roles sr where res.resource_id=sar.resource_id and sar.authority_id=sa.authority_id and sa.authority_id=sra.authority_id and sra.role_id=sr.role_id and res.resource_path='"
			+ url + "'";
			@SuppressWarnings("unchecked")
			List<String> roles = session.createSQLQuery(sql).list();
			for (String role : roles) {
				ConfigAttribute ca = new SecurityConfig(role);
				atts.add(ca);
			}
			
			resourceMap.put(url, atts);
		}
		session.close();
		
//		List<Resources> res = resourcesService.getAll();
//		System.out.println("url size :"+ res.size());
//		for(Resources r : res) {
//			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//			for(Authority auth : r.getAuthSet()) {
//				for(Role role : auth.getRoleSet()) {
//					ConfigAttribute ca = new SecurityConfig(role.getRoleName());
//					atts.add(ca);
//				}
//			}
//			resourceMap.put(r.getPath(), atts);
//		}
	}
	
	public boolean pathMatchesUrl(String resUrl, String url) {
//		System.out.println("数据库 ："  + resUrl);
//		System.out.println("访问路径 ："  + url);
		PathMatcher  urlMatcher = new AntPathMatcher();
		if(urlMatcher.match(resUrl, url)) {
			return true;
		}
		return false;
//		if (resUrl.equals(url)||url.equals("/"+resUrl))
//			return true;
//		else
//			return false;
	}

    public static Map<String, Collection<ConfigAttribute>> getResourceMap() {
        return resourceMap;
    }

    public static void setResourceMap(
            Map<String, Collection<ConfigAttribute>> resourceMap) {
    	MyInvocationSecurityMetadataSource.resourceMap = resourceMap;
    }
    
  //返回所请求资源所需要的权限
  	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
  		
//  		String requestUrl = ((FilterInvocation) object).getRequestUrl();
//  		log.info("requestUrl is " + requestUrl);
//  		if(resourceMap == null) {
//  			loadResourceDefine();
//  		}
//  		log.info("通过资源定位到的权限："+resourceMap.get(requestUrl));
//  		return resourceMap.get(requestUrl);
  		
  		String url = ((FilterInvocation) object).getRequestUrl();
//  		System.out.println("访问路径：" + url);
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		//每个资源必须属于一个属于一个角色，否则赋予一个不存在的角色
//		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//		ConfigAttribute ca = new SecurityConfig("ROLE_NON");
//		atts.add(ca);
//		return atts;
		//如果没有角色，会默认认为所有人都可以访问
		return null;
  	}

}
