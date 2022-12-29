package user;


public class EmployeeUser extends User{
    
    private String job;
    private double salary;
    private int index;

    public EmployeeUser(String username, String password) {
        super(username, password);
    }

    public EmployeeUser(String job, double salary, int index, String username, String password) {
        super(username, password);
        this.job = job;
        this.salary = salary;
        this.index = index;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return super.toString()+",employee,"+ job + "," + salary + "," + index;
    }
    
    

    @Override
    boolean login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
