import { joinService } from '../../services/join-service.js';

class joinView {
    constructor() {
        this.service = new joinService();
    }

    async handlePageLoad() {
        const params = new URLSearchParams(window.location.search);
        const uniqueCode = params.get('room');
        console.log(`Code de l'url: ${uniqueCode}`);
        
        if (uniqueCode) {
            const roomId = await this.service.handleJoinLobby(uniqueCode);
            }
             else {
            console.error('No room code provided');
        }
    }
    

    
}

export { joinView };
