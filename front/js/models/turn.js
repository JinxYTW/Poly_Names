class Turn {
    constructor() {
      this.currentTurn = 0;
    }
  
    nextTurn() {
      this.currentTurn += 1;
    }
  
    getTurn() {
      return this.currentTurn;
    }
  }
  
  export { Turn };
  