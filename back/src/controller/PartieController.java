package controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import dao.PartieDao;
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

    public void createLobby(WebServerContext context) {
        try {
            PartieDao myDao = new PartieDao();
            Partie myPartie = myDao.createLobby();
            WebServerResponse myResponse = context.getResponse();
            if (myPartie != null) {
                myResponse.json(myPartie);
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
