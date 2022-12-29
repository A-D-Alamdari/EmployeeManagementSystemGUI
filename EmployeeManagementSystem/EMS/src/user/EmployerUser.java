package user;

import mvc.Model;


public class EmployerUser extends User implements InsertAndDelete {

    public EmployerUser(String username, String password) {
        super(username, password);
    }
 
    @Override
    public boolean fire(Model mod,int index) {
        mod.removeUser(index);
        return true;
    }

    @Override
    public boolean hire(Model mod,int index, String name, String job, double salary) {
        User u = new EmployeeUser(job, salary, index, name, this.getPassword());
        mod.insertUser(u);
        return true;
        
    }

    @Override
    public boolean update(Model mod,int index, String name, String job, double salary) {
        EmployeeUser emp = new EmployeeUser(job, salary, index, name, this.getPassword());
        mod.updateUser(emp);
        return true;
        
    }

    @Override
    boolean login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString()+",employer";
    }

    
    
}
