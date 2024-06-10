package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                final int nb_joueur=results.getInt("nb_joueur");
                Partie myRecord = new Partie(id_partie,unique_code,score,nb_joueur);
                res.add(myRecord);
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }
    public Partie findByCode(String uniqueCode){
        try{
            PolyNameDatabase myDatabase= new PolyNameDatabase();
            
            String requestRead = "SELECT * FROM partie WHERE unique_code = '" + uniqueCode + "'";
            PreparedStatement prepStatRead=myDatabase.prepareStatement(requestRead);
            ResultSet results = prepStatRead.executeQuery();   
            while (results.next()){
                final int id_partie=results.getInt("id_partie");
                final String unique_code= results.getString("unique_code");
                final int score=results.getInt("score");
                final int nb_joueur=results.getInt("nb_joueur");
                Partie myRecord = new Partie(id_partie,unique_code,score,nb_joueur);
                return myRecord;
                }
            } 
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Partie createLobbyCode() {
        Partie myPartie = null;
        try {
            
            String uniqueCode = UUID.randomUUID().toString().substring(0, 8); // Génère un ID unique de 8 caractères
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            String requestCreate = "INSERT INTO partie (unique_code, score) VALUES (?, ?)";
            PreparedStatement prepStatCreate = myDatabase.prepareStatement(requestCreate);
            prepStatCreate.setString(1, uniqueCode);
            prepStatCreate.setInt(2, 0);
            prepStatCreate.executeUpdate();

            myPartie = findByCode(uniqueCode);
            String  playerAdd ="UPDATE partie SET nb_joueur = ? WHERE unique_code = ?";
            PreparedStatement prepStatAdd = myDatabase.prepareStatement(playerAdd);
            prepStatAdd.setInt(1, myPartie.nb_joueur() + 1);
            prepStatAdd.setString(2, uniqueCode);
            prepStatAdd.executeUpdate();

            return findByCode(uniqueCode);
            } 
            catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myPartie;
    }

    public Partie joinLobby(String uniqueCode) {
        Partie myPartie = null;
        System.out.println("uniqueCode : " + uniqueCode);
        try {
            myPartie = findByCode(uniqueCode);
            System.out.println("myPartie : " + myPartie);
            if (myPartie != null) {
                PolyNameDatabase myDatabase = new PolyNameDatabase();
                String requestUpdate = "UPDATE partie SET nb_joueur = ? WHERE unique_code = ?";
                PreparedStatement prepStatUpdate = myDatabase.prepareStatement(requestUpdate);
                prepStatUpdate.setInt(1, myPartie.nb_joueur() + 1);
                prepStatUpdate.setString(2, uniqueCode);
                prepStatUpdate.executeUpdate();

                myPartie = findByCode(uniqueCode);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myPartie;
    }

    public Partie updateScore(String uniqueCode) {
        Partie myPartie = null;
        System.out.println("uniqueCode : " + uniqueCode);
        try {
            myPartie = findByCode(uniqueCode);
            System.out.println("myPartie : " + myPartie);
            if (myPartie != null) {
                PolyNameDatabase myDatabase = new PolyNameDatabase();
                String requestUpdate = "UPDATE partie SET score = ? WHERE unique_code = ?";
                PreparedStatement prepStatUpdate = myDatabase.prepareStatement(requestUpdate);
                prepStatUpdate.setInt(1, myPartie.score() + 1);
                prepStatUpdate.setString(2, uniqueCode);
                prepStatUpdate.executeUpdate();

                myPartie = findByCode(uniqueCode);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myPartie;
    }

    public int getScore(String uniqueCode) {
        try {
            Partie myPartie = findByCode(uniqueCode);
            return myPartie.score();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    
}