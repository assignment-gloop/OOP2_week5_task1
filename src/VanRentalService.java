package src;



public class VanRentalService extends BaseRentalService
{
  public static final double INSURANCE_COST_PER_DAY = 5;
  public boolean insured = false;

  @Override
  public double calculateFee(){
    double fee = super.calculateFee();

    if(insured){
      double insurance = this.rental_duration * INSURANCE_COST_PER_DAY;
      fee += insurance;
    }

    return fee;
  }
}
