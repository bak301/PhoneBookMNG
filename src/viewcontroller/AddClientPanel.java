package viewcontroller;

import model.Client;
import model.Database;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by bak on 01/09/2015.
 */
public class AddClientPanel extends JPanel{
    protected Database db;
    protected LinkedList<JTextField> dataList;
    protected LinkedList<String> para;

    //Default gender is male
    private String gd = "male";

    public AddClientPanel() throws Exception{
        super(new SpringLayout());
        db = new Database();
        initComponent();
    }

    // ******************* NEED REWORK ********************************
    protected void initComponent(){
        dataList = new LinkedList<JTextField>();
        para = new LinkedList<String>();
        String[] labels = { "First Name: ", "Last Name: ", "Birthday: ","CMND: ","Address: " };

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

        // Create special radio button for Gender
        JPanel buttonGroup = new JPanel(new GridLayout());
        buttonGroup.setPreferredSize(new Dimension(400,50));

        // Get a buttonGroup so only one choice can be made;
        ButtonGroup gds = new ButtonGroup();
        JLabel genderLabel = new JLabel(" Gender: ");
        genderLabel.setLabelFor(buttonGroup);
        buttonGroup.add(genderLabel);

        // Declare these radio button
        String[] genderList = {"male","female","bisexual"};
        for (String l : genderList){
            JRadioButton rB = new JRadioButton(l);
            gds.add(rB);
            rB.addMouseListener((Press) e -> {
                gd = rB.getName();
            });
            buttonGroup.add(rB);
        }
        add(buttonGroup,BorderLayout.NORTH);

        //Create grid
        SpringUtilities.makeCompactGrid(this, labels.length, 2, //rows, cols
                6, 50, //initX, initY
                6, 6); //xPad, yPad
    }

    private boolean isValidName(JTextField f){
        return Pattern.matches("[a-zA-Z]+", f.getText());
    }

    private boolean isValidPhone(JTextField f){
        return Pattern.matches("[0&&([9&&[0-9]{8-9}+]|[1 6]([0-9]{7-8}+))]", f.getText());
    }

    private boolean isValidCMND(JTextField f){
        return Pattern.matches("[0-9]{11}+",f.getText());
    }

    //******************************************************************8

    // Gather data from all text field and then create new model.Client
    protected int process(){
        para.addAll(this.dataList.stream().map(JTextField::getText).collect(Collectors.toList()));
        Date bd = StringToDate(para.get(2));
        if (!isValidName(dataList.get(0))||!isValidName(dataList.get(1))){
            new JOptionPane().showMessageDialog(this,"Name only contains alphabet characters!");
        } else if (bd==null){
            new JOptionPane().showMessageDialog(this,"Date must match dd-mm-yyyy pattern!");
        } else if (!isValidCMND(dataList.get(3))){
            new JOptionPane().showMessageDialog(this,"Citizen ID must match contains exactly 11 numbers!");
        } else {
            long cmnd = Long.parseLong(para.get(3));
            Client newClient = new Client(para.get(0), para.get(1), bd, gd, cmnd, para.get(4));
            // Add it to database
            db.addClient(newClient);
            System.out.println("Client added! ");

            // clear all the field
            this.clearData();

            // return the id
            return db.clientDB.indexOf(newClient);
        }
        return -1;
    }

    protected boolean isAllFieldFilled(){
        for (JTextField l:this.dataList){
            if (l.getText().equals("")){
                return false;
            }
        }
        return true;
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
        Date date = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try{
            date = df.parse(dateString);
        }
        catch ( Exception ex ){
            return date;
        }
        return date;
    }
}
