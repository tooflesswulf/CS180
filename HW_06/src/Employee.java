import java.util.Random;

/**
 * Employee
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 2/18/18
 *
 */

public class Employee {
    private int idNumber;
    private String name;
    private String position;
    private double salary;
    
    public Employee(String name, String position) {
        setName(name);
        setPosition(position);
        idNumber = generateIdNumber();
    }
    
    private int generateIdNumber() {
        Random r = new Random();
        // Six digit numbers range from 100000 - 999999
        return 100000 + r.nextInt(900000); //bound is exclusive.
    }
    
    public int getIdNumber() {return idNumber;}
    public String getName() {return name;}
    public String getPosition() {return position;}
    public double getSalary() {return salary;}
    
    public void setName(String name) {
        if(name == null) name="";
        this.name=name;
    }
    
    public void setPosition(String position) {
        if(position == null) position="";
        this.position=position;
    
        salary = 25000;
        if(position.equals("Manager")) salary = 50000;
    }
    
    public void setSalary(double salary) {
//        if(salary==null) salary=0;
        if(salary<0) salary=0;
        this.salary = salary;
    }
    
    public String toString() {
        return String.format("ID number: %d\nName: %s\nPosition: %s\nSalary: $%.2f", idNumber, name, position, salary);
    }
}
