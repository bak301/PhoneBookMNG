package viewcontroller;

import model.Book;
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
public class FindBookPanel extends JPanel {
    private Database db;
    private JTextArea result;
    private ArrayList<Book> rs;

    public FindBookPanel() throws Exception{
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
        rs = new ArrayList<Book>();
        JButton del = new JButton("Delete");
        del.addMouseListener((Press)e -> {
            db.removeBook(rs);
            JOptionPane.showMessageDialog(this, "Delete success!");
        });
        add(del,BorderLayout.SOUTH);
    }

    private JPanel createParameterOption(){
        JPanel menu = new JPanel(new FlowLayout());
        String[] list = {"ID" , "ISBN" , "name" , "author" , "genre"};
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
                        Book book = (Book) x.next();
                        resultText += "ID: " + book.getID() +
                                "\n Name :" + book.getName() ;
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
