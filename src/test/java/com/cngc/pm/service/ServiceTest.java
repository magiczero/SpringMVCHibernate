package com.cngc.pm.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.util.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.model.Group;
import com.cngc.pm.model.Incident;
import com.cngc.pm.model.Person;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.Relations;
import com.cngc.pm.threemember.template.DocumentHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class ServiceTest {

//	static ApplicationContext context;
//	
//	static CheckItemsService checkService;
//	
//	static PersonService personService;
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private ThreeMemberService threeService;
	@Autowired
	private IncidentService incidentService;
	@Autowired
	private PersonService personService;
	
	Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);
	
	@BeforeClass
    public static void setUpClass() {
//        context = new ClassPathXmlApplicationContext("servlet-context.xml");
//        checkService = context.getBean(CheckItemsService.class);
//        personService = context.getBean(PersonService.class);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    @Transactional(readOnly=true)
    @Ignore
    public void testfindCurrentYearStudent() {
    	JSONObject jsonObj = new JSONObject();
    	List<Map<String, Object>> list2= new ArrayList<>();
    	for(Group group : groupService.getAllTop()) {
    		Map<String, Object> map2= new HashMap<>();
    		System.out.println(group.getGroupName());
    		map2.put("groupId", group.getId());
    		map2.put("groupName", group.getGroupName());
    		if(!group.getUsers().isEmpty()) {
    			List<Map<String, String>> list= new ArrayList<>();
    			for(SysUser user : group.getUsers()) {
    				System.out.println(user.getUsername());
    				Map<String, String> map = new HashMap<>();
    				map.put("userId", user.getUsername());
    				map.put("userName", user.getName());
    				map.put("userTel", user.getDepName());
    				map.put("userRoom", user.getMechName());
    				list.add(map);
    			}
    			map2.put("users", list);
    		}
    		
    		if(!group.getChild().isEmpty()) {
    			List<Map<String, Object>> list= new ArrayList<>();
    			for(Group group1 : group.getChild()) {
    				System.out.println(group1.getGroupName());
    				Map<String, Object> map = new HashMap<>();
    				map.put("groupId", group1.getId());
    				map.put("groupName", group1.getGroupName());
    				if(!group1.getUsers().isEmpty()) {
    					List<Map<String, String>> list1= new ArrayList<>();
    	    			for(SysUser user : group1.getUsers()) {
    	    				System.out.println(user.getUsername());
    	    				Map<String, String> map1 = new HashMap<>();
    	    				map1.put("userId", user.getUsername());
    	    				map1.put("userName", user.getName());
    	    				map1.put("userTel", user.getDepName());
    	    				map1.put("userRoom", user.getMechName());
    	    				list1.add(map1);
    	    			}
    	    			map.put("users", list1);
    				}
    				list.add(map);
    			}
    			map2.put("child", list);
    		}
    		list2.add(map2);
    	}
    	System.out.println("");
    	System.out.println(list2);
    	System.out.println("");
    	jsonObj.put("map", list2);
    	System.out.println(jsonObj);
    	
//    	String str = groupService.getAllWithJson();
//    	
//    	System.out.println(str);
//    	
//    	assertNotNull(str);

//        List<Object[]> list = checkService.getAllItemsByCode("BMB22");
//        for(Object[] objs : list) {
//        	//System.out.println(objs[5]);
//        	Style style = (Style)objs[0];
//        	System.out.print(style.getName() + " : ");
//        	System.out.println(style.getStyle().getName() + " : ");
//        	//System.out.println(style.getItems().size());
//        }
////        Assert.assertEquals(students.size(), 2);
//        System.out.println(list.size());
//        assertNotNull(list);
    }
    
    @Test
    public void testPersonAudit() throws Exception {
    	Person person = new Person();
    	person.setCountry("china");
    	person.setName("xiaoming");
    	
    	personService.addPerson(person);
    	//personDao.save(person);
    	
    	System.out.println(person);
//    	
//    	person.setCountry("japan");
//    	person.setName("xx");
//    	
//    	personService.addPerson(person);
    	//personDao.save(person);
    	
//    	AuditReader reader = AuditReaderFactory.get(personDao.getSession());
//    	
//    	Person original = reader.find(Person.class, person.getId(), 1);
//        Person current = reader.find(Person.class, person.getId(), 2);
//        
//        System.out.println("------------------------------------------------------------------------------------------------------------------");
//        System.out.print("First Version : ");
//        System.out.println(original);
//        System.out.print("Second Version : ");
//        System.out.println(current);
//        System.out.println("------------------------------------------------------------------------------------------------------------------");
//    	
    }

    @Test
    @Ignore
    public void testCollegeFind() throws Exception {
    	Incident incident = new Incident();
    	
    	incident.setAbs("dfdfdfdfdf");
    	incident.setApplyUser("wangdan");
    	incident.setCategory("010101");
    	incident.setCritical("04");
    	incident.setDetail("eeeeeeee");
    	incident.setFinishTime(new Date());
    	incident.setInfluence("04");
    	incident.setPhoneNumber("7777");
    	incident.setPriority("04");
    	incident.setSource("01");
    	incident.setSupportType("01");
    	incident.setApplyTime(new Date());
    	incident.setType("01");
    	incident.setStatus("01");

    	incidentService.save(incident, "mating","192.168.0.1");
    	DocumentHandler dh = new DocumentHandler();
    	
    	try {
			dh.createDoc();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List<Relations> list= threeService.getRelationItemListByType(ManageType.SystemManager);
    	
    	System.out.println(list.size());
    	
    	assertNotNull(list);
    	//List<Document> list = docService.getListByUserAndNum("admin", 0);
    	//SysCode code = docService.getCode(docService.getById(107l));
    	//String str = itemsService.getJSonByCode("CI");
    	
    	//System.out.println("document size is "+code.getId());
    	
    	//assertNotNull(str);
//    	List<Person> persons = personService.testPersons();
//    	LOGGER.info("Persons :" + persons);
//    	assertNotNull(persons);
//    	assertEquals(persons.size(), 2);
//    	LOGGER.info("Persons size :" + persons.size());
//        College college = collegeService.findById(1);
//        LOGGER.info("College :" + college);
//        assertNotNull(college);
//        assertEquals(college.getStudents().size(), 2);
//        LOGGER.info("College :" + college.getStudents().size());
    }

    @AfterClass
    public static void tearDownClass() {
    }
}
