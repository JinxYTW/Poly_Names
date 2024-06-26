package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import database.PolyNameDatabase;
import models.Carte;
import models.Dictionnaire;
import models.Partie;

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
    public ArrayList<Carte> findByCode(String uniqueCode,Boolean couleurBool){
        ArrayList<Carte> res=new ArrayList<>();
        try{
            PolyNameDatabase myDatabase= new PolyNameDatabase();
            PartieDao myPartieDao=new PartieDao();
            Partie myPartie=myPartieDao.findByCode(uniqueCode);
            String request="SELECT * FROM carte WHERE id_partie = ?";
            PreparedStatement prepStatSelect = myDatabase.prepareStatement(request);
            prepStatSelect.setInt(1, myPartie.id_partie());
            ResultSet results = prepStatSelect.executeQuery();      
            while (results.next()){
                final int id_carte=results.getInt("id_carte");
                final String mot= results.getString("mot");
                final Boolean etat=results.getBoolean("etat");
                final int position=results.getInt("position");
                final int id_mot=results.getInt("id_mot");
                final int id_partie=results.getInt("id_partie");
                if (couleurBool){
                    final int id_couleur=results.getInt("id_couleur");
                    Carte myRecord= new Carte(id_carte,mot,etat,position,id_couleur,id_mot,id_partie);
                    res.add(myRecord);
                }
                else{
                    Carte myRecord= new Carte(id_carte,mot,etat,position,4,id_mot,id_partie);
                    res.add(myRecord);
                }
                
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }

    public Carte findByCodeAndPosition(String uniqueCode,int pos, Boolean couleurBool){
        Carte res =new Carte(0,"",false,0,0,0,0);
        try{
            PolyNameDatabase myDatabase= new PolyNameDatabase();
            PartieDao myPartieDao=new PartieDao();
            Partie myPartie=myPartieDao.findByCode(uniqueCode);
            String request="SELECT * FROM carte WHERE id_partie = ?";
            PreparedStatement prepStatSelect = myDatabase.prepareStatement(request);
            prepStatSelect.setInt(1, myPartie.id_partie());
            ResultSet results = prepStatSelect.executeQuery();      
            while (results.next()){
                final int id_carte=results.getInt("id_carte");
                final String mot= results.getString("mot");
                final Boolean etat=results.getBoolean("etat");
                final int position=results.getInt("position");
                final int id_mot=results.getInt("id_mot");
                final int id_partie=results.getInt("id_partie");
                if (couleurBool){
                    final int id_couleur=results.getInt("id_couleur");
                    if (position==pos){
                        Carte myRecord= new Carte(id_carte,mot,etat,position,id_couleur,id_mot,id_partie);
                        res=myRecord;
                    }
                }
                else{
                    if (position==pos){
                        Carte myRecord= new Carte(id_carte,mot,etat,position,2,id_mot,id_partie);
                        res=myRecord;
                    }
                }
                
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }
    

    public ArrayList<Carte> genererCarte(String uniqueCode){
        ArrayList<Carte> cartes = new ArrayList<>();
        try {
            
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            DictionnaireDao myDictionnaireDao=new DictionnaireDao();
            ArrayList<Dictionnaire> mots =myDictionnaireDao.findAll();
            PartieDao myPartieDao=new PartieDao();
            Partie partie =myPartieDao.findByCode(uniqueCode);
            int id_partie =partie.id_partie();
            int couleur;
            int id_carte=-1;
            Collections.shuffle(mots, new Random());

            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 25; i++) {
                numbers.add(i);
            }
            Collections.shuffle(numbers, new Random());
            ArrayList<Integer> numbersBlue = new ArrayList<>(numbers.subList(0, 8));
            ArrayList<Integer> numbersBlack = new ArrayList<>(numbers.subList(8, 10));

            for (int i = 0; i < 25; i++) {
                if (numbersBlue.contains(i))couleur=1;
                else if (numbersBlack.contains(i)) couleur=3;
                else couleur =2;
                Dictionnaire dict = mots.get(i);
                String texte=dict.texte();
                int id_mot=myDictionnaireDao.getId(texte);

                String requestCreate = "INSERT INTO Carte (mot, etat, position, id_couleur, id_mot, id_partie) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement prepStatCreate = myDatabase.prepareStatement(requestCreate);
                prepStatCreate.setString(1, texte);
                prepStatCreate.setBoolean(2, false);
                prepStatCreate.setInt(3, i);
                prepStatCreate.setInt(4, couleur);
                prepStatCreate.setInt(5, id_mot);
                prepStatCreate.setInt(6, id_partie);
                prepStatCreate.executeUpdate();

                // Ajoutez la carte à la liste après insertion
                String requestSelect = "SELECT id_carte FROM Carte WHERE mot = ? AND id_partie = ?";
                PreparedStatement prepStatSelect = myDatabase.prepareStatement(requestSelect);
                prepStatSelect.setString(1, texte);
                prepStatSelect.setInt(2, id_partie);
                ResultSet results  = prepStatSelect.executeQuery();
                while (results.next()){
                    id_carte=results.getInt("id_carte");
                }
                Carte newCarte = new Carte(id_carte, texte, false, i, couleur, id_mot, id_partie);
                cartes.add(newCarte);
            }

            return cartes;
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
            return cartes;
        }
        
    }

    public void submitCard(String uniqueCode, int position) {
        try {
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            PartieDao myPartieDao = new PartieDao();
            Partie partie = myPartieDao.findByCode(uniqueCode);
            int id_partie = partie.id_partie();
            
            String request = "UPDATE Carte SET etat = true WHERE id_partie = ? AND position = ?";
            PreparedStatement prepStat = myDatabase.prepareStatement(request);
            prepStat.setInt(1, id_partie);
            prepStat.setInt(2, position);
            prepStat.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Boolean isPartieFinish(String uniqueCode){
        try {
            PolyNameDatabase myDatabase = new PolyNameDatabase();
            PartieDao myPartieDao = new PartieDao();
            Partie partie = myPartieDao.findByCode(uniqueCode);
            int id_partie = partie.id_partie();
            

            String request = "SELECT * FROM Carte WHERE id_couleur = ? AND etat = ? AND id_partie= ?";
            
            PreparedStatement prepStat = myDatabase.prepareStatement(request);
            prepStat.setInt(1, 1);
            prepStat.setBoolean(2, false);
            prepStat.setInt(3, id_partie);
            ResultSet result=prepStat.executeQuery();
            if (!result.isBeforeFirst()) {
                System.out.println("la partie est gagné ");
                return true;
            } else {return false;}

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
