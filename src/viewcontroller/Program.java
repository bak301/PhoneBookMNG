package viewcontroller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bak on 31/08/2015.
 */

public class Program extends JFrame{

    public Program(String title) throws Exception {
        super(title);
        setSize(new Dimension(500, 250));
        createMenu();
    }

    private void createMenu(){
        // create the bar
        JMenuBar mnBar = new JMenuBar();

        // Create menu item
        JMenu addClientMenu =  new JMenu("Add Client");
        addClientMenu.addMouseListener((Press) e -> {
            try {
                addClientFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create menu item
        JMenu findClientMenu = new JMenu("Find Clients");
        findClientMenu.addMouseListener((Press) e -> {
            try {
                findClientFrame();
            } catch (Exception x) {
                x.printStackTrace();
            }
        });

        // Add menu item to the bar
        mnBar.add(addClientMenu);
        mnBar.add(findClientMenu);
        setJMenuBar(mnBar);
    }

    private void addClientFrame() throws Exception{
        getContentPane().removeAll();
        // Set Spring layout to get data form
        setLayout(new BorderLayout());

        // Create an add client panel
        AddClientPanel p = new AddClientPanel();

        // Create a button to confirm data
        JButton confirm = new JButton("Add phone number");
        confirm.setFont(new Font("Arial", 0, 30));

        confirm.addMouseListener((Press) e -> {
            try {
                if (p.isAllFieldFilled()) {
                    int result = p.process();
                    if (result!=-1){
                        int input = JOptionPane.showOptionDialog(null, "Do you want to add phone for this client?", "PhoneBook Manager", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                        if(input == JOptionPane.OK_OPTION) {
                            addPhone(result);
                        } else if (input == JOptionPane.CANCEL_OPTION){
                            getContentPane().removeAll();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        add(confirm, BorderLayout.SOUTH);

        // Add the panel
        add(p, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    private void addPhone(int id) throws Exception{
        getContentPane().removeAll();
        AddPhonePanel p = new AddPhonePanel(id);
        add(p, BorderLayout.CENTER);

        // Create navigation menu
        JPanel navigator = new JPanel(new FlowLayout());

        // Return button
        JButton prev = new JButton("Cancel");
        prev.setFont(new Font("Arial", 0, 30));
        prev.addMouseListener((Press) e -> {
            try {
                p.removeAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        navigator.add(prev);

        // Reset button
        JButton reset = new JButton("Reset");
        reset.setFont(new Font("Arial", 0, 30));
        reset.addMouseListener((Press) e -> {
            try {
                p.clearData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        navigator.add(reset);

        // Next button
        JButton next = new JButton("Continue");
        next.setFont(new Font("Arial", 0, 30));
        next.addMouseListener((Press) e -> {
            try {
                if (p.isAllFieldFilled()) {
                    p.processPhone();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        navigator.add(next);

        add(navigator,BorderLayout.SOUTH);
        pack();
    }

    private void findClientFrame() throws Exception{
        getContentPane().removeAll();
        FindClientPanel find = new FindClientPanel();
        add(find);
        pack();
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new Program("PhoneBook Manager").setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
