import { SSEClient } from '../libs/sse-client.js';
import { MaitreIntuitionController} from './controllers/maitreIntuitionController.js';

function roleGivenChallenger(data,myMaitreIntuitionController){
    myMaitreIntuitionController.submitCard(data.id_carte,data.mot,data.eta,data.position,data.id_couleur,data.id_mot,data.id_partie)
}

async function run(myMaitreIntuitionController){
  localStorage.setItem("test","ça marche")
  const baseUrl = "localhost:8080"; 

  const sseClientWaiting = new SSEClient(baseUrl);
  try {
      await sseClientWaiting.connect();
      //await sseClient.subscribe("lobbyHost", fonctionHost).then(console.log("abonnement canal lobbyHost"));
      await sseClientWaiting.subscribe("RetourneCarte", (data) => roleGivenChallenger(data, myMaitreIntuitionController)).then(console.log("RetourneCarte up"));
      window.addEventListener('unload', () => {
        sseClientWaiting.disconnect();
      });
  } catch (error) {
      console.error("Failed to connect or subscribe to SSE:", error);
  }
}

document.addEventListener('DOMContentLoaded', () => {
    localStorage.setItem('test', "test");
    const myMaitreIntuitionController = new MaitreIntuitionController();
    run(myMaitreIntuitionController);
});

