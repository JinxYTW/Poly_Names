package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import dao.DictionnaireDao;

import database.PolyNameDatabase;
import models.Carte;
import models.Dictionnaire;

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
                final int position=results.getInt("position");
                final int id_couleur=results.getInt("id_couleur");
                final int id_mot=results.getInt("id_mot");
                final int id_partie=results.getInt("id_partie");
                Carte myRecord= new Carte(id_carte,mot,etat,position,id_couleur,id_mot,id_partie);
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
            DictionnaireDao myDao=new DictionnaireDao();
            ArrayList<Dictionnaire> mots =myDao.findAll();
            Collections.shuffle(mots, new Random());
            for (int i = 0; i < 25; i++) {
                Dictionnaire dict = mots.get(i);
                
                PolyNameDatabase myDatabase = new PolyNameDatabase();
                String requestCreate = "INSERT INTO Carte (mot, etat, position, id_couleur, id_mot, id_partie) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement prepStatCreate = myDatabase.prepareStatement(requestCreate);
                prepStatCreate.setString(1, dict.texte());
                prepStatCreate.setBoolean(2, false);
                prepStatCreate.setInt(3, i);
                prepStatCreate.setInt(4, 1);
                prepStatCreate.setInt(5, 1);
                prepStatCreate.setInt(6, 1);
                prepStatCreate.executeUpdate();
            }

            return cartes;
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("ici");
            return cartes;
        }
        
    }
}
