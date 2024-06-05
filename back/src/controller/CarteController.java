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
}
