package model;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Created by bak on 31/08/2015.
 */

public class Database {
    public ArrayList<Client> clientDB;

    public Database() throws Exception{
        initDB();
        readDB();
    }

    public void initDB() throws IOException{
        File file = new File("client.db");
        if (!file.exists()){
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        new ObjectOutputStream(new FileOutputStream(file)).writeObject(new ArrayList<Client>());
    }

    public ArrayList<Client> readDB() throws Exception{
        clientDB = (ArrayList<Client>) new ObjectInputStream(new FileInputStream("client.db")).readObject();
        return clientDB;
    }

    private boolean updateDB(){
        try{
            for(Client c:clientDB){
                c.setID(clientDB.indexOf(c));
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("client.db"));
            oos.writeObject(clientDB);
            oos.flush();
            oos.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //Add a client
    public boolean addClient(Client c){
        clientDB.add(c);

        //Set client ID on the database
        c.setID(clientDB.indexOf(c));
        return updateDB();
    }

    // Remove clients
    public boolean removeClient(ArrayList<Client> found){
        clientDB.removeAll(found);
        return updateDB();
    }

    // Find clients
    public ArrayList<Client> findBy(String field,String data){
        ArrayList<Client> found = new ArrayList<Client>();
        switch (field){
            case "ID":
                found.addAll(clientDB.stream().filter(c -> String.valueOf(c.getID()).contains(data)).collect(Collectors.toList()));
                break;
            case "firstName":
                found.addAll(clientDB.stream().filter(c -> c.getFirstName().contains(data)).collect(Collectors.toList()));
                break;
            case "lastName":
                found.addAll(clientDB.stream().filter(c -> c.getLastName().contains(data)).collect(Collectors.toList()));
                break;
            case "birthday":
                found.addAll(clientDB.stream().filter(c -> StringToDate(data).equals(c.getBirthDay())).collect(Collectors.toList()));
                break;
            case "cmnd":
                found.addAll(clientDB.stream().filter(c -> String.valueOf(c.getCmnd()).contains(data)).collect(Collectors.toList()));
                break;
            case "address":
                found.addAll(clientDB.stream().filter(c -> c.getAddress().contains(data)).collect(Collectors.toList()));
                break;
            case "gender":
                found.addAll(clientDB.stream().filter(c -> c.getGender().contains(data)).collect(Collectors.toList()));
                break;
            case "number":
                for(Client c:clientDB){
                    found.addAll(c.getNumList().stream().filter(phoneNumber -> String.valueOf(phoneNumber.getNumber()).contains(data)).map(phoneNumber -> c).collect(Collectors.toList()));
                }
                break;
        }
        return found;
    }

    private Date StringToDate(String dateString)
    {
        Date date = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try{
            date = df.parse(dateString);
        }
        catch ( Exception ex ){
            ex.printStackTrace();
        }
        return date;
    }
}
