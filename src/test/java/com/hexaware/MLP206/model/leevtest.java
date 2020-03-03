package com.hexaware.MLP206.model;

import com.google.protobuf.TextFormat.ParseException;
import com.hexaware.MLP206.persistence.EmployeeDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
@RunWith(JMockit.class)
public class leevtest {
    leev c;
    @Before
    public void initInput() {
        c = new leev();
    }
    @After
    public void destroyObject() {
        c = null;
    }

@Test
public void Testleevsetters() throws ParseException{
    
{
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
    Date setstartdate;
    Date setendDate;
    Date setappDate;
    try {
        setstartdate = sdf.parse("2020-01-12");
        setendDate=sdf.parse("2020-01-14");
        setappDate=sdf.parse("2020-01-11");
    } catch (java.text.ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    leev l=new leev();
    l.setleev_id(31);
    l.setType("sick");
    l.Setdes("fever");
    l.setStatus("approved");
    l.setstartdate(setstartdate);
    l.setenddate(setendDate);
    l.setemp_id(1);
    l.setlndays(2);
    l.setreason("fever");
    l.setappdate(setappDate);
}
}
 public void testleavegetters() throws ParseException
 {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
    Date setstartdate = sdf.parse("2020-01-12");
    Date setendDate=sdf.parse("2020-01-14");
    Date setappDate=sdf.parse("2020-01-11");
    leev l1=new leev(31,"sick","fever","approved",setstartdate,setendDate,1,2,"fever",setappDate);
    assertNotEquals(31,l1.getleev_id());
    assertNotEquals("sick",l1.getType());
    assertNotEquals("fever",l1.getdes());
    assertNotEquals("approved",l1.getStatus());

 }

}