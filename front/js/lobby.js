document.addEventListener('DOMContentLoaded', () => {
    const roomLink = document.getElementById('roomLink');
    const startGameBtn = document.getElementById('btn_Start');
    const urlParams = new URLSearchParams(window.location.search);
    const roomId = urlParams.get('room');
  
    if (roomId) {
      roomLink.value = `${window.location.origin}/lobby.html?room=${roomId}`;
    } else {
      window.location.href = '../index.html';
      return;  
    }
  
    // Simuler la connexion de l'autre joueur
    setTimeout(() => {
      startGameBtn.disabled = false;
    }, 3000); // Simule un délai de 3 secondes pour la connexion
  
    startGameBtn.addEventListener('click', () => {
      window.location.href = `selection.html?room=${roomId}`;
    });
  });
  