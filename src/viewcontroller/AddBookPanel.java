package viewcontroller;

import model.Book;
import model.Database;
import model.Genre;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by bak on 01/09/2015.
 */
public class AddBookPanel extends JPanel{
    protected Database db;
    protected LinkedList<JTextField> dataList;
    protected LinkedList<String> para;

    public AddBookPanel() throws Exception{
        super(new SpringLayout());
        db = new Database();
        dataList = new LinkedList<JTextField>();
        para = new LinkedList<String>();
        initComponent();
    }

    // ******************* NEED REWORK ********************************
    protected void initComponent(){
        String[] labels = { "ISBN: ", "name: ", "author: ","genre: "};

        // Create input field for each label
        for (String label : labels) {
            // Create label
            JLabel l = new JLabel(label, JLabel.TRAILING);

            //Create text field
            JTextField textField = new JTextField(50);
            l.setLabelFor(textField);

            //add it to list for future use
            this.dataList.add(textField);

            //add component to viewcontroller
            this.add(l);
            this.add(textField);
        }

        //Create grid
        SpringUtilities.makeCompactGrid(this, labels.length, 2, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
    }
    //******************************************************************8

    // Gather data from all text field and then create new model.Book
    protected int process(){
        para.addAll(this.dataList.stream().map(JTextField::getText).collect(Collectors.toList()));
        Book newBook = new Book(Long.parseLong(para.get(0)),para.get(1),para.get(2), Genre.valueOf(para.get(3).toUpperCase()));

        // Add it to database
        db.addBook(newBook);
        System.out.println("Book added! ");

        // clear all the field
        this.clearData();

        // return the id
        return db.getBookDB().indexOf(newBook);
    }

    // reset the text field to blank
    protected void clearData(){
        para.clear();
        for(JTextField f:this.dataList){
            f.setText("");
        }
    }

    protected Date StringToDate(String dateString)
    {
        Date date;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try{
            date = df.parse(dateString);
        }
        catch ( Exception ex ){
            return null;
        }
        return date;
    }
}
