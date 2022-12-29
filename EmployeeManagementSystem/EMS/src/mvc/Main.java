package mvc;

import user.AdminUser;
import user.EmployerUser;


public class Main {
    public static void main(String[] args) {
      
        Model mod = new Model();
        
        //lets add a default user
        AdminUser root = new AdminUser("admin", "root");
        EmployerUser empu = new EmployerUser("root", "root");
        mod.insertUser(root);
        mod.insertUser(empu);
        
        //get log in details
        Controller con = new Controller(mod);
        con.logIn();
    }
    
}
