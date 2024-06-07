package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.PolyNameDatabase;
import models.Dictionnaire;

public class DictionnaireDao {
    public DictionnaireDao(){
    }
    public ArrayList<Dictionnaire> findAll(){
        ArrayList<Dictionnaire> res=new ArrayList<>();
        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM dictionnaire";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                final int id_mot=results.getInt("id_mot");
                final String texte= results.getString("texte");
                Dictionnaire myRecord = new Dictionnaire(id_mot,texte);
                res.add(myRecord);
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }

    public int getId(String nom){
        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM dictionnaire WHERE texte = '" + nom + "'";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();   
            while (results.next()){
                final int res =results.getInt("id_mot") ;
                return res;
                }
            return 1;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
        
    }
}