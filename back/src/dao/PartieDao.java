package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
}
