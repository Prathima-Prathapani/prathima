package com.hexaware.MLP206.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.Date;
import java.util.List;

import com.hexaware.MLP206.model.leev;

// The DAO class for leavedetails.
public interface leevDAO {
// return all the details of all the Leavedetails.

  @SqlQuery("SELECT * FROM leev")
  @Mapper(leevMapper.class)
  List<leev> list();

//return all the details of the selected Leavedetails.

  @SqlQuery("SELECT * FROM leev WHERE l_id = :l_id")
  @Mapper(leevMapper.class)
  leev find(@Bind("l_id") int leaveId);

  //leavemanagercomm Updated Manager Response data.
   @SqlUpdate("UPDATE leev SET L_STATUS = :l_status WHERE  l_id= :l_id")
   @Mapper(leevMapper.class)
   void comment(@Bind("l_status") String dbStatus, @Bind("l_id") int l_id);

//return the Leavedetails object
@SqlQuery("SELECT * FROM leev WHERE "
+ " empId IN "
+ " (SELECT E2.empId FROM "
+ " employee E1 INNER JOIN "
+ " employee  E2 ON E1.empId = E2.mgr_id "
+ " WHERE E1.empId = :empid)  AND "
+ " l_status ='PENDING' ")
@Mapper(leevMapper.class)
List<leev> pending(@Bind("mgrId")int mgrId);

//return the employee object
// @SqlQuery("SELECT * FROM leev WHERE EMP_ID = :empID")
// @Mapper(leevMapper.class)
// List<leev> empHistory(int empId);

//return the ManagerID value
// @SqlQuery("SELECT E1.EMP_ID FROM EMP_DETAILS E1  "
// + " JOIN EMP_DETAILS E2 ON E1.Emp_ID=E2.MGR_ID WHERE E2.EMP_ID =(SELECT EMP_ID FROM LEAVE_DETAILS "
// + "   WHERE LEAVE_ID=:leaveID)")
// int showManager(int leaveId);

//return the total record count.
@SqlQuery("SELECT COUNT(*) FROM leev"
+    " WHERE empId= :empID AND (l_startdate BETWEEN :startDate AND :endDate "
+    " OR l_enddate BETWEEN :startDate AND :endDate)"
)
int count(@Bind("empId")int empId, @Bind("startDate")Date startDate, @Bind("endDate")Date endDate);

// @SqlUpdate("insert into leev values(empId,l_stardate,l_enddate)")
// void apply(@Bind("l_id")int l_id,@Bind("l_type")String l_type,  @Bind("l_status")String l_status,
//   @Bind("l_startDate") Date l_startDate, @Bind("l_endDate") Date l_endDate,
//    @Bind("le_id") int le_id, @Bind("l_Ndays")int l_Ndays,  
//    @Bind("l_Reason")String l_Reason, @Bind("l_AppliedOn") Date l_Appliedon);
    
   @SqlUpdate("insert into leev (l_id,l_type,l_status,l_startDate, l_enddate, le_id, l_ndays, l_reason, l_appliedon)"+
   "values(:l_id,:l_type,:l_status, :l_startDate,:l_endDate,"
   +":le_id,:l_Ndays, :l_Reason,:l_AppliedOn)")
void apply(@Bind("l_id")int l_id,@Bind("l_type") String l_type, @Bind("l_status")String l_status,
  @Bind("l_startDate") Date l_startDate, @Bind("l_endDate") Date l_endDate,
   @Bind("le_id") int le_id, @Bind("l_Ndays")int l_Ndays,  
   @Bind("l_Reason")String l_Reason, @Bind("l_AppliedOn") Date l_Appliedon);
    

@SqlUpdate("UPDATE leev SET l_startdate= :startDate, l_enddate= :endDate,"
    + " l_Ndays = :nodays, l_Type= :leavetype,l_Reason= :leavereason WHERE "
    + "l_id = :leaveid")
void updateLeave(@Bind("l_startdate")Date startDate, @Bind("endDate")Date endDate, @Bind("bal")int bal, @Bind("leaveType")String leaveType, @Bind("leaveStatus")String leaveStatus, @Bind("leaveReason")String leaveReason,
		@Bind("leaveId")int leaveId);


// @SqlQuery("SELECT E1.empId FROM Employee E1  "
//       + " JOIN Employee E2 ON E1.empid=E2.mgr_id WHERE E2.empId =(SELECT le_id FROM leev "
//       + " WHERE l_id=:l_id)")
//    int showManager(@Bind("l_id") int l_id);
   @SqlQuery("SELECT e1.mgr_id FROM employee e1"
          + " JOIN employee e2 ON e2.mgr_id = e1.empId where e1.empId"
          +" = (select le_id from leev where l_id = :l_id) LIMIT 1")
  int showManager(@Bind("l_id")int l_id);

   @SqlQuery("SELECT * FROM leev WHERE le_id = :empId")
   @Mapper(leevMapper.class)
   List<leev> leaveHistory(@Bind("empId") int empId);

   @SqlQuery("SELECT * FROM LEEV ORDER BY L_ID DESC LIMIT 1")
   @Mapper(leevMapper.class)
   leev getLastRow();



}