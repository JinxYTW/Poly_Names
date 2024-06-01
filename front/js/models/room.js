 class Room {
    constructor() {
      this.roomId = this.generateRoomId();
    }
  
    generateRoomId() {
      return Math.random().toString(36).substring(2, 9);
    }
  }

  export{Room};