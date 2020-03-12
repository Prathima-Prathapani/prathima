package com.hexaware.MLP206.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import com.hexaware.MLP206.model.LeaveStatus;
import com.hexaware.MLP206.model.LeaveType;
import com.hexaware.MLP206.model.leev;

 public class leevMapper implements ResultSetMapper<leev>
 {


     public final  leev map(final int id, ResultSet rs, StatementContext st) throws SQLException {
         // TODO Auto-generated method stub
        
         String leave_type = rs.getString("l_type");
        String l_status = rs.getString("l_status");
        LeaveType LeaveType= com.hexaware.MLP206.model.LeaveType.valueOf(leave_type);
        LeaveStatus LeaveStatus = com.hexaware.MLP206.model.LeaveStatus.valueOf(l_status);
        
         return new leev(rs.getInt("l_id"), LeaveType, LeaveStatus, rs.getDate("l_startdate"), rs.getDate("l_enddate"),
                 rs.getInt("le_id"), rs.getInt("l_Ndays"), rs.getString("l_type"),
         rs.getDate("l_AppliedOn"));

     }
     
 }