package com.hexaware.MLP206.util;

import java.util.Scanner;

import com.hexaware.MLP206.model.Employee;
import com.hexaware.MLP206.model.leev;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. approvedeny");
    System.out.println("Enter your choice:");
    int menuOption = option.nextInt();
    mainMenuDetails(menuOption);
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
         approveDeny();
         break;
      case 4:
          leaveHistory();
          break;
      case 5:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      
      default:
        System.out.println("Choose either 1, 2 or 3");
    }
    mainMenu();
  }
  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      System.out.println(employee.getEmpId()+employee.getname()+employee.getdes()+employee.getmgr_id()+employee.getphno()+employee.getmail());
    }
  }
  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) {
      System.out.println(e.getEmpId()+e.getname()+e.getdes()+e.getmgr_id()+e.getphno()+e.getmail());
    }
  }
  /**
   * The main entry point.
   * @param ar the list of arguments
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
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
        Employee.listById(empid);
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

  
public void login() {
}
}
