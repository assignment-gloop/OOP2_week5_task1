package src;


public class BaseRentalService
{
  /*
  in theory, these variable should be set to protected

  the usual
  "set to private then adding PUBLIC setter and getter without restriction"
  way in most of these Java beginner courses is nothing more useful than
  just setting these to 'public'
  */
  public String car_ID;
  public double base_rental_fee;
  public String customer_ID;
  public String customer_name;
  public double rental_duration; // unit is day
  public double total_mileage_travelled;

  // it should be renamed to calculateBaseRentalFee but whatever
  public double calculateFee(){
    double fee = 0;

    double weeks = Math.floor(this.rental_duration / 7);
    double remaining_days = this.rental_duration - weeks * 7;
    fee += this.base_rental_fee * weeks * 7 * 0.9;
    fee += this.base_rental_fee * remaining_days;

    // System.out.println("base: " + fee);

    return fee;
  }
}