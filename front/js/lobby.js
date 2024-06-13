// /js/lobby.js
import { SSEClient } from '../libs/sse-client.js';
import { LobbyController } from './controllers/lobbyController.js';

function joinLobbyChallenger(data,lobbyController){
  //const data = JSON.parse(event);
  lobbyController.showButton();
}

async function run(lobbyController){
  const baseUrl = "localhost:8080"; 

  const sseClientLobby = new SSEClient(baseUrl);
  try {
      await sseClientLobby.connect();
      //await sseClient.subscribe("lobbyHost", fonctionHost).then(console.log("abonnement canal lobbyHost"));
      await sseClientLobby.subscribe("lobbyChallenger", (data) => joinLobbyChallenger(data, lobbyController));
  } catch (error) {
      console.error("Failed to connect or subscribe to SSE:", error);
  }
}

document.addEventListener('DOMContentLoaded', () => {
  const myLobbyController = new LobbyController(); // Déclaration correcte de la variable
  run(myLobbyController);
});
