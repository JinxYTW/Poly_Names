package controller;
import java.util.ArrayList;

import dao.JoueurDao;

import models.Joueur;

import webserver.WebServerContext;
import webserver.WebServerResponse;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;


public class JoueurController {
    public JoueurController(){

    }
    public ArrayList<Joueur> findAll(WebServerContext context){
        ArrayList<Joueur> res=new ArrayList<>();
        try{
            JoueurDao myDao=new JoueurDao();
            res=myDao.findAll();
            WebServerResponse myResponse=context.getResponse();
            myResponse.json(res);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    } 

    public Map<Integer, Boolean> detect(WebServerContext context){
        Map<Integer, Boolean> dictionary = new HashMap<>();
        try{
            WebServerResponse myResponse=context.getResponse();
            JoueurDao myDao=new JoueurDao();
            ArrayList<Joueur> joueurs=myDao.findAll();
            for (int i=0;i<joueurs.size();i++){
                int id=joueurs.get(i).id_partie();
                if (dictionary.containsKey(id)){
                    dictionary.put(id,true);
                }
                else dictionary.put(id,false);
            }
            myResponse.json(dictionary);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return dictionary;
    } 

    public void chooseRole(WebServerContext context, String role, String roomId) {
        
        try {
            
    
            if ("randomchoice".equals(role)) {
                int number = (int) (Math.random() * 2);
                if (number == 0) {
                    role = "maitreintuition";
                } else {
                    role = "maitremot";
                }
            }
            // Mettre à jour la base de données ici
            JoueurDao myDao=new JoueurDao();
            myDao.UpdatePlayer(roomId, role, "Host");
            if (role.equals("maitreintuition")){
                myDao.UpdatePlayer(roomId, "maitremot", "Challenger");
                
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("roomId", roomId);
                jsonObject.addProperty("role", "maitremot");
                context.getSSE().emit("roleGivenChallenger",jsonObject);
            }
            else if (role.equals("maitremot")){
                myDao.UpdatePlayer(roomId, "maitreintuition", "Challenger");
                
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("roomId", roomId);
                jsonObject.addProperty("role", "maitreintuition");
                context.getSSE().emit("roleGivenChallenger",jsonObject);
            }
            Map<String, String> response = new HashMap<>();
            response.put("role", role);
            response.put("room", roomId);
    
            context.getResponse().json(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
    
        }
    }

    public String getPlayer1(WebServerContext context, String unique_code){
        String res="";
        try{
            JoueurDao myDao=new JoueurDao();
            res=myDao.getPlayer1(unique_code);
            WebServerResponse myResponse=context.getResponse();
            myResponse.json(res);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public String getPlayer2(WebServerContext context, String unique_code){
        String res="";
        try{
            JoueurDao myDao=new JoueurDao();
            res=myDao.getPlayer2(unique_code);
            WebServerResponse myResponse=context.getResponse();
            myResponse.json(res);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    
}
