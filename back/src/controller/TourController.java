package controller;
import java.util.ArrayList;

import com.google.gson.JsonObject;

import dao.TourDao;
import models.Tour;

import webserver.WebServerContext;
import webserver.WebServerResponse;

import models.HintRequestBody;

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

    public void submitHint(WebServerContext context, String uniqueCode) {
        try {
            HintRequestBody requestBody = context.getRequest().extractBody(HintRequestBody.class);
            
    
            // Extraction des valeurs spécifiques du JSON
            String indice = requestBody.indice();
            int wordToFindNb = requestBody.wordToFindNb();
    
            TourDao myDao = new TourDao();
            myDao.addTour(indice, wordToFindNb, uniqueCode);
    
            WebServerResponse myResponse = context.getResponse();
            myResponse.ok("Indice ajouté");
            
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("indice", indice);
            jsonObject.addProperty("wordToFindNb", wordToFindNb);
            context.getSSE().emit("newIndice",jsonObject);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            WebServerResponse myResponse = context.getResponse();
            myResponse.json("{\"status\":\"error\"}");
        }
    }
    public void getIndice(WebServerContext context, String uniqueCode) {
        try {
            TourDao myDao = new TourDao();
            Tour myTour=myDao.getIndice(uniqueCode);
    
            WebServerResponse myResponse = context.getResponse();
            myResponse.json(myTour);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            WebServerResponse myResponse = context.getResponse();
            myResponse.json("{\"status\":\"error\"}");
        }
    }
}
