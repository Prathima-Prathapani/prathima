package com.hexaware.MLP206.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import com.hexaware.MLP206.model.Employee;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM Employee")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

 
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

  //manager id

  // @SqlQuery("select * from employee e, employee f where e.e_id = f.mgr_id and e.mgr_id = :mgr_id")
  // @Mapper(EmployeeMapper.class)
  // List<Employee> findManager(@Bind("mgr_id") int mgr_id);
  /**
   * return all the details of the selected employee.
   * @param empid the id of the employee
   * @param leavendays for leave no of days
   */
  @SqlUpdate("UPDATE employee SET avail_l = avail_l + :leavendays WHERE empid = :empId")
  void increment(@Bind("empid") int empid, @Bind("l_Ndays") int leavendays);

  /**
   * Update LeaveBalance for selected employee.
   * @param empID the id of the employee.
   * @param leaveTaken no of days employee applied leave.
   */
  @SqlUpdate("UPDATE employee SET avail_l = avail_l -:leaveTaken WHERE EMP_ID = :empID")
  void decrement(@Bind("empID") int empID, @Bind("l_Ndays") int leaveTaken);


}
