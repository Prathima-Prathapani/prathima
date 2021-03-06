package com.hexaware.MLP206.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.hexaware.MLP206.model.Employee;
import com.hexaware.MLP206.model.LeaveStatus;
import com.hexaware.MLP206.model.LeaveType;
import com.hexaware.MLP206.model.leev;


/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain
{
  private Scanner option = new Scanner(System.in, "UTF-8");
  private String empId;
  
  // Menu

  
  private void mainMenu()
  {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. Emloyees Menu");
    System.out.println("2. Leaves Menu");
    System.out.println("3. Exit");

    System.out.println("Enter your choice:");
    int ch = option.nextInt();


    // Menu for Employee list
    switch(ch)
    {
      case 1:
      System.out.println("1. List All Employees Info");
      System.out.println("2. List Employee Info"); 
      System.out.println("3. Back to main menu");
      System.out.println("Enter your choice:");
      int menuOption = option.nextInt(); 
      mainMenuDetails(menuOption);
      break;


     // Menu for Leave List
      case 2:
      System.out.println("4.Apply for Leaves");
      System.out.println("5.Check leave status");
      System.out.println("6.Edit Leaves");
      System.out.println("7.Cancel Leaves");
      System.out.println("8.Check Leaves Available");
      System.out.println("9.Leave History");
      System.out.println("10.Update Leaves");
      System.out.println("11.Back to Main menu");
      System.out.println("Enter your choice:");
      int menuOption1 = option.nextInt();
      mainMenuDetails(menuOption1);
      break;


     //Out of the Menu list
      case 3:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        option.close();
        Runtime.getRuntime().halt(0);
    }
  

  }
   private void mainMenuDetails(final int selectedOption) {
    switch (selectedOption) {
      case 1:
        listEmployeesDetails();
        break;
      case 2:
        listEmployeeDetail();
        break;
      case 3:
        mainMenu(); 
        break;
      case 4:
        approveDeny();
        break;
      case 5:
        leaveHistory();
        break;
        case 6:
         applyLeave();
         break;
         case 7:
         updateLeaves();
         break;
      default:
        System.out.println("sorry you cannot Enjoy"); 
    }
     mainMenu();
  }

  //Login Method

  public void login(){
     System.out.println("Enter user_id: ");
     Scanner sc = new Scanner (System.in);
     int id = sc.nextInt();

     System.out.println("Enter Password: ");
     String password = sc.next();
     if((id==100) && password.equals("dawar")){
       System.out.println("login sucessful");
               mainMenu();
     }else{
      System.out.println("something is wrong!!!");
     }
  }

   // List of Employee info
    private void listEmployeeDetail()
 {
    System.out.println("Enter an Employee Id");
    int empId= option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) 
    { 
      System.out.println("Sorry, No such employee");
    } else
   {
      System.out.println(employee.gete_id()+ " "+employee.gete_name()+" "+ employee.gete_add()+" "+ employee.getmgr_id()+" "+ employee.gete_phno()+" " + employee.gete_mail()+" "+ employee.getDOJ()+ " "+employee.getavail_l() );
    }
  }

   //List of Employees Details
  private void listEmployeesDetails() 
  {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) 
    {
      System.out.println(e.gete_id()+ " " +e.gete_name()+ " " +e.gete_des()+" " + e.getmgr_id()+" " +e.gete_add()+" " +e.gete_phno()+" "+e.gete_mail()+" "+e.getDOJ()+" "+e.getavail_l());
    }
  }
  /**
   * The main entry point.
   * @param ar the list of arguments
   */
    public static void main(final String[] ar)
  {
    final CliMain mainObj = new CliMain();
            mainObj.login();
  


  } 
  public static void approveDeny() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a leave ID:");
    int leaveId = sc.nextInt();
    System.out.println("Enter Your Id");
    int mgrId = sc.nextInt();
    System.out.println("Decide what you want to do (APPROVED / REJECTED): ");
    String ch = sc.next();
    LeaveStatus leave_status = LeaveStatus.valueOf(ch.toUpperCase());
    String res = leev.approveDeny(leaveId, mgrId, leave_status);
    System.out.println(res);
  }

  
  // private void update() throws ParseException {
  //   Scanner sc=new Scanner(System.in);
  //    System.out.println("Enter your id");
  //    int empId=sc.nextInt();
  //    System.out.println("Enter new Start date");
  //    String startdate=sc.nextLine();
  //    System.out.println("Enter new end date");
  //    String enddate=sc.nextLine();
    
  //   //  int mgr_id=sc.nextInt();
  //   //  System.out.println("Enter number of days of leave");
  //    int noOfday=sc.nextInt();
   
  //   //  String l_AppliedOn=sc.nextLine();
  //   //   System.out.println("Leave updated!!!!");
  //     String leaveType=sc.next();
  //     String leaveReason=sc.next();
  //     int leaveId=sc.nextInt();
  //    String  res= updateLeave(empId, startdate, enddate, noOfday, leaveType, leaveReason, leaveId);
 
  //  }
  private void updateLeaves() {
    int leaveid = 0;
    int empid = 0;
    while (true) {
      System.out.println("Enter an employee Id");
      String empId = option.next();
      System.out.println("Enter an Leave Id");
      String leaveId = option.next();
      try {
        empid = Integer.parseInt(empId);
        leaveid = Integer.parseInt(leaveId);
        leev.listById(leaveid).getleev_empid();
        System.out.println(leev.listById(leaveid).getleev_empid());
        break;
      } catch (NumberFormatException nf) {
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("-----Please enter correct employee id------");
      } catch (NullPointerException x) {
        System.out.println();
        System.out.println("--------------No Such Employee---------------");
        System.out.println("---------------------------------------------");
        System.out.println("-----Please enter correct employee id--------");
      }
    }
    System.out.println();
    System.out.println("Your available leave balance is: " + Employee.listById(empid).getavail_l());
    String eDate = null;
    String sDate = null;
    while (true) {
      try {
        System.out.println("Enter Start Date of your leave (yyyy-MM-dd): ");
        sDate = option.next();
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        sdfrmt.parse(sDate);
        System.out.println("Enter End Date of your leave (yyyy-MM-dd): ");
        eDate = option.next();
        SimpleDateFormat sdfrm = new SimpleDateFormat("yyyy-MM-dd");
        sdfrm.setLenient(false);
        sdfrm.parse(eDate);
        break;
      } catch (ParseException exp) {
        System.out.println("------------------------------");
        System.out.println("Please enter in correct date/format.");

      }
    }
    System.out.println("Enter the number of days you want leave for: ");
    int ndays = option.nextInt();
    System.out.println("Enter the type of leave you want. (EL / SL / ML): ");
    String lType = option.next();
    System.out.println("Please mention the reason for taking leave: ");
    String lReason = option.next();
    String r = null;
    try {
      r = leev.updateLeave(empid, sDate,
                    eDate, ndays, lType.toUpperCase(),
                    lReason, leaveid
      );
    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(r);
  }


 private void leaveHistory() {
    int empid = 0;
    while (true) {
      System.out.println("Enter an Employee Id");
      String empId = option.next();
      try {
        empid = Integer.parseInt(empId);
        //Employee emp= Employee.listById(empid);
         leev[] leaves = leev.leaveHistory(empid);
        if (leaves.length == 0) {
          System.out.println("Sorry, No such employee History..");
        } else {
          System.out.println();
          for (leev e : leaves) {
            System.out.println(e.getleev_id()+ " | " + e.getleev_empid()+ " | "
                + e.getStart_date()+ " | " + e.getEnd_date()+ " | " + e.getndays()+ " | "
                + e.getType()+ " | " + e.getStatus()+ " | " + e.getreason()
                + e.getappdate() );
          }
        }
       
       // break;
      } catch (NumberFormatException nfe) {
        System.out.println("-------------------------------------------");
        System.out.println("-----please enter correct employee id------");
        System.out.println("-------------------------------------------");
        listEmployeeDetail();
      } catch (NullPointerException e) {
        System.out.println();
        System.out.println("--------------No Such Employee---------------");
        System.out.println("---------------------------------------------");
        System.out.println("-----please enter correct employee id--------");
        System.out.println("---------------------------------------------");
        listEmployeeDetail();
      }
    }
  }
  private void applyLeave() {
    int empid = 0;
   // int li_id=0;
    while (true) {
      System.out.println("Enter an Employee Id");
      String empId = option.next();
      // System.out.println("enter leave_id");
      // li_id=option.nextInt();

      try {
        empid = Integer.parseInt(empId);
        Employee.listById(empid).gete_id();
        break;
      } catch (NumberFormatException nfe) {
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("-----Please enter correct employee id------");
        System.out.println("-------------------------------------------");
      } catch (NullPointerException e) {
        System.out.println();
        System.out.println("--------------No Such Employee---------------");
        System.out.println("---------------------------------------------");
        System.out.println("-----Please enter correct employee id--------");
        System.out.println("---------------------------------------------");
      }
    }
    System.out.println();
    System.out.println("Your available leave balance is: " + Employee.listById(empid).getavail_l(empid));
    String endDate = null;
    String startDate = null;
    while (true) {
      try {
        System.out.println("Enter Start Date of your leave (yyyy-MM-dd): ");
        startDate = option.next();
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        sdfrmt.parse(startDate);
        System.out.println("Enter End Date of your leave (yyyy-MM-dd): ");
        endDate = option.next();
        SimpleDateFormat sdfrm = new SimpleDateFormat("yyyy-MM-dd");
        sdfrm.setLenient(false);
        sdfrm.parse(endDate);
        break;
      } catch (ParseException ex) {
        System.out.println("------------------------------");
        System.out.println("Please enter in correct date/format.");
        System.out.println("------------------------------");
      }
    }
    System.out.println("Enter the number of days you want leave for: ");
    int noOfdays = option.nextInt();
    System.out.println("Enter the type of leave you want. (EL / SL / ML): ");
    String leaveType = option.next();
    System.out.println("Please mention the reason for taking leave: ");
    String leaveReason = option.next();
    String res = null;
    try {
      
      res = leev.applyLeave(empid, startDate, endDate, noOfdays, leaveType.toUpperCase(), leaveReason);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(res);
  }



}  
  

