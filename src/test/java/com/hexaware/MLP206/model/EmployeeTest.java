package com.hexaware.MLP206.model;

import com.hexaware.MLP206.persistence.EmployeeDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Test class for Employee.
 */
@RunWith(JMockit.class)
public class EmployeeTest 
{

  /**
   * setup method.
   */
  @Before
  public void initInput() {

  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void Employee() throws ParseException
   {
    Date doj;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
    doj= sdf.parse("2018-09-08");
    Employee emp=new Employee(50996,"anisha","hr",50985,"bihar",75767,"qwe",doj,21);
    assertEquals(50996, emp.gete_id(),1);
    
    Employee e1 = new Employee(50996,"anisha","hr",50985,"bihar",75767,"qwe",doj,21);
    Employee e2 = new Employee(50960,"Dawar","deve",50996,"Assam",75787,"gfd",doj,22);
    Employee e3 = new Employee(50996,"anisha","hr",50985,"bihar",75767,"qwe",doj,21);
    Employee e4 = new Employee(50983,"sowmya","hr",50959,"bihar",75707,"jhg",doj,29);
    // assertNotEquals(e100, null);
    assertNotEquals(e2, e4);
    assertEquals(e1,e3);
   
    assertEquals(e1.hashCode(), e3.hashCode());
    assertNotEquals(e2.hashCode(),e4.hashCode());
    }
    


  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.list(); result = new ArrayList<Employee>();
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(0, es.length);
  }

  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  // @Test
  // public final void testListAllSome(@Mocked final EmployeeDAO dao) {
  //   new Expectations() {
  //     {
  //       ArrayList<Employee> es = new ArrayList<Employee>();
  //       es.add(new Employee(1));
  //       es.add(new Employee(10));
  //       es.add(new Employee(100));
  //       dao.list(); result = es;
  //     }
  //   };
  //   new MockUp<Employee>() {
  //     @Mock
  //     EmployeeDAO dao() {
  //       return dao;
  //     }
  //   };
  //   Employee[] es = Employee.listAll();
  //   assertEquals(3, es.length);
  //   assertEquals(new Employee(1), es[0]);
  //   assertEquals(new Employee(10), es[1]);
  //   assertEquals(new Employee(100), es[2]);
  // }

    /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListById(@Mocked final EmployeeDAO dao) throws Exception{
  Date doj;
  SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
  doj= sdf.parse("2018-09-08");
  Employee emp=new Employee(50996,"anisha","hr",50985,"bihar",75767,"qwe",doj,21); {
    final Employee e100 = new Employee(50996,"anisha","hr",50985,"bihar",75767,"qwe",doj,21);
    new Expectations() {
      {
        dao.find(100); result = e100;
        dao.find(-1); result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e = Employee.listById(100);
    assertEquals(e100, e);

    e = Employee.listById(-1);
    assertNull(e);
  }
  }
  //parameterized constr test
  @Test
  public void testEmployee() throws ParseException
  {
    SimpleDateFormat sdf;
	try {
    sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date doj = sdf.parse("2020-01-10");
      Employee m = new Employee (50996,"anisha","hr",50985,"bihar",75767,"qwe",doj,21);
      assertEquals(50996, m.gete_id());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

  }
  //Default constr
@Test
public void testEmployeeDefault() 
  {
    Employee m1  = new Employee();
    assertEquals("hashcode", m1.hashCode(), new Employee().hashCode());

  }
}