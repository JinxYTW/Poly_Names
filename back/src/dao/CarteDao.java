package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.PolyNameDatabase;
import models.Carte;

public class CarteDao {
    public CarteDao(){
    }
    public ArrayList<Carte> findAll(){
        ArrayList<Carte> res=new ArrayList<>();
        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM carte";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                final int id_carte=results.getInt("id_carte");
                final String mot= results.getString("mot");
                final Boolean etat=results.getBoolean("etat");
                final int id_couleur=results.getInt("id_couleur");
                final int id_mot=results.getInt("id_mot");
                Carte myRecord = new Carte(id_carte,mot,etat,id_couleur,id_mot);
                res.add(myRecord);
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }

    public ArrayList<Carte> genererCarte(){
        ArrayList<Carte> cartes = new ArrayList<>();
        try {
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT texte FROM dictionnaire";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();   
            List<String> mots = new ArrayList<>(); 
            while (results.next()) {
                mots.add(results.getString("texte"));
            }



            
        } catch (Exception e) {
            // TODO: handle exception
        }
    
    return cartes;
    }

    

}
