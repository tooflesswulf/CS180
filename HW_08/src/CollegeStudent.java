/**
 * CollegeStudent
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 3/6/18
 *
 */

public class CollegeStudent {
    private double dormCost;
    private double tuition;
    private Residency residency;
    private boolean livesOffCampus;
    
    public CollegeStudent(String residency) {
        this(residency, false);
    }
    
    public CollegeStudent(String residency, boolean livesOffCampus) {
        setResidency(residency);
        setLivingOffCampus(livesOffCampus);
    }
    
    public void setTuition(double tuition) {this.tuition = tuition;}
    public void setDormCost(double dormCost) {this.dormCost = dormCost;}
    
    protected double residencyToTuition(Residency residency) {
        switch(residency) {
            case IN_STATE:
                return 23000;
            case OUT_OF_STATE:
                return 42000;
            case INTERNATIONAL:
                return 44500;
            default:
                throw new IllegalArgumentException("Student residency must be one of the three specified statuses.");
        }
    }
    
    public void setResidency(String res) {
        res = res.toLowerCase();
        switch (res) {
            case "in state":
                residency = Residency.IN_STATE;
                break;
            case "out of state":
                residency = Residency.OUT_OF_STATE;
                break;
            case "international":
                residency = Residency.INTERNATIONAL;
                break;
            default:
                throw new IllegalArgumentException("Student residency must be one of the three specified statuses.");
        }
        setTuition(residencyToTuition(residency));
    }
    public void setLivingOffCampus(boolean offCampus) {
        livesOffCampus = offCampus;
        if(livesOffCampus) {
            setDormCost(500);
        } else {
            setDormCost(800);
        }
    }
    
    public double getDormCost() {return dormCost;}
    public double getTuition() {return tuition;}
    public Residency getResidency() {return residency;}
    public boolean isLivingOffCampus() {return livesOffCampus;}
    
    public String toString() {
        String res = "";
        
        switch(residency) {
            case IN_STATE:
                res = "In State";
                break;
            case OUT_OF_STATE:
                res = "Out of State";
                break;
            case INTERNATIONAL:
                res = "International";
                break;
        }
        
        String ret = String.format(
                "The total expenses are %.2f\n" +
                "Here is the breakdown:\n" +
                "This student is %s\n" +
                "Yearly Tuition: %.2f\n" +
                "Dorm Costs: %.2f\n",
                calculateYearlyCost(), res, tuition, dormCost*12);
        return ret;
    }
    
    public double calculateYearlyCost() {
        return tuition + 12*dormCost;
    }
    
}