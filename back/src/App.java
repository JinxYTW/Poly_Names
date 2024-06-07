//import controller.CarteController;
//import controller.CouleurController;
import controller.DictionnaireController;
//import controller.JoueurController;
//import controller.PartieController;
//import controller.TourController;
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
