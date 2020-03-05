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

  @SqlQuery("SELECT * FROM leev WHERE l_id = :leaveid")
  @Mapper(leevMapper.class)
  leev find(@Bind("leaveid") int leaveid);

  //leavemanagercomm Updated Manager Response data.
//   @SqlUpdate("UPDATE leev SET COMMENTS = :leavemanagercomm, LEAVE_STATUS = :leavestatus WHERE "
//       + "l_id= :leaveid")
// void comment(String mgrComments, String dbStatus, int l_id);

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
    
   @SqlUpdate("insert into leev values(:l_id,:l_type,:l_status, :l_startDate,:l_endDate,"
   +":le_id,:l_Ndays, :l_Reason,:l_AppliedOn)")
void apply(@Bind("l_id")int l_id,  @Bind("l_type") String l_type, @Bind("l_status")String l_status,
  @Bind("l_startDate") Date l_startDate, @Bind("l_endDate") Date l_endDate,
   @Bind("le_id") int le_id, @Bind("l_Ndays")int l_Ndays,  
   @Bind("l_Reason")String l_Reason, @Bind("l_AppliedOn") Date l_Appliedon);
    

@SqlUpdate("UPDATE leev SET START_DATE = :startDate, END_DATE = :endDate,"
    + " NO_OF_DAYS = :nodays, LEAVE_TYPE= :leavetype,LEAVE_REASON= :leavereason WHERE "
    + "LEAVE_ID = :leaveid")
void updateLeave(@Bind("startDate")Date startDate, @Bind("endDate")Date endDate, @Bind("bal")int bal, @Bind("leaveType")String leaveType, @Bind("leaveStatus")String leaveStatus, @Bind("leaveReason")String leaveReason,
		@Bind("leaveId")int leaveId);


@SqlQuery("SELECT E1.EMP_ID FROM EMP_DETAILS E1  "
      + " JOIN EMP_DETAILS E2 ON E1.Emp_ID=E2.MGR_ID WHERE E2.EMP_ID =(SELECT EMP_ID FROM LEAVE_DETAILS "
      + "   WHERE LEAVE_ID=:leaveID)")
   int showManager(@Bind("leaveID") int leaveID);
   

   @SqlQuery("SELECT * FROM leev WHERE le_id = :empId")
   @Mapper(leevMapper.class)
   List<leev> leaveHistory(@Bind("empId") int empId);



}