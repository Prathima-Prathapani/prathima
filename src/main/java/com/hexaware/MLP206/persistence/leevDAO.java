package com.hexaware.MLP206.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

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
  @SqlUpdate("UPDATE leev SET COMMENTS = :leavemanagercomm, LEAVE_STATUS = :leavestatus WHERE "
      + "l_id= :leaveid")
void comment(String mgrComments, String dbStatus, int l_id);

//return the Leavedetails object
@SqlQuery("SELECT * FROM leev WHERE "
+ " empId IN "
+ " (SELECT E2.empId FROM "
+ " employee E1 INNER JOIN "
+ " employee  E2 ON E1.empId = E2.mgr_id "
+ " WHERE E1.empId = :empid)  AND "
+ " l_status ='PENDING' ")
@Mapper(leevMapper.class)
List<leev> pending(int mgrId);

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
@SqlQuery("SELECT COUNT(*) FROM LEAVE_DETAILS "
+    " WHERE EMP_ID= :empID AND (START_DATE BETWEEN :startDate AND :endDate "
+    " OR END_DATE BETWEEN :startDate AND :endDate)"
)
int count(int empId, String startDate, String endDate);


@SqlUpdate("insert into leev  "+"(EMP_ID, "+"START_DATE, "+"END_DATE, "+"NO_OF_DAYS, "+"LEAVE_TYPE, "
 +"LEAVE_STATUS, "+"LEAVE_REASON, "+"APPLIED_ON) "+"values( "+":empId, "+":startDate, "+":endDate, "+":nodays, "+":leavetype, "
+":leavestatus, "+":leavereason, "+":appliedOn)")
void apply(int empId, String startDate, String endDate, int bal, String leaveType, String leaveStatus,
    String leaveReason, String appliedOn);
    

@SqlUpdate("UPDATE leev SET START_DATE = :startDate, END_DATE = :endDate,"
    + " NO_OF_DAYS = :nodays, LEAVE_TYPE= :leavetype,LEAVE_REASON= :leavereason WHERE "
    + "LEAVE_ID = :leaveid")
void updateLeave(String startDate, String endDate, int bal, String leaveType, String leaveStatus, String leaveReason,
		int leaveId);


@SqlQuery("SELECT E1.EMP_ID FROM EMP_DETAILS E1  "
      + " JOIN EMP_DETAILS E2 ON E1.Emp_ID=E2.MGR_ID WHERE E2.EMP_ID =(SELECT EMP_ID FROM LEAVE_DETAILS "
      + "   WHERE LEAVE_ID=:leaveID)")
   int showManager(@Bind("leaveID") int leaveID);

   @SqlQuery("SELECT * FROM leev WHERE le_id = :empId")
   @Mapper(leevMapper.class)
   List<leev> leaveHistory(@Bind("empId") int empId);



}