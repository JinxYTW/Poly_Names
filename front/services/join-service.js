class joinService {
    constructor() {}

    async handleJoinLobby(uniqueCode) {
        console.log(`Joining lobby with code: ${uniqueCode}`);
        try {
            
            const response = await fetch(`http://127.0.0.1:8080/api/joinLobby?room=${uniqueCode}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }     
            });
            
            console.log("ok")
            console.log(`Response status: ${response.status}`);
            if (response.ok) {
                const data = await response.json();
                console.log(`Lobby rejoint : ${data.unique_code}`);
                const roomId = data.unique_code;
                window.location.href = `lobby.html?room=${roomId}`;
            } else {
                console.error('Lobby not found');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
}

export { joinService };
