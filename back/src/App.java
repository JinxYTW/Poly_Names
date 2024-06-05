import java.util.ArrayList;

import controller.CarteController;
import controller.CouleurController;
import controller.DictionnaireController;
import controller.JoueurController;
import controller.PartieController;
import controller.TourController;
import dao.CarteDao;
import dao.CouleurDao;
import dao.DictionnaireDao;
import dao.JoueurDao;
import dao.PartieDao;
import dao.TourDao;
import models.Carte;
import models.Couleur;
import models.Dictionnaire;
import models.Joueur;
import models.Partie;
import models.Tour;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        WebServer webserver = new WebServer();
        webserver.listen(8082);
        DictionnaireController my_controller= new DictionnaireController();
        webserver.getRouter().get("/Dictionnaire",(WebServerContext context) -> { my_controller.findAll(context); } );



        System.out.println("Hello, World!");
    }
}
