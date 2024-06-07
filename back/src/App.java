import controller.CarteController;
import controller.CouleurController;
import controller.DictionnaireController;
import controller.PartieController;
import controller.TourController;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        WebServer webserver = new WebServer();
        webserver.listen(8080);

        DictionnaireController my_controller_dictionnaire= new DictionnaireController();
        webserver.getRouter().get("/api/dictionnaire",(WebServerContext context) -> { my_controller_dictionnaire.findAll(context); } );
        CouleurController my_controller_couleur= new CouleurController();
        webserver.getRouter().get("/api/couleur",(WebServerContext context) -> { my_controller_couleur.findAll(context); } );
        PartieController my_controller_partie= new PartieController();
        webserver.getRouter().get("/api/partie",(WebServerContext context) -> { my_controller_partie.findAll(context); } );
        TourController my_controller_tour= new TourController();
        webserver.getRouter().get("/api/tour",(WebServerContext context) -> { my_controller_tour.findAll(context); } );
        CarteController my_controller_carte= new CarteController();
        webserver.getRouter().get("/api/generateCarte",(WebServerContext context) -> { my_controller_carte.genererCarte(context); } );



        PartieController my_controller2= new PartieController();
        webserver.getRouter().get("/api/createLobby",(WebServerContext context) -> { my_controller2.createLobby(context); } );     

        System.out.println("Hello, World!");
    }
}
