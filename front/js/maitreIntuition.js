import { SSEClient } from '../libs/sse-client.js';
import { MaitreIntuitionController} from './controllers/maitreIntuitionController.js';

function RetourneCarte(data,myMaitreIntuitionController){
  if(data.enoughWord==true){
    myMaitreIntuitionController.submitCard(data.id_carte,data.mot,data.eta,data.position,data.id_couleur,data.id_mot,data.id_partie,data.uniqueCode)
  }
}
function newIndice(data,myMaitreIntuitionController){
  myMaitreIntuitionController.showIndice(data.indice,data.wordToFindNb)
}
function endGameIntuition(data,myMaitreIntuitionController){
  myMaitreIntuitionController.endGame(data.unique_code)
}
function victoire(data,myMaitreIntuitionController){ // à modifier ------------
  console.log("victoire")
  myMaitreIntuitionController.victoire(data.unique_code)
}


async function run(myMaitreIntuitionController){
  const baseUrl = "localhost:8080"; 

  const sseClientWaiting = new SSEClient(baseUrl);
  try {
      await sseClientWaiting.connect();
      //await sseClient.subscribe("lobbyHost", fonctionHost).then(console.log("abonnement canal lobbyHost"));
      await sseClientWaiting.subscribe("RetourneCarte", (data) => RetourneCarte(data, myMaitreIntuitionController)).then(console.log("RetourneCarte up"));
      await sseClientWaiting.subscribe("newIndice", (data) => newIndice(data, myMaitreIntuitionController)).then(console.log("newIndice up"));
      await sseClientWaiting.subscribe("endGame", (data) => endGameIntuition(data, myMaitreIntuitionController)).then(console.log("endGame up"));
      await sseClientWaiting.subscribe("victoire", (data) => victoire(data, myMaitreIntuitionController)).then(console.log("victoire up"));
      window.addEventListener('unload', () => {
        sseClientWaiting.disconnect();
      });
  } catch (error) {
      console.error("Failed to connect or subscribe to SSE:", error);
  }
}

document.addEventListener('DOMContentLoaded', () => {
    const myMaitreIntuitionController = new MaitreIntuitionController();
    run(myMaitreIntuitionController);
});

