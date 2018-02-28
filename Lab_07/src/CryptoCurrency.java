public interface CryptoCurrency {
    double getProfit();
    double getInterest();
    void assessProfit();
    void mine(int attempts);
    Demand getDemand();
    void setDemand(Demand d);
    void purchase(int coin);
}