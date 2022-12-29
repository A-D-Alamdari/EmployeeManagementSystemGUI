package mvc.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import mvc.Controller;
import mvc.Model;
import user.EmployeeUser;
import user.User;

public class AdminShowDatabase extends JFrame implements ActionListener {

    public void create(Model mod, Controller con) {

        AdminShowDatabase mainFrame = new AdminShowDatabase();
        mainFrame.setTitle("Database");
        mainFrame.setSize(650, 600);
        LayoutManager m = new BorderLayout();

        mainFrame.setLayout(m);

        //create border
        Border blackline = BorderFactory.createTitledBorder("Database");

        //add jpanel with gridbag layout
        JPanel jp_1 = new JPanel(new GridBagLayout());
        //create constraints
        GridBagConstraints c = new GridBagConstraints();

        //----------------------------------- first row, y =0
        JLabel title = new JLabel("Employees IN Database ");
        title.setPreferredSize(new Dimension(400, 20));
        //will add item at 0,0
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        jp_1.add(title, c);

       
        TextArea data = new TextArea();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        jp_1.add(data, c);

        data.append("____\t\t  ___\t  ______\t ______ \n");
        data.append("Name\t\t ,Job\t ,Salary\t   ,Index \n");
        data.append("____\t\t  ___\t  ______\t ______ \n");
        //add the text fields -------------------------------- third row y = 2
        ArrayList<User> users = mod.getEmployees();
        int y = 3;
        int count = 0;
        for (count = 0; count < users.size(); count++) {
            EmployeeUser u = (EmployeeUser)users.get(count);
            
            data.append(u.getUsername()+"\t\t ,"+u.getJob()+"\t ,"+u.getSalary()+"\t   ,"+u.getIndex()+"\n");
           

        }
        
        data.setEditable(false);

        //--------------------------------------- last row
        JButton btn_exit = new JButton("Return");
        btn_exit.setPreferredSize(new Dimension(400, 20));
        c.gridx = 0;
        c.gridy = count+2;
        c.gridwidth = 5;
        jp_1.add(btn_exit, c);

        jp_1.setPreferredSize(new Dimension(650, 500));

        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    con.openAdminPage();
                    mainFrame.setVisible(false);

                } catch (Exception exc) {

                }
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
        mainFrame.add(controlPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
