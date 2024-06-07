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
            String request = "INSERT INTO partie (unique_code, score) VALUES (?, ?)";
            PreparedStatement prepStat = myDatabase.prepareStatement(request);
            prepStat.setString(1, uniqueCode);
            prepStat.setInt(2, 0);
            prepStat.executeUpdate();

            ResultSet generatedKeys = prepStat.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idPartie = generatedKeys.getInt(1);
                myPartie = new Partie(idPartie, uniqueCode, 0);

                // L'ID du salon est connu ici, ce qui permet de renvoyer des informations complètes au client
                System.out.println("Lobby créé avec ID: " + idPartie);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myPartie;
    }
}
