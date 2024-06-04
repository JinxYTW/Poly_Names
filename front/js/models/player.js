class Player {
    constructor(name) {
      this.name = name;
      this.score = 0;
    }
  
    updateScore(points) {
      this.score += points;
    }
  
    getScore() {
      return this.score;
    }
  
    getName() {
      return this.name;
    }
  }
  
  export { Player };
  