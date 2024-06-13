import { SSEClient } from '../libs/sse-client.js';
import { HubController } from './controllers/hubController.js';

function joinLobbyChallenger(data){
  //const data = JSON.parse(event);
  localStorage.setItem("UniqueCode Challenger",data.UniqueCode);
  
  localStorage.setItem("Joueur Challenger",data.Joueur);
}

async function run(){
    const baseUrl = "localhost:8080"; 

    const sseClientLobby = new SSEClient(baseUrl);
    try {
        await sseClientLobby.connect();
        //await sseClient.subscribe("lobbyHost", fonctionHost).then(console.log("abonnement canal lobbyHost"));
        
        await sseClientLobby.subscribe("lobbyChallenger", joinLobbyChallenger).then(console.log("abonnement canal lobbyChallenger"));
    } catch (error) {
        console.error("Failed to connect or subscribe to SSE:", error);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new HubController();
    //run();
});
