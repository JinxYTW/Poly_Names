import { LobbyView } from '../views/lobbyView.js';

export class LobbyController {
  constructor() {
    this.view = new LobbyView();

    const urlParams = new URLSearchParams(window.location.search);
    const roomId = urlParams.get('room');

    if (roomId) {
      this.view.displayRoomLink(roomId);
    } else {
      window.location.href = '../index.html';
      return;
    }

    // Simuler la connexion de l'autre joueur
    setTimeout(() => {
      this.view.enableStartButton();
    }, 3000); // Simule un délai de 3 secondes pour la connexion

    this.view.bindStartGame(this.handleStartGame.bind(this, roomId));
  }

  handleStartGame(roomId) {
    window.location.href = `selection.html?room=${roomId}`;
  }
}