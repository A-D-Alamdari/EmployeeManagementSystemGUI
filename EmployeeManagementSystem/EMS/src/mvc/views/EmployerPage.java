
package mvc.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import mvc.Controller;
import mvc.Model;
import user.EmployeeUser;
import user.User;


public class EmployerPage extends JFrame implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void create(Model mod, Controller con) {

        EmployerPage mainFrame = new EmployerPage();
        mainFrame.setTitle("Employer Page");
        mainFrame.setSize(650, 600);
        LayoutManager m = new BorderLayout();

        mainFrame.setLayout(m);

        //create border
        Border blackline = BorderFactory.createTitledBorder("Employer Page");

        

        //add jpanel with gridbag layout
        JPanel jp_1 = new JPanel(new GridBagLayout());
        //create constraints
        GridBagConstraints c = new GridBagConstraints();
       
        
        //----------------------------------- first row, y =0
        JButton btn_show_db = new JButton("Show Database");
        btn_show_db.setPreferredSize(new Dimension(300, 20));
        //will add item at 0,0
        c.gridx = 0;
	c.gridy = 0;
        c.gridwidth = 4;
        jp_1.add(btn_show_db,c);
        
        
        //hire employee ----------------------------------- second row y = 1
        JLabel hder_name = new JLabel("Name",10);
        c.gridx = 0;
	c.gridy = 1;
        c.gridwidth = 1;
        jp_1.add(hder_name,c);
        JLabel hder_job = new JLabel("Job",10);
        c.gridx = 1;
	c.gridy = 1;
        jp_1.add(hder_job,c);
        JLabel hder_salary = new JLabel("Salary",10);
        c.gridx = 2;
	c.gridy = 1;
        jp_1.add(hder_salary,c);
        
        //add the text fields -------------------------------- third row y = 2
        JTextField txt_name = new JTextField(5);
        c.gridx = 0;
	c.gridy = 2;
        jp_1.add(txt_name,c);
        
        JTextField txt_job = new JTextField(5);
        c.gridx = 1;
	c.gridy = 2;
        jp_1.add(txt_job,c);
        
        JTextField txt_salary = new JTextField(5);
        c.gridx = 2;
	c.gridy = 2;
        jp_1.add(txt_salary,c);
        
        JButton btn_hire = new JButton("Hire Employee");
        c.gridx = 3;
	c.gridy = 2;
        jp_1.add(btn_hire,c);
        
        
        // fire employee items ----------------------------- fourth row y = 3
        JLabel hder_index = new JLabel("Index",10);
        c.gridx = 0;
	c.gridy = 3;
        jp_1.add(hder_index,c);
        
        //  ----------------------------------------  fifth row y = 4
        JTextField txt_em_index = new JTextField(15);
        c.gridx = 0;
	c.gridy = 4;
        c.gridwidth = 3;
        jp_1.add(txt_em_index,c);
        JButton btn_fire = new JButton("Fire Employee");
        c.gridx = 3;
	c.gridy = 4;
        c.gridwidth = 1;
        jp_1.add(btn_fire,c);
        
        //raise salary part  ----------------------------------------  sixth row y = 5
        JLabel hder_index_hire = new JLabel("Index",10);
        c.gridx = 0;
	c.gridy = 5;
        c.gridwidth = 2;
        jp_1.add(hder_index_hire,c);
        
        JLabel hder_salry_hire = new JLabel("Salary",10);
        c.gridx = 2;
	c.gridy = 5;
        c.gridwidth = 1;
        jp_1.add(hder_salry_hire,c);
        //add text fields
        
        //  ----------------------------------------  7th row y = 6
        JTextField txt_index_hire = new JTextField(10);
        c.gridx = 0;
	c.gridy = 6;
        c.gridwidth = 2;
        jp_1.add(txt_index_hire,c);
        
        JTextField txt_index_salary = new JTextField(5);
        c.gridx = 2;
	c.gridy = 6;
        c.gridwidth = 1;
        jp_1.add(txt_index_salary,c);
        
        JButton btn_raise = new JButton("Raise Salary");
        c.gridx = 3;
	c.gridy = 6;
        jp_1.add(btn_raise,c);
        
        //space row
        JLabel lbl_space = new JLabel("    ");
        c.gridx = 0;
	c.gridy = 7;
        jp_1.add(lbl_space,c);
        
        //--------------------------------------- last row
        JButton btn_exit = new JButton("Exit");
        btn_exit.setPreferredSize(new Dimension(300, 20));
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 4;
        jp_1.add(btn_exit,c);
        
        
       jp_1.setPreferredSize(new Dimension(400, 500));
       
        

       btn_hire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String name = txt_name.getText();
                    String job = txt_job.getText();
                    double sal = Double.parseDouble(txt_salary.getText());
                    String diff_pass = Cryptography.Cryptography.getHash(name);
                    int index = mod.getSize() + 1;

                    User euser = new EmployeeUser(job, sal, index, name, name);
                    mod.insertUser(euser);
                    mod.addToFile();
                    JOptionPane.showMessageDialog(rootPane, "Success, Use Name as Default username and password");

                } catch (Exception exc) {

                }
            }
        });
       
       
       btn_fire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = Integer.parseInt(txt_em_index.getText());
                boolean result = mod.removeUser(index);

                if (result) {
                    JOptionPane.showMessageDialog(rootPane, "Employee Fired!!!");
                } else {

                    JOptionPane.showMessageDialog(rootPane, "Failed to remove!!");

                }
            }
        });
       
       btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.setVisible(false);
               con.logIn();
            }
        });
       
       
       btn_raise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    
                    double sal = Double.parseDouble(txt_index_salary.getText());
                   
                    int index = Integer.parseInt(txt_index_hire.getText());

                    
                    boolean result = mod.raiseSalary(index, sal);
                    mod.addToFile();

                if (result) {
                    JOptionPane.showMessageDialog(rootPane, "User Updated!!!");
                } else {

                    JOptionPane.showMessageDialog(rootPane, "Failed to Update!!");

                }
            }
        });
       
       btn_show_db.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.setVisible(false);
                con.openEmployerShowDB();
            }
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(400, 600));
        controlPanel.setBorder(blackline);

        
        controlPanel.add(jp_1, JPanel.CENTER_ALIGNMENT);
        //add the panel
        mainFrame.add(controlPanel,BorderLayout.CENTER);
        mainFrame.setVisible(true);

    }

    public JPanel getSpace() {

        JPanel jp_space = new JPanel();
        jp_space.setMaximumSize(new Dimension(100, 50));
        return jp_space;

    }

    
}
