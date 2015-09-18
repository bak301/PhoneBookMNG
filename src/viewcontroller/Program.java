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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createMenu(){
        // create the bar
        JMenuBar mnBar = new JMenuBar();

        // Create menu item
        JMenu addBookMenu =  new JMenu("Add Book");
        addBookMenu.addMouseListener((Press) e -> {
            try {
                addBookFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create menu item
        JMenu findBookMenu = new JMenu("Find Books");
        findBookMenu.addMouseListener((Press) e -> {
            try {
                findBookFrame();
            } catch (Exception x) {
                x.printStackTrace();
            }
        });

        // Add menu item to the bar
        mnBar.add(addBookMenu);
        mnBar.add(findBookMenu);
        setJMenuBar(mnBar);
    }

    private void addBookFrame() throws Exception{
        getContentPane().removeAll();
        // Set Spring layout to get data form
        setLayout(new BorderLayout());

        // Create an add Book panel
        AddBookPanel p = new AddBookPanel();

        JButton btAddBook = new JButton("Add Book");
        btAddBook.addMouseListener((Press) e -> {
            p.process();
        });

        // Add the panel
        add(p, BorderLayout.CENTER);
        add(btAddBook,BorderLayout.SOUTH);
        pack();
    }

    private void findBookFrame() throws Exception{
        getContentPane().removeAll();
        FindBookPanel find = new FindBookPanel();
        add(find);
        pack();
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new Program("Library Manager").setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
