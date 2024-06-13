import { WaitingController } from "./controllers/waitingController.js"; 

document.addEventListener('DOMContentLoaded', () => {
    new WaitingController();
});

import { SSEClient } from '../libs/sse-client.js';
import { WaitingController } from "./controllers/waitingController.js"; 

function roleGivenChallenger(data,myWaitingController){
    myWaitingController.giveRoleChallenger(data.role)
}

async function run(myWaitingController){
  const baseUrl = "localhost:8080"; 

  const sseClientWaiting = new SSEClient(baseUrl);
  try {
      await sseClientWaiting.connect();
      //await sseClient.subscribe("lobbyHost", fonctionHost).then(console.log("abonnement canal lobbyHost"));
      await sseClientWaiting.subscribe("roleGivenChallenger", (data) => roleGivenChallenger(data, myWaitingController));
      window.addEventListener('unload', () => {
        sseClientWaiting.disconnect();
      });
  } catch (error) {
      console.error("Failed to connect or subscribe to SSE:", error);
  }
}
document.addEventListener('DOMContentLoaded', () => {
    const myWaitingController = new WaitingController();
    run(myWaitingController);
});

