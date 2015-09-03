package viewcontroller;

import model.Client;
import model.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bak on 01/09/2015.
 */
public class FindClientPanel extends JPanel {
    private Database db;
    private JTextArea result;
    private ArrayList<Client> rs;

    public FindClientPanel() throws Exception{
        super(new BorderLayout());
        initComponent();
    }

    private void initComponent() throws Exception{
        db = new Database();
        // Parameter menu
        add(createParameterOption(), BorderLayout.NORTH);

        // Result table
        result = new JTextArea();
        result.setBackground(new Color(255, 255, 255));
        result.setPreferredSize(new Dimension(100, 100));
        add(result, BorderLayout.CENTER);

        // Delete button
        rs = new ArrayList<Client>();
        JButton del = new JButton("Delete");
        del.addMouseListener((Press)e->{
            db.removeClient(rs);
            new JOptionPane().showMessageDialog(this,"Delete success!");
        });
        add(del,BorderLayout.SOUTH);
    }

    private JPanel createParameterOption(){
        JPanel menu = new JPanel(new FlowLayout());
        String[] list = {"ID","firstName","lastName","birthday","gender","address","cmnd"};
        JComboBox<String> cb = new JComboBox<String>(list);
        cb.setSelectedIndex(0);

        JLabel label = new JLabel("Search by: ");
        label.setLabelFor(cb);
        menu.add(label);
        menu.add(cb);

        JTextField data = new JTextField(30);
        data.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    result.setText("");

                    rs = db.findBy((String) cb.getSelectedItem(), data.getText());
                    Iterator x = rs.iterator();
                    String resultText ="";

                    while(x.hasNext()){
                        Client client = (Client) x.next();
                        resultText += "ID: "+client.getID()+": "+client.getLastName()+" "+client.getFirstName()+"\n";
                        result.setText(resultText);
                    }
                }
            }
        });

        JLabel input = new JLabel("Input: ");
        input.setLabelFor(data);
        menu.add(input);
        menu.add(data);

        return menu;
    }
}