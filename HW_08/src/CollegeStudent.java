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
    public void setResidency(String res) {
        switch (res) {
            case "IN_STATE":
                residency = Residency.IN_STATE;
                setTuition(23000);
                break;
            case "OUT_OF_STATE":
                residency = Residency.OUT_OF_STATE;
                setTuition(42000);
                break;
            case "INTERNATIONAL":
                residency = Residency.INTERNATIONAL;
                setTuition(44500);
                break;
            default:
                throw new IllegalArgumentException("Student residency must be one of the three specified statuses.");
        }
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
        String ret = String.format(
                "The total expenses are %.2f\n" +
                "Here is the breakdown:\n" +
                "This student is %s\n" +
                "Yearly Tuition: %.2f\n" +
                "Dorm Costs: %,2f\n",
                calculateYearlyCost(), "durp", tuition, dormCost*12);
        return ret;
    }
    
    public double calculateYearlyCost() {
        return tuition + 12*dormCost;
    }
    
}