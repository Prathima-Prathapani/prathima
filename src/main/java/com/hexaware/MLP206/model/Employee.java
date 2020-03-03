package com.hexaware.MLP206.model;

import com.hexaware.MLP206.persistence.DbConnection;
import com.hexaware.MLP206.persistence.EmployeeDAO;
import com.hexaware.MLP206.util.CliMain;


import java.util.Objects;
import java.util.List;

/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {

  /**
   * empId to store employee id.
   */
  private int empId;
  private String e_name;
  private String e_des;
  private int mgr_id;
  private String e_add;
  private int e_phno;
  private String e_mail;
   static int counter;

  
  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empId, emp.empId)&& Objects.equals(e_name,emp.e_name)&& Objects.equals(e_des,emp.e_des)
         && Objects.equals(mgr_id,emp.mgr_id) && Objects.equals(e_add,emp.e_add)&& Objects.equals(e_phno,emp.e_phno)
         && Objects.equals(e_mail,emp.e_mail))
          {
      return true;
    }
    return false;
  }

  @Override
  
  public final int hashCode() {
    return Objects.hash(empId);
  }
  


  /**
   * @param argEmpId to initialize employee id.
   */
  public Employee(final int argEmpId,final String e_name,final String e_des,
  final int mgr_id,final String e_add,final int e_phno,final String e_mail)
   {
    this.empId = argEmpId;
    this.e_name=e_name;
    this.e_des=e_des;
    this.e_add=e_add;
    this.e_phno=e_phno;
    this.e_mail=e_mail;
  }

  public Employee(int int1) {
}

public Employee() {
}

/**
 * Gets the EmployeeId.
 * 
 * @return this Employee's ID.
 */

  public void  login()
  {
  CliMain cl= new CliMain();
  cl.login();
  }




  
  public final int getEmpId() {
    return empId;
  }
  public final String getname()
  {
      return e_name;
  }
  public final String getdes()
  {
      return e_des;
  }
  public final int getmgr_id()
  {
      return mgr_id;
  }
  public final String getadd()
  {
      return e_add;
  }
  public final int getphno()
  {
      return e_phno;
  }
  public final String getmail()
  {
      return e_mail;
  }

  /**
   *
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }
  public final void setname(final String e_name)
   {
       this.e_name=e_name;
   }
   public final void setdes(final String e_des)
   {
       this.e_des=e_des;
   }
   public final void setmgr_id(final int mgr_id)
   {
       this.mgr_id=mgr_id;
   }
   public final void setadd(final String e_add)
   {
       this.e_add=e_add;
   }
   public final void setphno(final int e_phno)
   {
       this.e_phno=e_phno;
   }
   public final void setmail(final String e_mail)
   {
       this.e_mail=e_mail;
   }


  //  static Employee[] arr = new Employee[5];

  //   static void addEmployee() {
  //     Employee e = new Employee();
  //     e.setEmpId(1011);
  //     e.setname("Anisha pagal ladki");
  //     e.setdes("S Dev");
  //     e.setmgr_id(2011);
  //     e.setadd("MUmbai");
  //     e.setphno(900363441);
  //     e.setmail("dawarsarma@gmail.com");

  //     e.arr[counter++] = e;

  //   }

    // static void display() {
    //   for (int i = 0; i < 5; i++) {
    //     addEmployee();
    //   }
    //   System.out.println("Employee List");
    //   for (int i = 0; i < 5; i++) {
    //     System.out.println(arr[i].empId + "  " + arr[i].e_name);
    //   }

    //     }

  

   @Override
  public String toString() {
    return  " Employee Id: " + empId + "Employee name: " + e_name
     + "Employee des: "+ e_des + "Manager id: " + mgr_id + "Employee Address: " + e_add
      + "Employee phone no: " + e_phno + "Employee mail: " + e_mail;
  }






  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[] listAll() {

    List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }


  
  public static void insertEmployee( int empId, String e_name , String e_des,
   int mgr_id, String e_add, double e_phno,String e_mail){
    dao().insertEmployee(empId,e_name,e_des,mgr_id,e_add,e_phno,e_mail);
   }

   public static void deleteEmployee(int empId){

    
    dao().deleteEmployee(empId);
}
  /**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int empID) {
    return dao().find(empID);
  }

public long getavail_l() {
	return 0;
}

}
