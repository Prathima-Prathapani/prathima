package com.hexaware.MLP206.util;

import java.util.Scanner;

import com.hexaware.MLP206.model.Employee;
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
    System.out.println("Enter Employ Id  ");
    int mgrId = sc.nextInt();
    System.out.println("Decide what you want to do (APPROVE / DENY): ");
    String ch = sc.next();
    String res = leev.approveDeny(leaveId, mgrId, ch);
    System.out.println(res);
    sc.close();
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


}  
  


    

    
  


