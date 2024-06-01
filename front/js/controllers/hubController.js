import { Room } from '../models/room.js';
import { HubView } from '../views/hubView.js';

export class HubController {
  constructor() {
    this.room = new Room();
    this.view = new HubView();

    this.view.bindCreateLobby(this.handleCreateLobby.bind(this));
  }

  handleCreateLobby() {
    const roomId = this.room.roomId;
    window.location.href = `lobby.html?room=${roomId}`;
  }
}