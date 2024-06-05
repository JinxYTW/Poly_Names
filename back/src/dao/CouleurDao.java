package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.PolyNameDatabase;
import models.Couleur;

public class CouleurDao {
    public CouleurDao(){
    }
    public ArrayList<Couleur> findAll(){
        ArrayList<Couleur> res=new ArrayList<>();
        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM couleur";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                final int id_couleur=results.getInt("id_couleur");
                final String texte= results.getString("texte");
                Couleur myRecord = new Couleur(id_couleur,texte);
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
