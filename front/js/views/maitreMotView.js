 class MaitreMotView {
    constructor() {
      this.instructionDiv = document.getElementById('maitremotinstruction');
      this.grilleDiv = document.getElementById('grille');
    }
  
    hideInstructions() {
      this.instructionDiv.style.display = 'none';
      this.grilleDiv.style.display = 'block';
    }

    showGrid() {
      this.grilleDiv.style.display = 'block';
    }
  
    bindScreenClick(handler) {
      document.body.addEventListener('click', handler);
    }

    renderGrid(cards) {
      this.grilleDiv.innerHTML = ''; 
      cards.forEach(card => {
        const cardElement = document.createElement('div');
        cardElement.textContent = card.word.getWord();
        cardElement.style.border = '1px solid black';
        cardElement.style.padding = '10px';
        cardElement.style.margin = '5px';
        cardElement.style.display = 'inline-block';
        this.grilleDiv.appendChild(cardElement);
      });
    }
  }

  export { MaitreMotView}