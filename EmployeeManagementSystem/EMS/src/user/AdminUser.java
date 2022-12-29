package user;

import mvc.Model;


public class AdminUser extends User implements  InsertAndDelete{

    public AdminUser(String username, String password) {
        super(username, password);
    }

    @Override
    boolean login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean fire(Model mod,int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hire(Model mod,int index, String name, String job, double salary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Model mod,int index, String name, String job, double salary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public String toString() {
        return super.toString()+",admin";
    }

    
    
}
