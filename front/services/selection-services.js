class SelectionServices {
  constructor() {
    
  }

  async chooseRole(role) {
    console.log("ok")
    console.log('role:', role);
    try {
      console.log("ok2")  
      const roomId = this.getRoomIdFromURL();
      const response = await fetch(`http://127.0.0.1:8080/api/chooseRole/${role}/${roomId}`);
      if (!response.ok) {
        throw new Error('Marche pas');
      }
      const data = await response.json();
      const selectedRole = data.role;
      const room = data.room;
      return { selectedRole, room };
    } catch (error) {
      console.error('Erreur dans le choix:', error);
      return null;
    }
  }

  getRoomIdFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('room');
  }
}

  


export {SelectionServices};