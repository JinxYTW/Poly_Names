import { SSEClient } from '../libs/sse-client.js';
import { HubController } from './controllers/hubController.js';


document.addEventListener('DOMContentLoaded', () => {
    new HubController();
});
