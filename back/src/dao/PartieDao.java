package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import database.PolyNameDatabase;
import models.Partie;

public class PartieDao {
    public PartieDao(){
    }
    public ArrayList<Partie> findAll(){
        ArrayList<Partie> res=new ArrayList<>();
        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM partie";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                final int id_partie=results.getInt("id_partie");
                final String unique_code= results.getString("unique_code");
                final int score=results.getInt("score");
                Partie myRecord = new Partie(id_partie,unique_code,score);
                res.add(myRecord);
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }

    public Partie createLobby() {
        Partie myPartie = null;
        try {
            
            String uniqueCode = UUID.randomUUID().toString().substring(0, 8); // Génère un ID unique de 8 caractères
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            String requestCreate = "INSERT INTO partie (unique_code, score) VALUES (?, ?)";
            PreparedStatement prepStatCreate = myDatabase.prepareStatement(requestCreate);
            prepStatCreate.setString(1, uniqueCode);
            prepStatCreate.setInt(2, 0);
            prepStatCreate.executeUpdate();
            String requestRead = "SELECT * FROM partie WHERE unique_code = '" + uniqueCode + "'";
            PreparedStatement prepStatRead=myDatabase.prepareStatement(requestRead);
            ResultSet results = prepStatRead.executeQuery();   
            while (results.next()){
                final int id_partie=results.getInt("id_partie");
                final String unique_code= results.getString("unique_code");
                final int score=results.getInt("score");
                Partie myRecord = new Partie(id_partie,unique_code,score);
                return myRecord;
                }
            } 
            catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myPartie;
    }
}