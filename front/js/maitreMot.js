import { MaitreMotController } from './controllers/maitreMotController.js';
import { SSEClient } from '../libs/sse-client.js';

function RetourneCarte(data,myMaitreMotController){
  if(data.enoughWord==true){
    myMaitreMotController.submitCard(data.id_carte,data.mot,data.eta,data.position,data.id_couleur,data.id_mot,data.id_partie,data.uniqueCode)
  }
}
function lastMot(data,myMaitreMotController){
  myMaitreMotController.showButton();
}
function endGameMot(data,myMaitreMotController){
  myMaitreMotController.endGame(data.unique_code)
}

async function run(myMaitreMotController){
  const baseUrl = "localhost:8080"; 

  const sseClientWaiting = new SSEClient(baseUrl);
  try {
      await sseClientWaiting.connect();
      //await sseClient.subscribe("lobbyHost", fonctionHost).then(console.log("abonnement canal lobbyHost"));
      await sseClientWaiting.subscribe("RetourneCarte", (data) => RetourneCarte(data, myMaitreMotController)).then(console.log("RetourneCarte up"));
      await sseClientWaiting.subscribe("endGame", (data) => endGameMot(data, myMaitreMotController)).then(console.log("endGame up"));
      await sseClientWaiting.subscribe("lastMot", (data) => lastMot(data, myMaitreMotController)).then(console.log("lastMot up"));
      window.addEventListener('unload', () => {
        sseClientWaiting.disconnect();
      });
  } catch (error) {
      console.error("Failed to connect or subscribe to SSE:", error);
  }
}

document.addEventListener('DOMContentLoaded', () => {
  const myMaitreMotController = new MaitreMotController();
  run(myMaitreMotController);
});
