 class MaitreMotView {
    constructor() {
      this.instructionDiv = document.getElementById('maitremotinstruction');
      this.grilleDiv = document.getElementById('grille');
      this.hint = document.getElementById('hint');
      this.nbMots = document.getElementById('nbMots');
      this.btn_definir = document.getElementById('btn_Definir');

      this.hideGameElements();
      
    }
  
    hideInstructions() {
      this.instructionDiv.style.display = 'none';
      this.grilleDiv.style.display = 'block';
      
      
    }

    showGrid() {
      this.grilleDiv.style.display = 'block';
      this.showGameElements();
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

    hideGameElements() {
      this.hint.style.display = 'none';
      this.nbMots.style.display = 'none';
      this.btn_definir.style.display = 'none';
    }
  
    showGameElements() {
      this.hint.style.display = 'inline';
      this.nbMots.style.display = 'inline';
      this.btn_definir.style.display = 'inline';
    }

    
  }

  export { MaitreMotView}