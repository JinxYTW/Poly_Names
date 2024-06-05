package controller;
import java.util.ArrayList;

import dao.DictionnaireDao;
import models.Dictionnaire;

import webserver.WebServerContext;
import webserver.WebServerResponse;

public class DictionnaireController {
    public DictionnaireController(){

    }
    public ArrayList<Dictionnaire> findAll(WebServerContext context){
        ArrayList<Dictionnaire> res=new ArrayList<>();
        try{
            DictionnaireDao myDao=new DictionnaireDao();
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
