class joinService {
    constructor() {}

    async handleJoinLobby(uniqueCode) {
        console.log(`Joining lobby with code: ${uniqueCode}`);
    try {
        const response = await fetch(`http://127.0.0.1:8080/api/joinLobby/${uniqueCode}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }     
        });

        console.log("Response status: ", response.status);
        if (response.ok) {
            const data = await response.json();
            console.log('Data : ', data);
            console.log(`Lobby rejoint : ${data}`);
            const roomId = data;
            console.log(`Room ID: ${roomId}`);
            window.location.href = `waitingscreen.html?room=${roomId}`;
        } else if (response.status === 404) {
            console.error('Lobby not found');
            window.location.href = `cantjoin.html`;
        } else {
            console.error('Internal Server Error');
            window.location.href = `cantjoin.html`;
        }
    } catch (error) {
        console.error('Error:', error);
        window.location.href = `cantjoin.html`;
    }
    }
}

export { joinService };
