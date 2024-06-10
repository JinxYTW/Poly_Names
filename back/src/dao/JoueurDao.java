package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import database.PolyNameDatabase;
import models.Joueur;
import webserver.WebServerContext;

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
    
}

