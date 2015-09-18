package model;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by bak on 31/08/2015.
 */

public class Database {
    private ArrayList<Book> BookDB;

    public Database() throws Exception{
//        initDB();
        readDB();
    }

    public void initDB() throws IOException{
        File file = new File("Book.db");
        if (file.exists()){
            if (file.delete()){
                System.out.println(" Old database deleted !");
            } else {
                System.out.println(" Cannot delete old database !");
            }
        }
        if (file.createNewFile()){
            System.out.println(" Database file created !");
        } else {
            System.out.println(" Cannot create new file !");
        }
        new ObjectOutputStream(new FileOutputStream(file)).writeObject(new ArrayList<Book>());
        System.out.println("Database initialized !");
    }

    public void readDB() throws Exception{
        BookDB = (ArrayList<Book>) new ObjectInputStream(new FileInputStream("Book.db")).readObject();
    }

    public ArrayList<Book> getBookDB() {
        return BookDB;
    }

    private boolean updateDB(){
        try{
            for(Book c:BookDB){
                c.setID(BookDB.indexOf(c));
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Book.db"));
            oos.writeObject(BookDB);
            oos.flush();
            oos.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //Add a Book
    public boolean addBook(Book c){
        BookDB.add(c);

        //Set Book ID on the database
        c.setID(BookDB.indexOf(c));
        return updateDB();
    }

    // Remove Books
    public boolean removeBook(ArrayList<Book> found){
        BookDB.removeAll(found);
        return updateDB();
    }

    // Find Books
    public ArrayList<Book> findBy(String field,String data){
        ArrayList<Book> found = new ArrayList<>();
        switch (field){
            case "ID":
                found.addAll(BookDB.stream().filter(c -> String.valueOf(c.getID()).contains(data)).collect(Collectors.toList()));
                break;
            case "ISBN":
                found.addAll(BookDB.stream().filter(c -> String.valueOf(c.getISBN()).contains(data)).collect(Collectors.toList()));
                break;
            case "name":
                found.addAll(BookDB.stream().filter(c -> c.getName().contains(data)).collect(Collectors.toList()));
                break;
            case "author":
                found.addAll(BookDB.stream().filter(c -> c.getAuthor().contains(data)).collect(Collectors.toList()));
                break;
            case "genre":
                found.addAll(BookDB.stream().filter(c -> c.getGenre().toString().contains(data)).collect(Collectors.toList()));
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
