package src;

import java.util.*;
import java.io.IOException;
import java.nio.file.*; // because it's good


public class Main
{
  static List<BaseRentalService> plans = new LinkedList<BaseRentalService> ();

  private static void fillData(){
    VanRentalService serv1 = new VanRentalService();
    serv1.customer_ID = "300028";
    serv1.car_ID = "1001";
    serv1.customer_name = "Kevin lee";
    serv1.base_rental_fee = 10;
    serv1.insured = true;
    serv1.rental_duration = 10;

    plans.add(serv1);

    StandardRentalService serv2 = new StandardRentalService();
    serv2.customer_ID = "200127";
    serv2.car_ID = "2001";
    serv2.customer_name = "Robert Felming";
    serv2.base_rental_fee = 30;
    serv2.mileage_limit = 90;
    serv2.rental_duration = 12;
    serv2.total_mileage_travelled = 1200;

    plans.add(serv2);

    
    PremiumRentalService serv3 = new PremiumRentalService();
    serv3.customer_ID = "100052";
    serv3.car_ID = "3001";
    serv3.customer_name = "Sara Davi";
    serv3.base_rental_fee = 35;
    serv3.bonus = 3;
    serv3.rental_duration = 5;

    plans.add(serv3);
  }

  
  static enum ReportMode
  {
    CONSOLE,
    FILE
  }
  private static void generateReports(ReportMode mode){
    plans.sort((a, b) -> {
      return a.customer_ID.compareTo(b.customer_ID);
    });

    StringBuilder output = new StringBuilder();

    String sep = String.format("+%s+%s+%s+\n",
        "-".repeat(15),"-".repeat(20), "-".repeat(20)
    );
    String format = "| %-13s | %-18s | %-18s |\n";

    output.append(sep);
    output.append(String.format(format,
        "Customer ID", "Customer Name", "Total Rental Fee"
    ));
    output.append(sep);

    for(BaseRentalService serv : plans){
      output.append(String.format(format,
          serv.customer_ID,
          serv.customer_name,
          Double.toString(serv.calculateFee()) + " \u00A3" 
      ));
      output.append(sep);
      
    }
    

    switch(mode){
    case CONSOLE:
      System.out.println(output);
      break;
    case FILE:
      try{
        Files.createDirectories(Paths.get("C:/tmp"));
        java.nio.file.Files.writeString(
            Paths.get("C:/tmp", "employeeReport.txt"),
            output,
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING
        );
        System.out.println("Reports saved at C:/tmp/employeeReport.txt");
      } catch(IOException ioe){
        System.err.println("error when writing to output file");
        ioe.printStackTrace();
      }
      break;
    }
  }

  public static void main(String args[]){
    fillData();

    System.out.println("Please enter your selection:");
    System.out.println("1. Display car rental report");
    System.out.println("2. Save car rental report to a file");
    System.out.print("> ");

    try(Scanner sc = new Scanner(System.in)){
      ReportMode mode = sc.nextInt() == 2 ?
          ReportMode.FILE : ReportMode.CONSOLE;
      
      System.out.println();

      generateReports(mode);
    }
  }
}
