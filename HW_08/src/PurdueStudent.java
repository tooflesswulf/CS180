/**
 * PurdueStudent
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/8/18
 *
 */

public class PurdueStudent extends CollegeStudent{
    private double bookFees;
    private double financialAid;
    private String major;
    
    public PurdueStudent(String residency, String major, boolean livesOffCampus, double GPA) {
        super(residency, livesOffCampus);
        
        setMajor(major);
        gpa2aid(GPA);
    }
    
    public void setBookFees(double bookFees) { this.bookFees=bookFees; }
    public void setFinancialAid(double financialAid) { this.financialAid=financialAid; }
    public void setMajor(String major) {
        this.major = major;
        bookFees = major2book(major);
    }
    
    public double getBookFees() { return bookFees; }
    public double getFinancialAid() { return financialAid; }
    public String getMajor() { return major; }
    
    protected double residencyToTuition(Residency residency) {
        switch(residency) {
            case IN_STATE:
                return 9992;
            case OUT_OF_STATE:
                return 28794;
            case INTERNATIONAL:
                return 30954;
            default:
                throw new IllegalArgumentException("Student residency must be one of the three specified statuses.");
        }
    }
    
    private void gpa2aid(double GPA) {
        if(GPA < 2) {
            throw new IllegalArgumentException("GPA needs to be above or equal to 2.0");
        }
        
        if(GPA > 3.75) {
            setFinancialAid(5000);
        } else if(GPA > 3.50) {
            setFinancialAid(4500);
        } else if(GPA > 3.00) {
            setFinancialAid(3000);
        } else if(GPA > 2.50) {
            setFinancialAid(2500);
        } else {
            setFinancialAid(0);
        }
    
    }
    
    protected double major2book(String major) {
        major = major.toLowerCase();
        switch (major) {
            case "computer science":
                return 200;
            case "engineering":
                return 500;
            case "liberal arts":
                return 750;
            default:
                return 100;
        }
    }
    
    @Override
    public double calculateYearlyCost() {
        return super.calculateYearlyCost() + bookFees - financialAid;
    }
    
    @Override
    public String toString() {
        String front = super.toString();
        String back = String.format("Book Fees: %.2f\n" +
                "Financial Aid: %.2f\n",
                bookFees, financialAid);
        
        return front + back;
    }
}