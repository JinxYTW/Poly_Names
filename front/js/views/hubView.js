class HubView {
    constructor() {
      this.createLobbyBtn = document.getElementById('btn_createlobby');
    }
  
    bindCreateLobby(handler) {
      this.createLobbyBtn.addEventListener('click', handler);
    }
  }

  export {HubView};