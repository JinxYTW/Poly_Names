import { ApplicationMemory} from "./application/application-memory.js"


document.addEventListener('DOMContentLoaded', () => {
    const createGameBtn = document.getElementById('btn_createlobby');
  
    createGameBtn.addEventListener('click', () => {
      print
      const roomId = generateRoomId();
      window.location.href = `lobby.html?room=${roomId}`;
    });
  
    function generateRoomId() {
      return Math.random().toString(36).substring(2, 9);
    }
  });
  


window.addEventListener("load", () => {
    const app = new ApplicationMemory();
})