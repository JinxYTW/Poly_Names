package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.JsonObject;

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
            // Génère un ID unique de 8 caractères
            String uniqueCode = UUID.randomUUID().toString().substring(0, 8); 
    
            // Initialise la connexion à la base de données
            PolyNameDatabase myDatabase = new PolyNameDatabase();
    
            // Insère une nouvelle partie dans la table 'partie'
            String requestCreate = "INSERT INTO partie (unique_code, score, nb_joueur) VALUES (?, ?, ?)";
            PreparedStatement prepStatCreate = myDatabase.prepareStatement(requestCreate);
            prepStatCreate.setString(1, uniqueCode);
            prepStatCreate.setInt(2, 0);
            prepStatCreate.setInt(3, 1); // Initialise le nombre de joueurs à 1
            prepStatCreate.executeUpdate();

            Partie partieInitial= findByCode(uniqueCode);
            String requestCreateTour = "INSERT INTO Tour (tour, indice, word_to_find_nb,word_to_guess,id_partie) VALUES (?, ?, ?,?,?)";
            PreparedStatement prepStatCreateTour = myDatabase.prepareStatement(requestCreateTour);
            prepStatCreateTour.setInt(1, 0);
            prepStatCreateTour.setString(2, "initialisation");
            prepStatCreateTour.setInt(3, 0); // Initialise le nombre de joueurs à 1
            prepStatCreateTour.setInt(4, 0);
            prepStatCreateTour.setInt(5, partieInitial.id_partie());
            prepStatCreateTour.executeUpdate();
            
    
            // Récupère la partie nouvellement créée
            myPartie = findByCode(uniqueCode);
    
            // Ajoute un joueur 'Host' à la table 'joueur'
            if (myPartie != null) {
                 
                JoueurDao myJoueurDao=new JoueurDao();
                myJoueurDao.AddPlayer("Host", null,myPartie.id_partie() );
            }
    
            return myPartie;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myPartie;
    }

    public Partie joinLobby(String uniqueCode) {
        Partie myPartie = null;
        
        try {
            myPartie = findByCode(uniqueCode);
            if (myPartie != null && myPartie.nb_joueur() < 2) {
                PolyNameDatabase myDatabase = new PolyNameDatabase();
                String requestUpdate = "UPDATE partie SET nb_joueur = ? WHERE unique_code = ?";
                PreparedStatement prepStatUpdate = myDatabase.prepareStatement(requestUpdate);
                prepStatUpdate.setInt(1, myPartie.nb_joueur() + 1);
                prepStatUpdate.setString(2, uniqueCode);
                prepStatUpdate.executeUpdate();


            }else{
                System.out.println("La partie est pleine ou introuvable.");
                myPartie = null;

                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            myPartie = null;
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
    public void endGame(String uniqueCode){
        try {
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            System.out.println("fin de partie");
            String request = "UPDATE partie SET score = ? WHERE unique_code=?";
            PreparedStatement prepStat = myDatabase.prepareStatement(request);
            prepStat.setInt(1, -1); 
            prepStat.setString(2, uniqueCode); 
            prepStat.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

    