/**
 * IUStudent
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/6/18
 *
 */

public class IUStudent extends CollegeStudent {
    private double bookFees;
    private double transportationFees;
    private double financialAid;
    
    public IUStudent(String residency, boolean livesOffCampus, double GPA) {
        super(residency, livesOffCampus);
        
        setBookFees(1034);
        gpa2aid(GPA);
    }

    public void setTransportationFees(double transportationFees) { this.transportationFees=transportationFees; }
    public void setBookFees(double bookFees) { this.bookFees=bookFees; }
    public void setFinancialAid(double financialAid) { this.financialAid=financialAid; }
    
    public double getBookFees() { return bookFees; }
    public double getFinancialAid() { return financialAid; }
    public double getTransportationFees() { return transportationFees; }
    
    @Override
    public void setLivingOffCampus(boolean offCampus) {
        super.setLivingOffCampus(offCampus);
        
        if(offCampus) {
            setTransportationFees(500);
            setDormCost(400);
        } else {
            setTransportationFees(100);
            setDormCost(800);
        }
    }
    
    private void gpa2aid(double GPA) {
        if(GPA < 0) {
            throw new IllegalArgumentException("GPA needs to be above or equal to 0");
        }
        if(GPA > 3.75) {
            setFinancialAid(4500);
        } else if(GPA > 3.50) {
            setFinancialAid(3500);
        } else if(GPA > 3.00) {
            setFinancialAid(2750);
        } else if(GPA > 2.50) {
            setFinancialAid(2500);
        } else {
            setFinancialAid(0);
        }
    }
    
    protected double residencyToTuition(Residency residency) {
        switch(residency) {
            case IN_STATE:
                return 10534;
            case OUT_OF_STATE:
                return 34846;
            default:
                throw new IllegalArgumentException("Student must in state or out of state.");
        }
    }
    
    @Override
    public double calculateYearlyCost() {
        double naive = super.calculateYearlyCost();
        return naive + bookFees + transportationFees - financialAid;
    }
    
    @Override
    public String toString() {
        String init = super.toString();
        String other = String.format("Book Fees: %.2f\n" +
                "Transportation Cost: %.2f\n" +
                "Financial Aid: %.2f\n",
                bookFees, transportationFees, financialAid);
        
        return init + other;
    }
}