package mvc.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import mvc.Controller;
import mvc.Model;
import user.EmployeeUser;

public class LogInForm extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void create(Model mod, Controller con) {

        LogInForm mainFrame = new LogInForm();
        mainFrame.setTitle("Sign In");
        mainFrame.setSize(650, 650);
        
        
        LayoutManager m = new BorderLayout();

        mainFrame.setLayout(m);

        //create border
        //createTitledBorder(String title)
//        Creates a new titled border with the specified title
        Border blackline = BorderFactory.createTitledBorder("Sign In");

        //add jpanel with gridbag layout
        JPanel jp_1 = new JPanel(new GridBagLayout());
        //create constraints
        GridBagConstraints c = new GridBagConstraints();
        //create user name button
        JTextField txt_uname = new JTextField(15);
        //create the label
        JLabel lbl_uname = new JLabel("User Name", JLabel.CENTER);
        //will add item at 0,0
        c.gridx = 0;
        c.gridy = 0;
        jp_1.add(lbl_uname, c);
        //add the text field next to username label
        c.gridx = 1;
        c.gridy = 0;
        jp_1.add(txt_uname, c);

        //adding jpassword
        JPasswordField txt_pass = new JPasswordField(15);
        JLabel lbl_pass = new JLabel("Password", JLabel.CENTER);

        c.gridx = 0;
        c.gridy = 2;
        jp_1.add(lbl_pass, c);
        c.gridx = 1;
        c.gridy = 2;
        jp_1.add(txt_pass, c);
        jp_1.setMaximumSize(new Dimension(400, 50));

        //add the buttons
        JPanel buttons = new JPanel();
        JButton btn_sign = new JButton("Sign In");
        JButton btn_exit = new JButton("Exit");
        c.gridx = 1;
        c.gridy = 4;
        buttons.add(btn_sign);
        buttons.add(btn_exit);
        jp_1.add(buttons, c);

        btn_exit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btn_sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = txt_uname.getText();
                String pas = txt_pass.getText();
                String rst = mod.checkUser(uname, pas);
                EmployeeUser emp = mod.getEmployee(uname, pas);

                if (rst != null) {
                    switch (rst) {
                        case "admin":
                            con.openAdminPage();
                            mainFrame.setVisible(false);
                            break;
                        case "employer":
                            mainFrame.setVisible(false);
                            con.openEmployerPage();
                            break;
                        case "employee":
                            mainFrame.setVisible(false);
                            con.openEmployeePage(emp);
                            break;
                        default:
                            JOptionPane.showMessageDialog(rootPane, "Wrong password or Username");
                            break;
                    }
                }else{
                JOptionPane.showMessageDialog(rootPane, "[Null] Wrong password or Username");
                }

            }
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        JPanel controlPanel = new JPanel();
        controlPanel.setSize(new Dimension(400, 400));
        controlPanel.setLayout(new GridLayout(4, 0));
        controlPanel.setBorder(blackline);

        controlPanel.add(getSpace(), JPanel.CENTER_ALIGNMENT);
        controlPanel.add(jp_1, JPanel.CENTER_ALIGNMENT);

        //add control panel
        mainFrame.add(controlPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);

    }

    public JPanel getSpace() {

        JPanel jp_space = new JPanel();
        jp_space.setMaximumSize(new Dimension(400, 150));
        return jp_space;

    }

}
