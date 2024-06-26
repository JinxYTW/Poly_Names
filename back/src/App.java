import controller.CarteController;

import controller.JoueurController;
import controller.PartieController;
import controller.TourController;
import webserver.WebServer;
import webserver.WebServerContext;
import webserver.WebServerResponse;
import webserver.WebServerSSEEventType;


public class App {
    public static void main(String[] args) throws Exception {

        //-------------- SSE ----------------------------
        WebServer webServer = new WebServer();
        ConnectCallback connectCallback = new ConnectCallback();
        SubscribeCallback subscribeCallback = new SubscribeCallback();
        UnsubscribeCallback unsubscribeCallback = new UnsubscribeCallback();
        webServer.getSSE().addEventListeners(WebServerSSEEventType.CONNECT, connectCallback);
        webServer.getSSE().addEventListeners(WebServerSSEEventType.SUBSCRIBE, subscribeCallback);
        webServer.getSSE().addEventListeners(WebServerSSEEventType.UNSUBSCRIBE, unsubscribeCallback);
        webServer.getRouter().post("/id/:id/:channelId", (WebServerContext context) -> {
            WebServerResponse response = context.getResponse();
            int id = Integer.parseInt(context.getRequest().getParam("id"));
            String channelId = context.getRequest().getParam("channelId");
            System.out.println("id: " + id + " channelId: " + channelId);
            context.getSSE().emit(channelId, "{\"id\":" + id + ", \"channelId\":\"" + channelId + "\"}");
            response.ok("Enchère effectuée avec succès");

        });


        //-------------- Routes ----------------------------


        WebServer webserver = new WebServer();
        webserver.listen(8080);

        

        
        CarteController my_controller_carte= new CarteController();
        PartieController my_controller= new PartieController();
        TourController myTourController= new TourController();

        
        
        webserver.getRouter().get("/api/createLobby", (WebServerContext context) -> {
            String uniqueCode=my_controller.createLobbyCode(context);
            my_controller_carte.genererCarte(uniqueCode);
        });

        

        
        webserver.getRouter().post("/api/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam( "uniqueCode");
            my_controller.createLobby(context, uniqueCode);
        });
        

        webserver.getRouter().post("/api/joinLobby/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam( "uniqueCode");
            my_controller.joinLobby(context, uniqueCode);
        });

        webserver.getRouter().get("/api/getInfo/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller.getInfo(context, uniqueCode);
        });


        webserver.getRouter().get("/api/getCartes/:uniqueCode", (WebServerContext context) -> {
            
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller_carte.getCards(context,uniqueCode,false);
        });
        webserver.getRouter().get("/api/getIndice/:uniqueCode", (WebServerContext context) -> {
            
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            myTourController.getIndice(context,uniqueCode);
        });
        webserver.getRouter().get("/api/getCartesM/:uniqueCode", (WebServerContext context) -> {
            
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller_carte.getCardsM(context,uniqueCode);
        });
        webserver.getRouter().get("/api/getCartesI/:uniqueCode", (WebServerContext context) -> {
            
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller_carte.getCardsI(context,uniqueCode);
        });

        webserver.getRouter().post("/api/submitCard/:uniqueCode/:position", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            int position = Integer.parseInt(context.getRequest().getParam("position"));
            
            my_controller_carte.submitCard(context, uniqueCode, position);
        });
        




        JoueurController my_controller_joueur= new JoueurController();
        webserver.getRouter().get("/api/detect",(WebServerContext context) -> { my_controller_joueur.detect(context); } );

        webserver.getRouter().get("/api/chooseRole/:role/:room", (WebServerContext context) -> {
            String role = context.getRequest().getParam("role");
            String roomId = context.getRequest().getParam("room");
            
            
            my_controller_joueur.chooseRole(context, role, roomId);
        });

        webserver.getRouter().get("/api/getPlayer1Name/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller_joueur.getPlayer1(context, uniqueCode);
        });

        webserver.getRouter().get("/api/getPlayer2Name/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            my_controller_joueur.getPlayer2(context, uniqueCode);
        });

        webserver.getRouter().get("/api/endTour/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            myTourController.endTour(context, uniqueCode);
        });

        TourController my_controller_tour= new TourController();
        webserver.getRouter().get("/api/tour",(WebServerContext context) -> { my_controller_tour.findAll(context); } );

        webserver.getRouter().post("/api/submitHint/:uniqueCode", (WebServerContext context) -> {
            String uniqueCode = context.getRequest().getParam("uniqueCode");
            System.out.println("uniqueCodeRouter : " + uniqueCode);
            my_controller_tour.submitHint(context, uniqueCode);
        });




        System.out.println("Hello, World!");
    }
    
    
}
