//NOTE: You will be using the random package for probability
import java.util.Random;
import java.lang.Math;

public class XerosCoin implements CryptoCurrency {
    private Demand demand;
    private double interest;
    private double profit;
    private int muns;
    private double worth;
    
    public XerosCoin(Demand demand, int muns){
        this.demand = demand;
        this.muns = muns;
    
        profit = 0;
        interest = 0;
        worth = .75;
    }
    
    @Override
    public double getProfit() {
        return profit;
    }
    
    @Override
    public double getInterest() {
        switch(demand) {
            case HIGH:
                interest = 0.5;
                break;
            case ABOVE_AVERAGE:
                interest = 0.025;
                break;
            case AVERAGE:
                interest = 0;
                break;
            case BELOW_AVERAGE:
                interest = -0.025;
                break;
            case LOW:
                interest = -0.5;
                break;
        }
        return interest;
    }
    
    @Override
    public void assessProfit() {
        double A = worth * Math.pow(1.0+getInterest()/3, 3);
        profit += A * muns;
    }
    
    @Override
    public void mine(int attempts) {
        Random rand = new Random();
        for(int i=0; i<attempts; ++i) {
            //1% chance
            if(rand.nextInt(100) < 4) ++muns;
        }
    }
    
    @Override
    public Demand getDemand() {
        return demand;
    }
    
    @Override
    public void setDemand(Demand d) {
        demand = d;
    }
    
    @Override
    public void purchase(int coin) {
        profit -= coin*worth;
        muns += coin;
    }
}