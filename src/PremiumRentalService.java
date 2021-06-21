package src;


public class PremiumRentalService extends BaseRentalService
{
  public static final double PERMIUM_ADDITIONAL_CHARGES = 20; // daily
  public double bonus;
  
  @Override
  public double calculateFee(){
    double fee = super.calculateFee();

    double extra = PERMIUM_ADDITIONAL_CHARGES * this.rental_duration;
    fee += extra;

    if(this.bonus == 3){
      fee *= 0.5;
      this.bonus = 0;
    }

    return fee;
  }
}
