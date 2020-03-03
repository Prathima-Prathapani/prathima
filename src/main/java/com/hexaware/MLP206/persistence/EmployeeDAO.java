package com.hexaware.MLP206.persistence;

import com.hexaware.MLP206.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  
  @SqlUpdate("insert into Employee(empId,e_name,e_des,mgr_id,e_add,e_phno,e_mail) values(:e_id, :e_name, e_des, : mgr_id, :e_add, :e_phno, :e_mail)")
  void insertEmployee
  (
    @Bind ("empId") int empId,
    @Bind ("e_name") String e_name,
    @Bind ("e_des") String e_des,
    @Bind ("mgr_id") int mgr_id,
    @Bind ("e_add") String e_add,
    @Bind ("e_phno") double e_phno,
    @Bind ("e_mail") String e_mail
    );

  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  @SqlUpdate("Delete from Employee where empId = :empId")
    void deleteEmployee(
      @Bind("empId") int empId
      
      );

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE empId = :empId")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empId") int empId);

  /**
  * close with no args is used to close the connection.
  */
  void close();


/**
   * return all the details of the selected employee.
   * @param empid the id of the employee
   * @param leavendays for leave no of days
   */
  @SqlUpdate("UPDATE EMP_DETAILS SET AVAIL_LEAVE_BAL = AVAIL_LEAVE_BAL +:leavendays WHERE EMP_ID = :empid")
  void increment(@Bind("empid") int empid, @Bind("leavendays") int leavendays);

  /**
   * Update LeaveBalance for selected employee.
   * @param empID the id of the employee.
   * @param leaveTaken no of days employee applied leave.
   */
  @SqlUpdate("UPDATE EMP_DETAILS SET AVAIL_LEAVE_BAL = AVAIL_LEAVE_BAL -:leaveTaken WHERE EMP_ID = :empID")
  void decrement(@Bind("empID") int empID, @Bind("leaveTaken") int leaveTaken);
}