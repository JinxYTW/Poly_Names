import {HubServices} from '../../services/hub-services.js';
class HubView {
    constructor() {
      this.createLobbyBtn = document.getElementById('btn_createlobby');
    }
  
    async createGame() {
      const hubService = new HubServices();
      const roomId = await hubService.handleCreateLobby();
      if (roomId) {
          window.location.href = `lobby.html?room=${roomId}`;
      }
  }

  bindCreateLobby() {
    this.createLobbyBtn.addEventListener('click', () => this.createGame());
}
  }

  export {HubView};