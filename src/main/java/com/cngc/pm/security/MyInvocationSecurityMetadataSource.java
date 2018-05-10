package com.cngc.pm.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;
import com.cngc.pm.service.ResourcesService;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;

public class MyInvocationSecurityMetadataSource implements
				FilterInvocationSecurityMetadataSource {
	//由spring调用
//	Logger log=Logge..getLogger(MyInvocationSecurityMetadataSource.class);
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MyInvocationSecurityMetadataSource.class);      
//	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
//	private ApplicationContext context;

	@Resource
	private ResourcesService resourcesService;
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MyInvocationSecurityMetadataSource() {
		//loadResourceDefine();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
	//加载所有资源与权限的关系
//	private void loadResourceDefine() {
//		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
//		context = new ClassPathXmlApplicationContext(
//				"classpath:servlet-context.xml");
//		SessionFactory sessionFactory = (SessionFactory) context
//				.getBean("hibernate4AnnotatedSessionFactory");
//		
//		Session session = sessionFactory.openSession();
//		String sql = "";
//		sql="select resource_path from sys_resources";
//		@SuppressWarnings("unchecked")
//		List<String> urls = session.createSQLQuery(sql).list();
//		for (String url : urls) {
//		    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//			sql = "select sr.role_name from sys_resources res, sys_authorities_resources sar, sys_authorities sa, sys_roles_authorities sra, sys_roles sr where res.resource_id=sar.resource_id and sar.authority_id=sa.authority_id and sa.authority_id=sra.authority_id and sra.role_id=sr.role_id and res.resource_path='"
//			+ url + "'";
//			@SuppressWarnings("unchecked")
//			List<String> roles = session.createSQLQuery(sql).list();
//			for (String role : roles) {
//				ConfigAttribute ca = new SecurityConfig(role);
//				atts.add(ca);
//			}
//			
//			resourceMap.put(url, atts);
//		}
//		session.close();
//
//		ObjectMapper mapper = new ObjectMapper();
//
//	       mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
//
//	       String json = "";
//		try {
//			json = mapper.writeValueAsString(resourceMap);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info("权限资源对应关系："+json);
//	}
	
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

//    public static Map<String, Collection<ConfigAttribute>> getResourceMap() {
//        return resourceMap;
//    }

//    public static void setResourceMap(
//            Map<String, Collection<ConfigAttribute>> resourceMap) {
//    	MyInvocationSecurityMetadataSource.resourceMap = resourceMap;
//    }
    
  //返回所请求资源所需要的权限
  	public Collection<ConfigAttribute> getAttributes(Object object) {
  		
//  		String requestUrl = ((FilterInvocation) object).getRequestUrl();
//  		log.info("requestUrl is " + requestUrl);
//  		if(resourceMap == null) {
//  			loadResourceDefine();
//  		}
//  		log.info("通过资源定位到的权限："+resourceMap.get(requestUrl));
//  		return resourceMap.get(requestUrl);
  		
  		String url = ((FilterInvocation) object).getRequestUrl();
  		//System.out.println(url);
  		logger.info("访问路径：" + url);
  		
  		List<Resources> resourcesList = resourcesService.getAll();
  		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
  		for(Resources r : resourcesList) {
  			if (pathMatchesUrl(r.getPath(), url)) {
  				//System.out.println(r.getId());
  				//根据资源获得角色
  				for (Role role : resourcesService.getRoles(r)) {
  					ConfigAttribute ca = new SecurityConfig(role.getRoleName());
  					atts.add(ca);
  				}
  				return atts;
  			}
  		}
  		return null;
  		
//		Iterator<String> ite = resourceMap.keySet().iterator();
//		while (ite.hasNext()) {
//			String resURL = ite.next();
//			if (pathMatchesUrl(resURL, url)) {
//				return resourceMap.get(resURL);
//			}
//		}
		//每个资源必须属于一个属于一个角色，否则赋予一个不存在的角色
//		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//		ConfigAttribute ca = new SecurityConfig("ROLE_NON");
//		atts.add(ca);
//		return atts;
		//如果没有角色，会默认认为所有人都可以访问
//		return null;
  	}

}
