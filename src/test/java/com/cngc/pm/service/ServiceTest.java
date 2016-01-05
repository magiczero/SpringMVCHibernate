package com.cngc.pm.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cngc.pm.model.Document;
import com.cngc.pm.model.Person;
import com.cngc.pm.model.Style;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
public class ServiceTest {

	static ApplicationContext context;
	
	static CheckItemsService checkService;
	
	static PersonService personService;
	
	Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);
	
	@BeforeClass
    public static void setUpClass() {
        context = new ClassPathXmlApplicationContext("servlet-context.xml");
        checkService = context.getBean(CheckItemsService.class);
        personService = context.getBean(PersonService.class);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    @Ignore
    public void testfindCurrentYearStudent() {

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
    
    public void testCollegeFind() {

    	List<Person> persons = personService.testPersons();
    	LOGGER.info("Persons :" + persons);
    	assertNotNull(persons);
    	assertEquals(persons.size(), 2);
    	LOGGER.info("Persons size :" + persons.size());
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
