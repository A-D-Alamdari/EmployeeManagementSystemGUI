package user;

import mvc.Model;


public interface  InsertAndDelete {
    
    public boolean fire(Model mod,int index);
    public boolean hire(Model mod,int index,String name, String job, double salary);
    public boolean update(Model mod,int index,String name, String job, double salary);
    
    
}
