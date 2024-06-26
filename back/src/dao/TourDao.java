package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



import database.PolyNameDatabase;
import models.Partie;
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
    public Tour getIndice(String uniqueCode){
        Tour res= new Tour(0, null, 0, 0);
        try{
            PartieDao myPartieDao=new PartieDao();
            Partie myPartie=myPartieDao.findByCode(uniqueCode);
            int id =myPartie.id_partie();
            int tour=getMaxTour(uniqueCode);
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM tour WHERE id_partie=? AND tour=? ";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            prepStat.setInt(1, id);
            prepStat.setInt(2, tour);
            ResultSet results = prepStat.executeQuery();   
            while (results.next()){
                final int id_tour=results.getInt("id_tour");
                final String indice= results.getString("indice");
                final int word_to_find_nb=results.getInt("word_to_find_nb");
                final int id_partie=results.getInt("id_partie");
                Tour myRecord = new Tour(id_tour,indice,word_to_find_nb,id_partie);
                res=myRecord;
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }
    public int getWordToGuess(String uniqueCode){
        int res=-1;
        try{
            PartieDao myPartieDao=new PartieDao();
            Partie myPartie=myPartieDao.findByCode(uniqueCode);
            int id =myPartie.id_partie();
            int tour=getMaxTour(uniqueCode);
            PolyNameDatabase my_Database= new PolyNameDatabase();
            String request="SELECT * FROM tour WHERE id_partie=? AND tour=? ";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            prepStat.setInt(1, id);
            prepStat.setInt(2, tour);
            ResultSet results = prepStat.executeQuery();   
            while (results.next()){
                res=results.getInt("word_to_guess");
                }
            return res;
            }
        
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }
    
    public int getMaxTour(String uniqueCode) {
        int res=-1;
        try {            
            PartieDao myPartieDao=new PartieDao();
            Partie myPartie=myPartieDao.findByCode(uniqueCode);
            int id =myPartie.id_partie();
            if (myPartie != null) {
                PolyNameDatabase myDatabase = new PolyNameDatabase();
                String request = "SELECT MAX(tour) AS max_tour FROM tour WHERE id_partie = ?";
                PreparedStatement prepStat = myDatabase.prepareStatement(request);
                prepStat.setInt(1, id);
                ResultSet results = prepStat.executeQuery();   
                while (results.next()){
                    final int tour=results.getInt("max_tour");
                    res=tour;
                    }
                } 
                return res;
            }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public void addTour(String indice, int wordToFindNb, String uniqueCode) {
        try {
            PartieDao myPartieDao = new PartieDao();
            Partie myPartie = myPartieDao.findByCode(uniqueCode);
            int idPartie = myPartie.id_partie();
            int word_to_guess= wordToFindNb + 1;
            int tour=getMaxTour(uniqueCode)+1;
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            String request = "INSERT INTO tour (indice, word_to_find_nb,word_to_guess, id_partie, tour) VALUES (?,?,?, ?, ?)";
            PreparedStatement prepStat = myDatabase.prepareStatement(request);
            prepStat.setString(1, indice);
            prepStat.setInt(2, wordToFindNb);
            prepStat.setInt(3, word_to_guess);
            prepStat.setInt(4, idPartie);
            prepStat.setInt(5, tour);
            prepStat.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Boolean decrementeWordtoGuess( String uniqueCode) {
        try {
            PartieDao myPartieDao = new PartieDao();
            Partie myPartie = myPartieDao.findByCode(uniqueCode);
            int idPartie = myPartie.id_partie();
            int res=0;
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            String request = "SELECT word_to_guess FROM tour WHERE id_partie=?";
            PreparedStatement prepStat = myDatabase.prepareStatement(request);
            prepStat.setInt(1, idPartie); 
            ResultSet results= prepStat.executeQuery();
            while (results.next()){
                final int word_to_guess=results.getInt("word_to_guess");
                res=word_to_guess;
                }
            if (res==0){
                return false;
            }
            else{
                String requestUpdate = "UPDATE tour SET word_to_guess = ? WHERE id_partie = ?";
                PreparedStatement prepStatUpdate = myDatabase.prepareStatement(requestUpdate);
                prepStatUpdate.setInt(1, res- 1);
                prepStatUpdate.setInt(2, idPartie);
                prepStatUpdate.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public int getScoreUpdateValue(String uniqueCode, int tour) {
        
        int res=0;
        try {        

            PartieDao myPartieDao=new PartieDao();
            Partie myPartie=myPartieDao.findByCode(uniqueCode);
            int id =myPartie.id_partie();
            if (myPartie != null) {
                PolyNameDatabase myDatabase = new PolyNameDatabase();
                String request = "SELECT word_to_find_nb,word_to_guess FROM tour WHERE id_partie = ? AND tour= ?";
                PreparedStatement prepStat = myDatabase.prepareStatement(request);
                prepStat.setInt(1, id);
                prepStat.setInt(2, tour);
                ResultSet results = prepStat.executeQuery();   
                while (results.next()){
                    final int word_to_find_nb=results.getInt("word_to_find_nb");
                    final int word_to_guess=results.getInt("word_to_guess");
                    
                    if (word_to_guess==0){
                        res=(word_to_find_nb+1)*(word_to_find_nb+1);
                    }
                    else{
                        res=(word_to_find_nb+1)-word_to_guess;
                    }
                    }
                } 
                return res;
            }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

public void updateScore(String uniqueCode, int tour) {
    try {
        
        PartieDao myPartieDao=new PartieDao();
        Partie myPartie=myPartieDao.findByCode(uniqueCode);
        if (myPartie != null) {
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            String requestUpdate = "UPDATE partie SET score = ? WHERE unique_code = ?";
            PreparedStatement prepStatUpdate = myDatabase.prepareStatement(requestUpdate);
            prepStatUpdate.setInt(1, myPartie.score() + getScoreUpdateValue(uniqueCode, tour));
            prepStatUpdate.setString(2, uniqueCode);
            prepStatUpdate.executeUpdate();

        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
public void endTour(String uniqueCode){
    try {
        PartieDao myPartieDao = new PartieDao();
        Partie myPartie = myPartieDao.findByCode(uniqueCode);
        int idPartie = myPartie.id_partie();
        PolyNameDatabase myDatabase = new PolyNameDatabase();
        String request = "UPDATE tour SET word_to_guess = ? WHERE id_partie=?";
        PreparedStatement prepStat = myDatabase.prepareStatement(request);
        prepStat.setInt(1, 0); 
        prepStat.setInt(2, idPartie); 
        prepStat.executeUpdate();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
}
