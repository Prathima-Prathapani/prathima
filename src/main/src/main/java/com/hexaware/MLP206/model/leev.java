package com.hexaware.MLP206.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.hexaware.MLP206.persistence.DbConnection;
import com.hexaware.MLP206.persistence.EmployeeDAO;
import com.hexaware.MLP206.persistence.leevDAO;


public class leev
{
     private  int l_id;
     private LeaveType l_type;
    // private static String l_des;
     private  LeaveStatus l_status;
     private  Date l_startdate;
     private  Date l_enddate;
     private  int le_id;
     private int l_Ndays;
     private static String l_Reason;
     private Date l_AppliedOn;

     static int counter = 2;

     public final int getleev_id() {
       return l_id;
     }

     public final LeaveType getType() {
       return l_type;
     }

     // public final String getdes() {
     // return l_des;
     // }

     public final LeaveStatus getStatus() {
       return l_status;
     }

     public final Date getStart_date() {
       return l_startdate;
     }

     public final Date getEnd_date() {
       return l_enddate;
     }

     public final int getleev_empid() {
       return le_id;
     }

     public final int getndays() {
       return l_Ndays;
     }

     public final String getreason() {
       return l_Reason;
     }

     public final Date getappdate() {
       return l_AppliedOn;
     }

     // setter methods
     public final void setleev_id(int leev_id) {
       this.l_id = leev_id;
     }

     public final void setType(LeaveType Type) {
       this.l_type = Type;
     }

     // public final void Setdes(String l_des) {
     // this.l_des = l_des;
     // }

     public final void setStatus(LeaveStatus status) {
       this.l_status = status;
     }

     public final void setstartdate(Date leev_start_date) {
       this.l_startdate = leev_start_date;
     }

     public final void setenddate(Date leev_end_date) {
       this.l_enddate = leev_end_date;
     }

     public final void setemp_id(int leev_empid) {
       this.le_id = leev_empid;
     }

     public final void setlndays(int lee_ndays) {
       this.l_Ndays = lee_ndays;
     }

     public final void setreason(String lee_r) {
       this.l_Reason = lee_r;
     }

     public final void setappdate(Date lee_date) {
       this.l_AppliedOn = lee_date;
     }
     // hashcode

     @Override
     public final int hashCode() {
       return Objects.hash(l_id, le_id, l_type, l_status, l_startdate, l_enddate, l_Ndays, l_Reason, l_AppliedOn);
     }

     // default constructor
     public leev(int i, LeaveType sl, Date date, Date date2, int j, int k, String string, Date date3) {
     }

     @Override
     public final boolean equals(final Object obj) {
       if (obj == null) {
         return false;
       }
       if (getClass() != obj.getClass()) {
         return false;
       }
       leev l = (leev) obj;
       if (Objects.equals(l_id, l.l_id) && Objects.equals(l.l_type, l.l_type) && Objects.equals(l_status, l.l_status)
           && Objects.equals(l_startdate, l.l_startdate) && Objects.equals(l_enddate, l.l_enddate)
           && Objects.equals(le_id, l.le_id) && Objects.equals(l_Ndays, l.l_Ndays)
           && Objects.equals(l_Reason, l.l_Reason) && Objects.equals(l_AppliedOn, l.l_AppliedOn)) {
         return true;
       }
       return false;
     }

     public leev(int i) {

     }

     /**
      * @param string
      * @param argEmpId to initialize employee id.
      */
     public leev(final int l_id, final LeaveType l_type, final LeaveStatus l_status, final Date leev_start_dDate,
         final Date leev_endDate, final int leev_emp_id, final int ln, final String r, final Date ld) {
       this.l_id = l_id;
       this.l_type = l_type;
       this.l_status = l_status;
       this.l_startdate = leev_start_dDate;
       this.l_enddate = leev_endDate;
       this.le_id = leev_emp_id;
       this.l_Ndays = ln;
       this.l_Reason = r;
       this.l_AppliedOn = ld;
     }

     public leev() {
     }

     @Override
     public String toString() {
       return " leave Id: " + l_id + "leave status: " + l_status + "leave startdate: " + l_startdate + "leave enddate: "
           + l_enddate + "leave employee_id: " + le_id + l_Ndays + l_Reason + l_AppliedOn;
     }

     public static String approveDeny(final int l_id, final int empId, final LeaveStatus l_status) {
       //extracting the leave object for a particular leave id
       leev ld = leev.listById(l_id);

       String res = null;
       
       if (ld != null) {
         int noOfDays = ld.getndays();
         int emplId = ld.getleev_empid();
         Employee emp = Employee.listById(emplId);
      //   System.out.println("Leave Employee Id: " + emplId);
         int empdId = leev.showManager(l_id);
     //    System.out.println("MANAGER: "+empdId);

         String dbStatus = null;
         System.out.println(l_status);
         System.out.println(ld.getStatus());
         if (emp.getmgr_id() != empdId) {
           res = "You are not authorised to access this employee.";
           return res;
         }
         if (l_status.equals(LeaveStatus.APPROVED) && ld.getStatus().equals(LeaveStatus.PENDING)) {
           dbStatus = "APPROVED";
           res = "Leave Approved Successfully";
           edao().decrement(emplId, noOfDays);
           dao().comment( dbStatus, l_id);

         } else if (l_status.equals(LeaveStatus.REJECTED) && ld.getStatus().equals(LeaveStatus.APPROVED)) {
           dbStatus = "REJECTED";
           edao().increment(emplId, noOfDays);

           res = "Leave Rejected";
         } else if (l_status.equals(LeaveStatus.APPROVED) && ld.getStatus().equals(LeaveStatus.REJECTED)) {
           dbStatus = "APPROVED";
           edao().decrement(emplId, noOfDays);
           res = "Leave Approved Successfully";
           
         } else {
           if (l_status.equals(LeaveStatus.REJECTED) && ld.getStatus().equals(LeaveStatus.PENDING)) {
             dbStatus = "REJECTED";
             edao().increment(emplId, noOfDays);
             res = "Leave Rejected";
           } else {
             res = "Already on given status";
           }
         }
       } else {
         res = "Invalid LeaveId";
       }
       return res;
     }

     public static leev[] leaveHistory(int empId) {
       List<leev> es = dao().leaveHistory(empId);
       return (es.toArray(new leev[es.size()]));
     }

     public static String applyLeave(final int empId, final String startDate, final String endDate, final int noOfdays,
         final String leaveType, final String leaveReason) throws ParseException {
       String s = null;
          int leaveId;
       Employee emplo = Employee.listById(empId);
       // LeaveType lt = LeaveType.valueOf(leaveType);
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date l_startdate = sdf.parse(startDate);
       Date l_enddate = sdf.parse(endDate);

       if (emplo != null) {

         Calendar start = Calendar.getInstance();
         start.setTime(l_startdate);
         Calendar end = Calendar.getInstance();
         end.setTime(l_enddate);
         int count = 0;
         for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
           Calendar c = Calendar.getInstance();
           c.setTime(date);
           int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

           if (dayOfWeek == 1 || dayOfWeek == 7) {
             count++;
           }
         }

         //getting the last row of leaves table
         leev lastRow = dao().getLastRow();
         leaveId = lastRow.getleev_id() + 1;
         //System.out.println("LeaveId:" + leaveId);
        // System.out.println(count);

         long diff = l_enddate.getTime() - l_startdate.getTime();
         System.out.println(diff);

         long days = diff / (1000 * 60 * 60 * 24);
         Date today = new Date();
         String appliedOn = sdf.format(today);
         Date appliedDate = sdf.parse(appliedOn);
         days = days + 1;

         long availBal = 0;
         long dif = 0;
         long updLeave = 0;
         String leaveStatus = null;

         // int overl = leev.countNo(empId, stDate, enDate);
         availBal = emplo.getavail_l();
         dif = availBal - days;
         updLeave = days - count;
         int bal = (int) updLeave;

         if (days <= 0) {
           s = "Start Date Must be Greater than EndDate...";
         } else if (dif < 0) {
           s = "insufficient leav balance";
         } else if (noOfdays != days) {
           s = "NO Of Days Should be right";
         } else if (l_startdate.compareTo(sdf.parse(appliedOn)) < 0) {
           s = "Start should be greater or equal to current date";
           // } else if (overl > 0) {
           // s = "already applied on given date";
         } else {
           if (emplo.getmgr_id() == 0) {
             leaveStatus = "APPROVED";
             dao().apply(leaveId, leaveType, leaveStatus, l_startdate, l_enddate, emplo.gete_id(), bal,l_Reason,
                 appliedDate);
             s = "Leave Applied Successfully...";
           } else {
             leaveStatus = "PENDING";
             dao().apply(leaveId, leaveType, leaveStatus, l_startdate, l_enddate, emplo.gete_id(), bal, l_Reason,
                 appliedDate);
          //edao().decrement(empId, bal);
          s = "Leave Applied Successfully For "  + (days - count) + " Days.";
        }
      }
    } else {
      s = "invalid employee";
  }
  return s;
}


// if (ld != null) {
//   int noOfDays = ld.getndays();
//   int emplId = ld.getleev_empid();
//   Employee emp = Employee.listById(emplId);
// //   System.out.println("Leave Employee Id: " + emplId);
//   int empdId = leev.showManager(l_id);
// //    System.out.println("MANAGER: "+empdId);

//   String dbStatus = null;
//   System.out.println(l_status);
//   System.out.println(ld.getStatus());
//   if (emp.getmgr_id() != empdId) {
//     res = "You are not authorised to access this employee.";

public static String updateLeave(final int empId,
  final String startDate,
   final String endDate,
   final int noOfday,
   final String leaveType,
   final String leaveReason,
   final int leaveId
   ) throws ParseException {
     String s = null;
     Employee employ = Employee.listById(empId);
     leev ld = leev.listById(leaveId);
     int prevDays = ld.getndays();
     if (employ != null) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date strDate = sdf.parse(startDate);
       Date endsDate = sdf.parse(endDate);
       Calendar start = Calendar.getInstance();
       start.setTime(strDate);
       Calendar end = Calendar.getInstance();
       end.setTime(endsDate);
       int counts = 0;
       for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
         Calendar c = Calendar.getInstance();
         c.setTime(date);
         int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
         if (dayOfWeek == 1 || dayOfWeek == 7) {
           counts++;
         }
       }
       System.out.println(counts);
       long diffr = endsDate.getTime() - strDate.getTime();
       System.out.println(diffr);
       long day = diffr / (1000 * 60 * 60 * 24);
       Date today = new Date();
       String appliedOn = sdf.format(today);
       day = day + 1;
       long availBalance = 0;
       long diff = 0;
       long updLeave = 0;
       String leaveStatus = null;
       SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");
       Date l_startdate = sdf1.parse(startDate);
       Date l_enddate = sdf1.parse(endDate);
       //int overl = ld.countNo(empId, startDate, endDate);
       availBalance = employ.getavail_l();
       diff = availBalance - day;
       updLeave = day - counts;
       int bal = (int) updLeave;
       if (empId != leev.listById(leaveId).getleev_id()) {
         s = "You are not authorised to update this leave.";
       } else if (day <= 0) {
         s = "Start Date Must be Greater than EndDate...";
       } else if (diff < 0) {
         s = "insufficient leav balance";
       } else if (noOfday != day) {
         s = "NO Of day Should be right";
       } else if (strDate.compareTo(sdf.parse(appliedOn)) < 0) {
         s = "Start should be greater or equal to current date";
       } else {
         if (employ.getmgr_id() == 0) {
           leaveStatus = "APPROVED";
           dao().updateLeave(l_startdate, l_enddate, bal,
               leaveType, leaveStatus, leaveReason, leaveId);
           s = "Leave updated Successfully...";
          } else {
           leaveStatus = "PENDING";
           dao().updateLeave(l_startdate, l_enddate, bal,
               leaveType, leaveStatus, leaveReason, leaveId);
           if (bal - prevDays > 0) {
             bal = bal - prevDays;
             edao().decrement(empId, bal);
           } else if (bal - prevDays < 0) {
             bal = prevDays - bal;
             edao().increment(empId, bal);
           } else {
             bal = bal - prevDays;
             edao().decrement(empId, bal);
           }
           s = "Leave updated Successfully For "  + (day - counts) + " day.";
         }
       }
     } else {
       s = "invalid employee";
     }
     return s;
   }
 private static int showManager(int l_id2) {
   return dao().showManager(l_id2);
 }

 // dao for LeaveDetails.
private static leevDAO dao() {
 DbConnection db = new DbConnection();
 return db.getConnect().onDemand(leevDAO.class);
}

//dao for emp
private static EmployeeDAO edao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

//list leev details
  public static leev[] listAll() {

    List<leev> es = dao().list();
    return es.toArray(new leev[es.size()]);
  }
  //list emp leave details for manager
  public static leev[] listPending(final int mgrId) {

    List<leev> e = dao().pending(mgrId);
    return e.toArray(new leev[e.size()]);
  }
  //list employee details by id(leave id)
  public static leev listById(final int leaveId) {
    return dao().find(leaveId);
  }


//to get the emp details using empid
  // public static leev[] leaveHis(final int empId) {
  //   List<leev> es = dao().leaveHistory(empId);
  //   return es.toArray(new leev[es.size()]);
  // }
  //returns ManagerId for LeaveId value
  // public static int showManager(final int leaveId) {
  //   return dao().showManager(leaveId);
  // }
   //count no of days for applied leaves
  //  public static int countNo(final int e_id, final Date startDate, final Date endDate) {
  //   int count = dao().count(e_id, startDate, endDate);
  //   return count;
  // }

  


}