class LobbyView  {
    constructor() {
      this.roomLink = document.getElementById('roomLink');
      this.startGameBtn = document.getElementById('btn_Start');
    }
  
    displayRoomLink(roomId) {
      this.roomLink.value = `${window.location.origin}/front/pages/join.html?room=${roomId}`;
    }
  
    enableStartButton() {
      this.startGameBtn.disabled = false;
    }
  
    bindStartGame(handler) {
      this.startGameBtn.addEventListener('click', handler);
    }
  }

  export {LobbyView};