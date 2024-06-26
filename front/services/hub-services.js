class HubServices{
    constructor(){
    }

    async handleCreateLobby() {
        try {
          const response = await fetch('http://127.0.0.1:8080/api/createLobby', {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
          });
          if (response.status !== 404) {
            const data = await response.json();
            const roomId = data.unique_code; // Utilisez l'ID unique renvoyé par le backend
            window.location.href = `lobby.html?room=${roomId}`;
            
          } else {
            console.error('Failed to create lobby');
          }
        } catch (error) {
          console.error('Error:', error);
        }
      }
}

export {HubServices};