package com.hexaware.MLP206.model;

import com.hexaware.MLP206.persistence.DbConnection;
import com.hexaware.MLP206.persistence.EmployeeDAO;
import com.hexaware.MLP206.util.CliMain;

import java.util.Objects;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee
{

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
    private Date DOJ;
    private int avail_l;
    static int counter = 0;
    Employee[] arr = new Employee[5];
  
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null)
     {
      return false;
     }
        if (getClass() != obj.getClass())
     {
      return false;
     }

    final Employee emp = (Employee) obj;
        if (Objects.equals(empId, emp.empId)&& 
         Objects.equals(e_name, emp.e_name)&&
         Objects.equals(e_des, emp.e_des)&&
         Objects.equals(mgr_id, emp.mgr_id)&&
         Objects.equals(e_add, emp.e_add)&&
         Objects.equals(e_phno, emp.e_phno)&&
         Objects.equals(e_mail, emp.e_mail)&&
         Objects.equals(DOJ, emp.DOJ)&&
         Objects.equals(avail_l, emp.avail_l)
         )
    {
      return true;
    }
      return false;
  } 

  @Override
       public final int hashCode() 
  {
    return Objects.hash(empId,e_name,e_des,mgr_id,e_add,e_phno,e_mail,DOJ,avail_l);
  }



// toString Method
  @Override
       public String toString()
  {
    return        
                   " Employee Id: " + empId+ 
                  "Employee name: " + e_name + 
                 "Employee des: "+ e_des +
                "Manager id: " + mgr_id + 
              "Employee Address: " + e_add + 
            "Employee phone no: " + e_phno + 
          "Employee mail: " + e_mail +
          "Employee DOJ: " + DOJ +
          "Employee avail_l: " + avail_l;

  }
  


      /**
      * @param argEmpId to initialize employee id.
     * @param i
     * @param date
      */


      //parameterized constructor
  public Employee(final int argEmpId, final String e_name, final String e_des, final int mgr_id,
          final String e_add, final int e_phno, final String e_mail, final Date date, final int avail_l)
   {
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //Date doj = sdf.parse(DOJ);
    this.empId= argEmpId;
             this.e_name=e_name;
                         this.e_des=e_des;
                                 this.mgr_id=mgr_id;
                                         this.e_add=e_add;
                                                   this.e_phno=e_phno;
                                                              this.e_mail=e_mail;
                                                                          this.DOJ=date;
                                                                                  this.avail_l=avail_l;
  
  
    }   
    
          //Default constructor

    public Employee()
   {

   }
    public void  login()
   {
    CliMain cl= new CliMain();
    cl.login();
   }


// getters
    public final int gete_id()
   {
    return empId;
   }
    public final String gete_name()
   {
    return e_name;
   }
    public final String gete_des()
   {
    return e_des;
   }
    public final int getmgr_id()
   {
    return mgr_id;
   }
    public final String gete_add()
   {
    return e_add;
   }
    public final int gete_phno()
   {
    return e_phno;
   }
    public final String gete_mail()
   {
    return e_mail;
   }
    public final Date getDOJ()
   {
     return DOJ;
   }
    public final int getavail_l()
   {
     return avail_l;
   }

   //setters

    public final void sete_id(final int e_id) 
   {
    this.empId =  e_id;
   }
    public final void sete_name(final String e_name)
   {
    this.e_name=e_name;
   }
   public final void sete_des(final String e_des)
   {
   this.e_des=e_des;
   }
   public final void setmgr_id(final int mgr_id)
   {
   this.mgr_id=mgr_id;
   }
   public final void sete_add(final String e_add)
   {
    this.e_add=e_add;
   }
   public final void sete_phno(final int e_phno)
   {
   this.e_phno=e_phno;
   }
   public final void sete_mail(final String e_mail)
   {
       this.e_mail=e_mail;
   }
    public final void setDOJ(final Date DOJ)
    {
      this.DOJ=DOJ;
    }
    public final void setavail_l(final int avail_l)
    {
      this.avail_l= avail_l;
    }
      
  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() 
  
  {
    final DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }



  /**
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[] listAll() {
    final List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }

/**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int emp_id) 
  {
    return dao().find(emp_id);
  }


}
