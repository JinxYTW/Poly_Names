package controller;
import java.util.ArrayList;

import dao.TourDao;
import models.Tour;

import webserver.WebServerContext;
import webserver.WebServerResponse;

public class TourController {
    public TourController(){

    }
    public ArrayList<Tour> findAll(WebServerContext context){
        ArrayList<Tour> res=new ArrayList<>();
        try{
            TourDao myDao=new TourDao();
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
