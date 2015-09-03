package viewcontroller;

import model.PhoneNumber;

import javax.swing.*;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by bak on 01/09/2015.
 */
public class AddPhonePanel extends AddClientPanel{
    private int clientID;
    public AddPhonePanel(int ID) throws Exception {
        super();
        this.clientID = ID;
    }

    @Override
    protected void initComponent(){
        String[] labels = { "Number", "Registration day", "Service Provider"};
        for (String label:labels){
            JLabel l = new JLabel(label,JLabel.TRAILING);
            JTextField tf = new JTextField(50);
            l.setLabelFor(tf);
            dataList.add(tf);
            this.add(l);
            this.add(tf);
        }
        SpringUtilities.makeCompactGrid(this, labels.length, 2, 6, 6, 6, 6);
    }

    protected void processPhone(){
        para.addAll(this.dataList.stream().map(JTextField::getText).collect(Collectors.toList()));
        long num = Long.parseLong(para.get(0));
        Date d = StringToDate(para.get(1));
        if(!db.findBy("ID",String.valueOf(clientID)).get(0).addNumber(new PhoneNumber(num,d,true,para.get(2)))){
            new JOptionPane().showMessageDialog(this,"This client have 3 number already!");
        }
        this.clearData();
    }
}
