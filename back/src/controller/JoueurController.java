package controller;
import java.util.ArrayList;

import dao.JoueurDao;
import models.Joueur;

import webserver.WebServerContext;
import webserver.WebServerResponse;
import java.util.HashMap;
import java.util.Map;


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
        System.out.println("chooseRoleController");
        try {
            System.out.println("role : " + role);
            System.out.println("room : " + roomId);
    
            if ("randomchoice".equals(role)) {
                int number = (int) (Math.random() * 2);
                if (number == 0) {
                    role = "maitreintuition";
                } else {
                    role = "maitremot";
                }
            }
    
            // Metttre à jour la base de données ici
    
            Map<String, String> response = new HashMap<>();
            response.put("role", role);
            response.put("room", roomId);
    
            context.getResponse().json(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
    
        }
    }
}
