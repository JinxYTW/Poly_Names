import { SSEClient } from '../libs/sse-client.js';
import { WaitingController } from "./controllers/waitingController.js"; 

function roleGivenChallenger(data,myWaitingController){
    
    localStorage.setItem('test', "test");
    myWaitingController.giveRoleChallenger(data.role)
}

async function run(myWaitingController){
    localStorage.setItem('test', "test");
  const baseUrl = "localhost:8080/waitingSSE"; 

  const sseClientWaiting = new SSEClient(baseUrl);
  try {
      await sseClientWaiting.connect();
      //await sseClient.subscribe("lobbyHost", fonctionHost).then(console.log("abonnement canal lobbyHost"));
      await sseClientWaiting.subscribe("roleGivenChallenger", (data) => roleGivenChallenger(data, myWaitingController)).then(console.log("roleGivenChallenger up"));
      window.addEventListener('unload', () => {
        sseClientWaiting.disconnect();
      });
  } catch (error) {
      console.error("Failed to connect or subscribe to SSE:", error);
  }
}
document.addEventListener('DOMContentLoaded', () => {
    localStorage.setItem('test', "test");
    const myWaitingController = new WaitingController();
    run(myWaitingController);
});

