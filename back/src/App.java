//import controller.CarteController;
//import controller.CouleurController;
import controller.DictionnaireController;
//import controller.JoueurController;
import controller.PartieController;
//import controller.TourController;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        WebServer webserver = new WebServer();
        webserver.listen(8081);

        DictionnaireController my_controller= new DictionnaireController();
        webserver.getRouter().get("/api/dictionnaire",(WebServerContext context) -> { my_controller.findAll(context); } );

        PartieController my_controller2= new PartieController();
        webserver.getRouter().get("/api/createLobby",(WebServerContext context) -> { my_controller2.createLobby(context); } );     

        System.out.println("Hello, World!");
    }
}
