package controller;
import java.util.ArrayList;

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
}
