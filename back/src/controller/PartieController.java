package controller;
import java.util.ArrayList;

import com.google.gson.JsonObject;

import dao.PartieDao;
import dao.TourDao;
import models.Partie;

import webserver.WebServerContext;
import webserver.WebServerResponse;

public class PartieController {
    public PartieController(){

    }
    public ArrayList<Partie> findAll(WebServerContext context){
        
        ArrayList<Partie> res=new ArrayList<>();
        try{
            PartieDao myDao=new PartieDao();
            res=myDao.findAll();
            WebServerResponse myResponse=context.getResponse();
            myResponse.json(res);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    } 

    public String createLobbyCode(WebServerContext context) {
        WebServerResponse myResponse = context.getResponse();
        try {
            PartieDao myDao = new PartieDao();
            Partie myPartie = myDao.createLobbyCode();
            
            myResponse.json(myPartie);
            if (myPartie != null) {
                String code=myPartie.unique_code();
                myResponse.json("{ \"unique_code\": \"" + code + "\" }");

                return myPartie.unique_code();
            } else {
                myResponse.serverError("{ \"error\": \"Erreur lors de la création de la partie.\" }");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            myResponse.serverError("{ \"error\": \"Erreur lors de la création de la partie.\" }");
        }
        return null;
    }
    public void createLobby(WebServerContext context,String uniqueCode) {
        try {
            System.err.println("ça marche");
            PartieDao myDao = new PartieDao();
            Partie myPartie = myDao.findByCode(uniqueCode);
            WebServerResponse myResponse = context.getResponse();
            if (myPartie != null) {
                myResponse.json("Cest la partie qui est au code suivant :" + myPartie.unique_code());
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void joinLobby(WebServerContext context, String uniqueCode) {
        try {
            PartieDao myDao = new PartieDao();
            Partie myPartie = myDao.joinLobby(uniqueCode);
            WebServerResponse myResponse = context.getResponse();
            
            if (myPartie != null) {
                String code=myPartie.unique_code();
                myResponse.json(code);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("UniqueCode", code);
                jsonObject.addProperty("Joueur", "Challenger");
                context.getSSE().emit("lobbyChallenger",jsonObject);
            } else {
                myResponse.status(404, "Trop de joueurs dans la partie");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateScore(WebServerContext context, String uniqueCode) {
        try {
            PartieDao myDao = new PartieDao();
            Partie myPartie = myDao.updateScore(uniqueCode);
            WebServerResponse myResponse = context.getResponse();
            if (myPartie != null) {
                myResponse.json("Cest la partie qui est au code suivant :" + myPartie.unique_code());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void getInfo(WebServerContext context, String uniqueCode){
        try {
            PartieDao myPartieDao = new PartieDao();
            TourDao myTourDao= new TourDao();
            int score = myPartieDao.getScore(uniqueCode);
            int tour = myTourDao.getMaxTour(uniqueCode);
            WebServerResponse myResponse = context.getResponse();
            String res="Score : "+score+" Tour : "+tour;
            myResponse.json(res);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
