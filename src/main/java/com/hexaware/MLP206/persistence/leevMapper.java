package com.hexaware.MLP206.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import com.hexaware.MLP206.model.leev;

 public class leevMapper implements ResultSetMapper<leev>
 {

     public final  leev map(final int id, ResultSet rs, StatementContext st) throws SQLException {
         // TODO Auto-generated method stub
        
         return new leev(rs.getInt("l_id"),rs.getString("l_type"),rs.getString("l_des"),rs.getString("l_status"),
         rs.getDate("l_startdate"),rs.getDate("l_enddate"),rs.getInt("le_id"),rs.getInt("l_Ndays"),rs.getString("l_Reason")
         ,rs.getDate("l_AppliedOn"));

     }
     
 }