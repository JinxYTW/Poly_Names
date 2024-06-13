package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.PolyNameDatabase;
import models.Joueur;


public class JoueurDao {
    public JoueurDao(){
    }
    public ArrayList<Joueur> findAll(){
        ArrayList<Joueur> res=new ArrayList<>();
        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM joueur";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                final int id_joueur=results.getInt("id_joueur");
                final String pseudo= results.getString("pseudo");
                final String role=results.getString("role");
                final int id_partie=results.getInt("id_partie");
                Joueur myRecord = new Joueur(id_joueur,pseudo,role,id_partie);
                res.add(myRecord);
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }

    public String getPlayer1(String unique_code){
        String res="";

        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT pseudo FROM joueur WHERE id_partie = (SELECT id_partie FROM partie WHERE unique_code = ?) AND pseudo = 'Host'";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            prepStat.setString(1, unique_code);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                res=results.getString("pseudo");
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }
    
    public String getPlayer2(String unique_code){
        String res="";

        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT pseudo FROM joueur WHERE id_partie = (SELECT id_partie FROM partie WHERE unique_code = ?) AND pseudo = 'Challenger'";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            prepStat.setString(1, unique_code);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                res=results.getString("pseudo");
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }
}

