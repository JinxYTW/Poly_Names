class MaitreMotView {
  constructor() {
    this.instructionDiv = document.getElementById('maitremotinstruction');
    this.grilleDiv = document.getElementById('grille');
    this.cardsContainer = this.grilleDiv.querySelector('.cards');
    this.hint = document.getElementById('hint');
    this.nbMots = document.getElementById('nbMots');
    this.btn_definir = document.getElementById('btn_Definir');

    this.hideGameElements();
  }

  hideInstructions() {
    this.instructionDiv.classList.add('hidden');
    this.grilleDiv.classList.remove('hidden');
  }

  showGrid() {
    this.grilleDiv.classList.remove('hidden');
    this.showGameElements();
  }

  bindScreenClick(handler) {
    document.body.addEventListener('click', handler);
  }

  renderGrid(cards) {
    console.log("Rendu de la carte ",cards);
    this.cardsContainer.innerHTML = '';
    cards.forEach((card, index) => {
      const cardElement = document.createElement('div');
      cardElement.classList.add('card');
      cardElement.textContent = card.word.getWord();
      cardElement.dataset.index = index;
      this.cardsContainer.appendChild(cardElement);
    });
  }

  hideGameElements() {
    this.hint.classList.add('hidden');
    this.nbMots.classList.add('hidden');
    this.btn_definir.classList.add('hidden');
  }

  showGameElements() {
    this.hint.classList.remove('hidden');
    this.nbMots.classList.remove('hidden');
    this.btn_definir.classList.remove('hidden');
  }
}

export { MaitreMotView };
