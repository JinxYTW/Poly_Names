import controller.CarteController;
import controller.CouleurController;
import controller.DictionnaireController;
import controller.JoueurController;
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
        
        CarteController my_controller_carte= new CarteController();

        PartieController my_controller= new PartieController();
        webserver.getRouter().get("/api/createLobby", (WebServerContext context) -> {
            String uniqueCode = my_controller.createLobbyCode(context);
            my_controller_carte.genererCarte(uniqueCode);
            webserver.getRouter().get("/api/" + uniqueCode, (WebServerContext codeContext) -> {my_controller.createLobby(codeContext, uniqueCode); });
        });

        

        

        

        webserver.getRouter().get("/api/joinLobby/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam( "uniqueCode");
            my_controller.joinLobby(context, uniqueCode);
        });

        webserver.getRouter().get("/api/updateScore/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller.updateScore(context, uniqueCode);
        });

        webserver.getRouter().get("/api/getInfo/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller.getInfo(context, uniqueCode);
        });




        webserver.getRouter().get("/api/getCartes/:uniqueCode", (WebServerContext context) -> {
            System.out.println("uniqueCodeRouter : " + context.getRequest().getParam("uniqueCode"));
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller_carte.getCards(context,uniqueCode);
        });
        




        JoueurController my_Controller3= new JoueurController();
        webserver.getRouter().get("/api/detect",(WebServerContext context) -> { my_Controller3.detect(context); } );

        webserver.getRouter().get("/api/chooseRole/:role/:room", (WebServerContext context) -> {
            String role = context.getRequest().getParam("role");
            System.out.println("roleRouter : " + role);
            String roomId = context.getRequest().getParam("room");
            System.out.println("roomRouter : " + roomId);
            
            my_Controller3.chooseRole(context, role, roomId);
        });

        TourController my_controller_tour= new TourController();
        webserver.getRouter().get("/api/tour",(WebServerContext context) -> { my_controller_tour.findAll(context); } );

        webserver.getRouter().get("/api/submitHint/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller_tour.submitHint(context, uniqueCode);
        });



        System.out.println("Hello, World!");
    }
    
    
}
