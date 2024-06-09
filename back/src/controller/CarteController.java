package controller;
import java.util.ArrayList;


import dao.CarteDao;
import models.Carte;

import webserver.WebServerContext;
import webserver.WebServerResponse;

public class CarteController {
    public CarteController(){

    }
    public ArrayList<Carte> findAll(WebServerContext context){
        ArrayList<Carte> res=new ArrayList<>();
        try{
            CarteDao myDao=new CarteDao();
            res=myDao.findAll();
            WebServerResponse myResponse=context.getResponse();
            myResponse.json(res);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    } 
    public ArrayList<Carte> genererCarte(String uniqueCode){
        ArrayList<Carte> cartes=new ArrayList<>();
        try{
            CarteDao myDao=new CarteDao();
            cartes=myDao.genererCarte(uniqueCode);
            System.out.println("Cartes générées pour le code unique " + uniqueCode + ": " + cartes);
            //WebServerResponse myResponse=context.getResponse();
            //myResponse.json("ok,25 cartes en plus dans la bdd");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            
            System.out.println("là");
        }
        return cartes;
    }

    public String getCards(String uniqueCode) {
        System.out.println("uniqueCodeCarteController: " + uniqueCode);
        ArrayList<Carte> cartes = genererCarte(uniqueCode);
        StringBuilder jsonArray = new StringBuilder("[");
        System.out.println("ok");
        for (int i = 0; i < cartes.size(); i++) {
            Carte carte = cartes.get(i);
            jsonArray.append("{")
                    .append("\"id_carte\":").append(carte.id_carte()).append(",")
                    .append("\"mot\":\"").append(carte.mot()).append("\",")
                    .append("\"etat\":").append(carte.etat()).append(",")
                    .append("\"position\":").append(carte.position()).append(",")
                    .append("\"id_couleur\":").append(carte.id_couleur()).append(",")
                    .append("\"id_mot\":").append(carte.id_mot()).append(",")
                    .append("\"id_partie\":").append(carte.id_partie())
                    .append("}");        
            if (i < cartes.size() - 1) {
                jsonArray.append(",");
            }
        }
        jsonArray.append("]");
        System.out.println("Cartes JSON: " + jsonArray.toString());
        return jsonArray.toString();
    }
    
    
}
