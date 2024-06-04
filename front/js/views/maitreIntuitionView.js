class MaitreIntuitionView {
    constructor() {
      this.instructionDiv = document.getElementById('maitreintuitioninstruction');
      this.grilleDiv = document.getElementById('grille');
      this.cardsContainer = this.grilleDiv.querySelector('.cards');
      this.score = document.getElementById('score');
      this.turn = document.getElementById('turn');
      this.playerName1 = document.getElementById('playerName1');
      this.playerName2 = document.getElementById('playerName2');
      this.chat = document.getElementById('chat');
      this.chatContent = document.getElementById('chatContent');
  
      this.hideGameElements();
    }
  
    hideInstructions() {
      console.log("Masquage des instructions");
      this.instructionDiv.classList.add('hidden');
      this.grilleDiv.classList.remove('hidden');
    }
  
    showGrid() {
      console.log("Affichage de la grille");
      this.grilleDiv.style.display = 'block';
      this.showGameElements();
    }
  
    bindScreenClick(handler) {
      document.body.addEventListener('click', handler);
    }
  
    
  
    renderGrid(cards) {
      console.log("Rendu de la carte", cards);
      this.cardsContainer.innerHTML = '';
      cards.forEach((card, index) => {
        const cardElement = document.createElement('div');
        cardElement.classList.add('card');
        cardElement.textContent = card.word.getWord();
        cardElement.dataset.index = index;
        this.cardsContainer.appendChild(cardElement);
      });
    }
  
    updatePlayersName(player1Name, player2Name) {
      this.playerName1.textContent = player1Name;
      this.playerName2.textContent = player2Name;
    }
  
    updateScore(player2Score) {
      this.score.textContent = `Score: ${player2Score}`;
    }
  
    updateTurn(turn) {
      this.turn.textContent = `Tour: ${turn}`;
    }
  
    hideGameElements() {
      this.score.classList.add('hidden');
      this.turn.classList.add('hidden');
      this.playerName1.classList.add('hidden');
      this.playerName2.classList.add('hidden');
      this.chat.classList.add('hidden');
      this.chatContent.classList.add('hidden');
    }
  
    showGameElements() {
      this.score.classList.remove('hidden');
      this.turn.classList.remove('hidden');
      this.playerName1.classList.remove('hidden');
      this.playerName2.classList.remove('hidden');
      this.chat.classList.remove('hidden');
      this.chatContent.classList.remove('hidden');
    }
  
}
  
  export { MaitreIntuitionView };
  