package mvc;

import mvc.views.AdminPage;
import mvc.views.AdminShowDatabase;
import mvc.views.EmployeePage;
import mvc.views.EmployerPage;
import mvc.views.EmployerShowwDatabase;
import mvc.views.LogInForm;
import user.EmployeeUser;


public class Controller {

    Model mod;
    public Controller(Model mod) {
        this.mod = mod;
    }
    
    
    
    
    public void logIn(){
    
        //request view
        LogInForm login = new LogInForm();
        login.create(mod, this);
        
    
    }
    
    public void openAdminPage(){
    
        //request view
        AdminPage admin = new AdminPage();
        admin.create(mod, this);
        
    
    }
    
    
    public void openEmployerPage(){
    
        //request view
        EmployerPage emp = new EmployerPage();
        emp.create(mod, this);
        
        
    
    }
    
    public void openEmployeePage(EmployeeUser user){
    
        //request view
       EmployeePage p = new EmployeePage();
       p.create(mod, this,user);
        
    
    }
    
    public void openAdminShowDB(){
    
        //request view
        AdminShowDatabase sh = new AdminShowDatabase();
        sh.create(mod, this);
        
    
    }
    
    public void openEmployerShowDB(){
    
        //request view
        EmployerShowwDatabase sh = new EmployerShowwDatabase();
        sh.create(mod, this);
        
    
    }
    
}
