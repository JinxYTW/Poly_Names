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
    public ArrayList<Carte> genererCarte(String uniqueCode){
        ArrayList<Carte> cartes=new ArrayList<>();
        try{
            CarteDao myDao=new CarteDao();
            cartes=myDao.genererCarte(uniqueCode);
            //WebServerResponse myResponse=context.getResponse();
            //myResponse.json("ok,25 cartes en plus dans la bdd");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            
            System.out.println("là");
        }
        return cartes;
    }

    public void getCards(WebServerContext context,String uniqueCode,boolean couleurBool) {
        try{
            CarteDao myDao=new CarteDao();
            ArrayList<Carte> cartes=myDao.findByCode(uniqueCode,couleurBool);
            WebServerResponse myResponse=context.getResponse();
            myResponse.json(cartes);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void getCardsM(WebServerContext context,String uniqueCode) {
        try{
            getCards(context, uniqueCode,true);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void getCardsI(WebServerContext context,String uniqueCode) {
        try{
            getCards(context, uniqueCode,false);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void submitCard(WebServerContext context, String uniqueCode, int position) {
        try {
            CarteDao myDao = new CarteDao();
            myDao.submitCard(uniqueCode, position);
            WebServerResponse myResponse = context.getResponse();
            myResponse.ok("Carte soumise");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            WebServerResponse myResponse = context.getResponse();
            myResponse.json("{\"status\":\"error\"}");
        }
    }
}

    
    
