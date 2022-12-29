package mvc;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.crypto.SecretKey;
import user.AdminUser;
import user.EmployeeUser;
import user.EmployerUser;
import user.User;

/**
 *
 * data format user_name, password, user_type, job,salary,index
 */
public class Model {

    String key;
    ArrayList<User> users;
    SecretKey secretKey;
    int numUsers;

    public Model() {
        try {
            key = "0123456789abcdef0123456789abcdef";
            users = getAll();
            secretKey = Cryptography.Cryptography.generateSecretKey(key);
            numUsers = users.size();
        } catch (Exception exc) {
            System.out.println("constructor error " + exc);
        }
    }

    public ArrayList<User> getAll() {

        users = new ArrayList<>();

        try {
            secretKey = Cryptography.Cryptography.generateSecretKey(key);
            Scanner s = new Scanner(new File("database.txt"));

            while (s.hasNext()) {
                String encrypted = s.next();
                String decryString = Cryptography.Cryptography.decrypt(encrypted, secretKey);

                String[] arr = decryString.split(",");
                User user;
                if (arr[2].equals("employee")) {

                    //user_name, password, user_type, job,salary,index
                    user = new EmployeeUser(arr[3], Double.parseDouble(arr[4]), Integer.parseInt(arr[5]), arr[0], arr[1]);
                } else if (arr[2].equals("admin")) {

                    user = new AdminUser(arr[0], arr[1]);

                } else {

                    user = new EmployerUser(arr[0], arr[1]);

                }
                users.add(user);

            }
            s.close();
            return users;

        } catch (Exception exc) {
            System.out.println(" ArrayList<User> getAll() " + exc);
        }

        return new ArrayList<>();
    }

    /**
     * used to add new users
     *
     * @param user
     * @return
     */
    public boolean insertUser(User user) {

        if (!isUser(user.getUsername(), user.getPassword())) {
            try {
                users.add(user);
                System.out.println("User Added");
                numUsers++;
                return true;
            } catch (Exception exc) {
                System.out.println(" insertUser(User user) " + exc);
            }
            return true;
        } else {
            System.out.println("User failed");
            return false;
        }
    }

    /**
     * used to remove users
     *
     * @param index
     * @return
     */
    public boolean removeUser(int index) {

        try {
            for (User u : users) {

                if (u instanceof EmployeeUser) {

                    EmployeeUser x = (EmployeeUser) u;
                    if (x.getIndex() == index) {
                        users.remove(u);
                        return true;
                    }
                }

            }
        } catch (Exception exc) {
            System.out.println(" removeUser(int index) " + exc);
        }

        return false;
    }

    /**
     * used to update users
     *
     * @param emp
     * @return
     */
    public boolean updateUser(EmployeeUser emp) {

        int count = 0;
        try {
            for (User u : users) {

                if (u instanceof EmployeeUser) {

                    EmployeeUser x = (EmployeeUser) u;

                    if (x.getIndex() == emp.getIndex()) {
                        users.remove(u);
                        x.setIndex(emp.getIndex());
                        x.setUsername(emp.getUsername());
                        x.setJob(emp.getJob());
                        x.setSalary(emp.getSalary());
                        users.add(count, x);

                    }
                }

                count++;

            }
            return true;
        } catch (Exception exc) {
            System.out.println(" " + exc);
        }

        return false;

    }
    
    
    public boolean raiseSalary(int index, double salary) {

        int count = 0;
        try {
            for (int x = 0; x < users.size(); x++) {

                User u = users.get(x);
                if (u instanceof EmployeeUser) {

                    EmployeeUser emp = (EmployeeUser) u;

                    if (emp.getIndex() == index) {
                        users.remove(u);
                        emp.setSalary(salary);
                        users.add(count, emp);

                    }
                }

                count++;

            }
            return true;
        } catch (Exception exc) {
            System.out.println(" " + exc);
        }

        return false;

    }


    /**
     * 
     * @return 
     */
    public ArrayList<User> getEmployees() {

        int count = 0;
        ArrayList<User> emps = new ArrayList<>();
        try {
            for (User u : users) {

                if (u instanceof EmployeeUser) {

                    emps.add(u);
                }

                count++;

            }
            return emps;
        } catch (Exception exc) {
            System.out.println(" " + exc);
        }

        return emps;

    }

    /**
     * used to write data to file
     */
    public void addToFile() {

        File dlt = new File("database.txt");
        dlt.delete();

        File create_file = new File("database.txt");

        try {

            FileWriter writer = new FileWriter("database.txt");
            for (User str : users) {
                String plain = str.toString();
                String encrypted = Cryptography.Cryptography.encrypt(plain, secretKey);
                writer.write(encrypted + System.lineSeparator());
            }
            writer.close();

        } catch (Exception exc) {

            System.out.println("HERE-----------" + exc);

        }

    }

    /**
     *
     * @param uname
     * @param password
     * @return
     */
    public String checkUser(String uname, String password) {

        try {
            for (User u : users) {

                System.out.println(u.getUsername() + " -- " + uname + " Passwords " + u.getPassword() + " " + password);
                if (u.getPassword().equals(password) && u.getUsername().equals(uname)) {

                    System.out.println(u.toString());
                    if (u instanceof EmployeeUser) {

                        //user_name, password, user_type, job,salary,index
                        return "employee";
                    } else if (u instanceof EmployerUser) {

                        return "employer";

                    } else {

                        return "admin";

                    }
                }

            }
        } catch (Exception exc) {
            System.out.println(" " + exc);
        }

        return null;
    }
    
    
     public EmployeeUser getEmployee(String uname, String password) {

        try {
            for (User u : users) {

               
                if (u.getPassword().equals(password) && u.getUsername().equals(uname)) {

                    System.out.println(u.toString());
                    if (u instanceof EmployeeUser) {

                        EmployeeUser emp = (EmployeeUser)u;
                        return emp;
                    } 
                }

            }
        } catch (Exception exc) {
            System.out.println(" " + exc);
        }

        return null;
    }

    public boolean isUser(String uname, String password) {

        try {
            for (User u : users) {

                if (u.getPassword().equals(password) && u.getUsername().equals(uname)) {
                    return true;
                }

            }
        } catch (Exception exc) {
            System.out.println(" " + exc);
        }

        return false;
    }

    public int getSize() {

        return numUsers;

    }

}
