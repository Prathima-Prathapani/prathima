package com.hexaware.MLP206.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.hexaware.MLP206.persistence.EmployeeDAO;
//import com.persistence.leevDAO;
import com.hexaware.MLP206.persistence.leevDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;

// unit test class for leaves

@RunWith(JMockit.class)
public class leevTest1 {

    leev l;

    @Before
    public void initInput() {
        l = new leev();
    }

    @After
    public void destroyObject() {
        l = null;
    }

    // test for default constructor
    @Test
    public void testleevDefault() {
        leev newleev = new leev();
        assertEquals("hashcode", newleev.hashCode(), new leev().hashCode());

    }
    // final int l_id, final LeaveType l_type, final LeaveStatus l_status, final Date leev_start_dDate,
    //      final Date leev_endDate, final int leev_emp_id, final int ln, final String r, final Date ld

    @Test
    public void testEquals() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final Date d = sdf.parse("2020-01-14");
        final Date d1 = sdf.parse("2020-01-16");
        final Date d2 = sdf.parse("2020-01-13");
        final Date d3 = sdf.parse("2020-01-12");
        final Date d4 = sdf.parse("2020-01-11");
        leev l = new leev(32,LeaveType.ML,LeaveStatus.APPROVED,d,d1,2,2,"notfeelingwell",d2);
        leev l1 = new leev(32,LeaveType.ML,LeaveStatus.REJECTED,d,d1,2,2,"notfeelingwell",d2);
        leev l2 = new leev(31,LeaveType.EL,LeaveStatus.APPROVED,d3,d,1,2,"fever",d4);
        assertEquals(l, l1);
        assertNotEquals(l2, l);
    }
    @Test
    public void testleevSetters() {
        l = new leev();  
        l.setemp_id(31);
        l.setStatus(LeaveStatus.APPROVED);

        assertNotEquals(31, l.getStatus());

    }
    /**
   * Test to check the functionalty of approveDeny.
   * @param dao to mock the dao class
   * @throws ParseException mocking the dao class
   */
 
      @Test
      public final void testapproveDeny(@Mocked final leevDAO dao) throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final leev ld1 = new leev(31,LeaveType.SL, sdf.parse("2018-12-19"), sdf.parse("2018-11-26"),2,3, "sick", sdf.parse("2018-11-29"));
        final leev ld2 = new leev(32, LeaveType.SL , sdf.parse("2018-12-21"), sdf.parse("2018-12-26"),3, 6,"sick", sdf.parse("2018-11-30"));
        final leev ld3 = new leev(33,LeaveType.SL, sdf.parse("2018-11-19"), sdf.parse("2018-11-24"),4,5, "sick", sdf.parse("2018-12-01"));
        final leev ld4 = new leev(34,LeaveType.ML,sdf.parse("2018-12-21"), sdf.parse("2018-12-26"),3,6,"fever", sdf.parse("2018-11-30"));
    
        new Expectations() {
          {
            dao.find(31); result = ld1;
            dao.find(32); result = ld2;
            dao.find(33); result = ld3;
            dao.find(31); result = ld4;
            dao.find(33); result = null;
          }
        };
        new Expectations() {
          {
           // dao.showManager(1); result = 1001;
            dao.showManager(50959); result = 2002;
            dao.showManager(50996); result = 1001;
            dao.showManager(50975); result = 3002;
            dao.showManager(50959); result = 4002;
          }
        };
        new Expectations() {
    
          {
            dao.comment( "APPROVED", 50975);
            dao.comment("REJECTED",50996);
          }
        };
        new MockUp<leev>() {
          @Mock
          leevDAO dao() {
            return dao;
            }
        };
        
       // final int l_id, final int empId, final LeaveStatus l_status
        String str1 = leev.approveDeny(31,50975,LeaveStatus.APPROVED);
        assertEquals(str1, "Invalid LeaveId");
        String str2 = leev.approveDeny(32,50996,LeaveStatus.APPROVED);
        assertEquals(str2, "Leave Approved Successfully");
        String str3 = leev.approveDeny(33,50960,LeaveStatus.APPROVED);
        assertEquals(str3, "You are not authorised to access this employee.");
        String str4 = leev.approveDeny(31,50959, LeaveStatus.REJECTED);
        assertEquals(str4, "Leave Rejected");
        String str5 = leev.approveDeny(32,50975,LeaveStatus.REJECTED);
        assertEquals(str5, "Leave Rejected");
        String str6 = leev.approveDeny(33,50996,LeaveStatus.APPROVED);
        assertEquals(str6, "Leave Approved Successfully");
        String str7 = leev.approveDeny(31,50959,LeaveStatus.APPROVED);
        assertEquals(str7, "Already on given status");
      }



       /**
   * Test to check the functionalty of approveDeny.
   * @param dao to mock the dao class
   * @param edao to mock the edao class
   * @throws ParseException mocking the dao class
   * @throws NullPointerException mocking the dao class
   */
      @Test
      public final void testapply(@Mocked final EmployeeDAO edao, @Mocked final leevDAO dao)
       throws ParseException, NullPointerException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final Date Date1=sdf.parse("2017-11-11");
        final Date Date2=sdf.parse("2017-11-11");
        final Date today = new Date();
        new Expectations() {
          {
            dao.count(1, sdf.parse("2018-12-27"),sdf.parse("2018-12-28")); result = 0;
            dao.count(1, sdf.parse("2018-12-30"),sdf.parse( "2018-12-30")); result = 1;
          }
        };

        // public Employee(final int argEmpId, final String e_name, final String e_des, final int mgr_id,
        // final String e_add, final int e_phno, final String e_mail, final Date date, final int avail_l)
        new Expectations() {
          {
              
            edao.find(1); result = new Employee(50975, "prathima", "trainee",50959,"chennai",74654,"abc@gmail.com",
                                               Date1, 24);
            edao.find(2); result = new Employee(50960, "dawar", "HR",50996,"marina",775485,"dawar@gmail.com",Date2, 24);
            edao.find(3); result = null;
          }
        };
        // @SqlUpdate("insert into leev values(:l_id,:l_type,:l_status, :l_startDate,:l_endDate,"
        // +":le_id,:l_Ndays, :l_Reason,:l_AppliedOn)")

        new Expectations() {
          {
            dao.apply(31,"SL","PENDING",sdf.parse("2018-12-27") , sdf.parse("2018-12-28"), 2, 3,
                                       "sick", sdf.parse("2018-12-26"));
            dao.apply(32, "ML", "APPROVED",sdf.parse("2018-12-28"),sdf.parse("2018-12-31") ,2,4, "fever"
            , sdf.parse("2018-12-27"));
          }
        };
        new MockUp<Employee>() {
          @Mock
          EmployeeDAO dao() {
            return edao;
            }
        };
        new MockUp<leev>() {
          @Mock
          leevDAO dao() {
            return dao;
            }
        };
        String str1 = leev.applyLeave(1, "2018-12-27", "2018-12-28", 2, "SL",
                                            "sick");
        assertEquals(str1, "Leave Applied Successfully For 2 Days.");
        String str2 = leev.applyLeave(2, "2018-12-27", "2018-12-28", 2, "SL",
                                            "sick");
        assertEquals(str2, "Leave Applied Successfully...");
        String str3 = leev.applyLeave(1, "2018-12-30", "2018-12-30", 1, "SL",
                                            "sick");
        assertEquals(str3, "already applied on given date");
        // String str1 = LeaveDetails.applyLeave(1, "2018-11-26", "2018-11-28", 3, "SL",
        //                                     "sick");
        // assertEquals(str1, "Start should be greater or equal to current date");
        String str4 = leev.applyLeave(1, "2018-12-27", "2018-12-28", 5, "SL",
                                            "sick");
        assertEquals(str4, "NO Of Days Should be right");
        String str5 = leev.applyLeave(1, "2018-12-27", "2019-12-28", 112, "SL",
                                           "sick");
        assertEquals(str5, "insufficient leav balance");
        String str6 = leev.applyLeave(3, "2018-12-27", "2018-12-28", 3, "SL",
                                            "sick");
        assertEquals(str6, "invalid employee");
        String str7 = leev.applyLeave(1, "2018-11-28", "2018-11-30", 3, "SL",
                                            "sick");
        assertEquals(str7, "Start should be greater or equal to current date");
        String str8 = leev.applyLeave(1, "2018-12-28", "2018-12-26", 3, "SL",
                                            "sick");
        assertEquals(str8, "Start Date Must be Greater than EndDate...");
      }


       /**
   * Tests that a list with some pending leaves is handled correctly.
   * @param dao mocking the dao class
   * @throws ParseException mocking the dao class
   */
  @Test
  public final void testListPending(@Mocked final leevDAO dao) throws ParseException {
    new Expectations() {
      {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final ArrayList<leev> es1 = new ArrayList<leev>();
        final ArrayList<leev> es2 = new ArrayList<leev>();

        es1.add(new leev(31,LeaveType.SL, sdf.parse("2018-12-19"), sdf.parse("2018-11-26"),2,3, "sick", sdf.parse("2018-11-29")));

        es1.add(new leev(32, LeaveType.SL , sdf.parse("2018-12-21"), sdf.parse("2018-12-26"),3, 6,"sick", sdf.parse("2018-11-30")));

        es1.add(new leev(33,LeaveType.SL, sdf.parse("2018-11-19"), sdf.parse("2018-11-24"),4,5, "sick", sdf.parse("2018-12-01")));

        es1.add(new leev(34,LeaveType.ML,sdf.parse("2018-12-21"), sdf.parse("2018-12-26"),3,6,"fever", sdf.parse("2018-11-30")));
        dao.pending(4); result = es1;
        dao.pending(5); result = es2;
      }
    };
    new MockUp<leev>() {
      @Mock
      leevDAO dao() {
        return dao;
      }
    };
    leev[] l = leev.listPending(4);
    assertEquals(4, l.length);
    leev[] l2 = leev.listPending(5);
    assertEquals(0, l2.length);
  }


  @Test

  public final void testleevHistory(@Mocked final leevDAO dao) throws ParseException {
    new Expectations() {
        {
         // dao.showManager(1); result = 1001;
          dao.leaveHistory(50959); 
          dao.leaveHistory(50975); 
          dao.leaveHistory(50960); 
          dao.leaveHistory(50996); 
        }
      };
}
  

}