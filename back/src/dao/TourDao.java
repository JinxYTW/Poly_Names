package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.PolyNameDatabase;
import models.Tour;

public class TourDao {
    public TourDao(){
    }
    public ArrayList<Tour> findAll(){
        ArrayList<Tour> res=new ArrayList<>();
        try{
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM tour";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                final int id_tour=results.getInt("id_tour");
                final String indice= results.getString("indice");
                final int word_to_find_nb=results.getInt("word_to_find_nb");
                final int id_partie=results.getInt("id_partie");
                Tour myRecord = new Tour(id_tour,indice,word_to_find_nb,id_partie);
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
