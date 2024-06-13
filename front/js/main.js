import { SSEClient } from '../libs/sse-client.js';
import { HubController } from './controllers/hubController.js';

function fonction(event){
    const data = JSON.parse(event);
    localStorage.setItem("UniqueCode",data.UniqueCode);
    
    localStorage.setItem("Joueur",data.Joueur);
}

async function run(){
    // Assurez-vous que l'URL est correctement formée
    const baseUrl = "localhost:8080"; // URL de base correcte

    const sseClient = new SSEClient(baseUrl);
    try {
        await sseClient.connect();
        await sseClient.subscribe("lobby", fonction).then(console.log("abonnement canal lobby"));
    } catch (error) {
        console.error("Failed to connect or subscribe to SSE:", error);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new HubController();
    run();
});
