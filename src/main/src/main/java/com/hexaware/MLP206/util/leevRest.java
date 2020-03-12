package com.hexaware.MLP206.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.MLP206.model.leev;

@Path("/cars")
public class leevRest {
    /**
     * Retrieve the list from the DB
     * 
     * @return list of cars
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public final leev[] listAll() {
        final leev[] carList = leev.listAll();
        return carList;
    }
    // void apply(@Bind("l_id")int l_id, @Bind("l_type") String l_type,
    // @Bind("l_status")String l_status,
    // @Bind("l_startDate") Date l_startDate, @Bind("l_endDate") Date l_endDate,
    // @Bind("le_id") int le_id, @Bind("l_Ndays")int l_Ndays,
    // @Bind("l_Reason")String l_Reason, @Bind("l_AppliedOn") Date l_Appliedon);

    // public static String applyLeave(final int empId, final String startDate,
    // final String endDate, final int noOfdays,
    // final String leaveType, final String leaveReason)

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/applyLeave")
    public final String applyLeave(final leev lv) throws ParseException 
    {
        String dateString = null;
        String dateString1=null;
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
        dateString = sdfr.format(lv.getStart_date());
        dateString1 = sdfr.format(lv.getEnd_date());

        String comment = "";
        final String result = leev.applyLeave(lv.getleev_empid(), dateString, dateString1,lv.getndays(),lv.getType(),lv.getreason());   
        if(result.equals("Leave Applied Successfully...")){
          comment = "{\" value \" : \" leave added successfully \"}";
        }else{
          comment = "{\" value \" : \" leave not added  \"}";
        }
        return comment;
    }
  
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateCar")
    public final String updateLeave(final leev lev) 
    {
        String comment = "";
        final int result = leev.updateLeave(lev);
        if(result > 0){
          comment = "{\" value \" : \" Car updated successfully \"}";
        }else{
          comment = "{\" value \" : \" Car not updated  \"}";
        }
        return comment;
    }
  
    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // @Path("/showCar/{id}")
    // public final Cars showCar(@PathParam("id") final int carId) {
    //   final Cars car = Cars.find(carId);
    //   return car;
    // }


}
