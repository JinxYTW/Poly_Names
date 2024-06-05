package controller;
import java.util.ArrayList;

import dao.CouleurDao;
import models.Couleur;

import webserver.WebServerContext;
import webserver.WebServerResponse;

public class CouleurController {
    public CouleurController(){

    }
    public ArrayList<Couleur> findAll(WebServerContext context){
        ArrayList<Couleur> res=new ArrayList<>();
        try{
            CouleurDao myDao=new CouleurDao();
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
