package controller;
import java.util.ArrayList;

import dao.JoueurDao;
import models.Joueur;

import webserver.WebServerContext;
import webserver.WebServerResponse;

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
}
