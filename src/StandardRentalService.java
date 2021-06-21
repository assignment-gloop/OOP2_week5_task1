package src;


public class StandardRentalService extends BaseRentalService
{
  public static final double EXCESS_RATE_PER_MILE = 1;
  public double mileage_limit; // daily

  @Override
  public double calculateFee(){
    double fee = super.calculateFee();

    // excess mileage
    double excess = this.total_mileage_travelled -
        this.rental_duration * this.mileage_limit;
    if(excess > 0){
      fee += EXCESS_RATE_PER_MILE * excess;
    }

    return fee;
  }
}
