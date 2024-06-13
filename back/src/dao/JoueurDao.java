package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.PolyNameDatabase;
import models.Joueur;
import models.Partie;


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
            String request="SELECT role FROM joueur WHERE id_partie = (SELECT id_partie FROM partie WHERE unique_code = ?) AND pseudo = 'Host'";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            prepStat.setString(1, unique_code);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                res=results.getString("role");
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
            String request="SELECT role FROM joueur WHERE id_partie = (SELECT id_partie FROM partie WHERE unique_code = ?) AND pseudo = 'Challenger'";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            prepStat.setString(1, unique_code);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                res=results.getString("role");
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }

    public void UpdatePlayer(String unique_code,String role,String pseudo){
        try{
            JoueurDao myJoueurDao=new JoueurDao();
            PartieDao myPartieDao=new PartieDao();
            if (pseudo.equals("Host")){
                System.out.println("pseudo :"+pseudo);
                PolyNameDatabase myDatabase = new PolyNameDatabase();
                String updatePlayer = "UPDATE Joueur SET role = ? WHERE id_partie = (SELECT id_partie FROM Partie WHERE unique_code = ?) AND pseudo = ?";
                java.sql.PreparedStatement prepStatUpdate = myDatabase.prepareStatement(updatePlayer);
                prepStatUpdate.setString(1, role);
                prepStatUpdate.setString(2, unique_code);
                prepStatUpdate.setString(3, pseudo);
                prepStatUpdate.executeUpdate();     
            }
            else{
                Partie myPartie = myPartieDao.findByCode(unique_code);
                myJoueurDao.AddPlayer(pseudo, role,myPartie.id_partie());
            }
        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void AddPlayer(String pseudo, String role, int id_partie){
        try{
            System.out.println(pseudo);
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            String requestAddPlayer = "INSERT INTO joueur (pseudo, role, id_partie) VALUES (?, ?, ?)";
            PreparedStatement prepStatAddPlayer = myDatabase.prepareStatement(requestAddPlayer);
            prepStatAddPlayer.setString(1, pseudo);
            prepStatAddPlayer.setString(2, role); 
            prepStatAddPlayer.setInt(3,id_partie);
            prepStatAddPlayer.executeUpdate();  

            String request="SELECT role FROM joueur WHERE id_partie = ?";
            PreparedStatement prepStat=myDatabase.prepareStatement(request);
            prepStat.setInt(1, id_partie);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                System.out.print("valeur de test : ");
                System.out.println(results.getString("role"));
                }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}

