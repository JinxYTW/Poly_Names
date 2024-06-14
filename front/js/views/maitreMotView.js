import { MaitreMotServices } from "../../services/maitremot-services.js";

class MaitreMotView {
  constructor() {
    this.instructionDiv = document.getElementById('maitremotinstruction');
    this.grilleDiv = document.getElementById('grille');
    this.cardsContainer = this.grilleDiv.querySelector('.cards');
    this.hint = document.getElementById('hint');
    this.nbMots = document.getElementById('nbMots');
    this.btn_definir = document.getElementById('btn_Definir');
    this.score = document.getElementById('score');
    this.turn = document.getElementById('turn');
    this.playerName1 = document.getElementById('playerName1');
    this.playerName2 = document.getElementById('playerName2');
    this.chat = document.getElementById('chat');
    this.chatContent = document.getElementById('chatContent');

    // Instance de MaitreMotServices
    this.maitreMotServices = new MaitreMotServices();

    // Appel pour mettre à jour les noms des joueurs
    this.updatePlayersNames();

    this.hideGameElements();
  }

  async updatePlayersNames() {
    const uniqueCode = window.location.search.split('=')[1]; // Obtenez uniqueCode depuis l'URL

    // Appel pour récupérer le nom du joueur 1
    const player1Name = await this.maitreMotServices.getPlayer1Name(uniqueCode);
    // Appel pour récupérer le nom du joueur 2
    const player2Name = await this.maitreMotServices.getPlayer2Name(uniqueCode);

    // Mise à jour des noms dans l'interface utilisateur
    this.playerName1.textContent = player1Name;
    this.playerName2.textContent = player2Name;
  }

  hideInstructions() {
    this.instructionDiv.classList.add('hidden');
    this.grilleDiv.classList.remove('hidden');
  }

  showGrid() {
    //this.grilleDiv.classList.add("visible");
    this.grilleDiv.style.display = 'block';
    this.showGameElements();
  }

  bindScreenClick(handler) {
    document.body.addEventListener('click', handler);
  }

  renderGrid(cards) {
    console.log("Rendu de la carte ", cards);
    this.cardsContainer.innerHTML = '';
    if (cards.length === 0) {
      console.log("Aucune carte à afficher");
      return;
    }
    cards.forEach((card) => {
      const cardElement = document.createElement('div');
      cardElement.classList.add('card');
      cardElement.textContent = card.mot; 
      cardElement.dataset.index = card.position; 
      
      if (card.id_couleur === 1) {
        cardElement.classList.add('blue');
      } else if (card.id_couleur === 2) {
        cardElement.classList.add('grey');
      } else if (card.id_couleur === 3) {
        cardElement.classList.add('black');
      }
      else if (card.id_couleur === 4) {
        cardElement.classList.add('white');
      }

      this.cardsContainer.appendChild(cardElement);
    });
  }

  updateScore(player2Score) {
    this.score.textContent = `Score:  ${player2Score}`;
  }

  updateTurn(turn) {
    this.turn.textContent = `Tour: ${turn}`;
  }

  hideGameElements() {
    this.hint.classList.add('hidden');
    this.nbMots.classList.add('hidden');
    this.btn_definir.classList.add('hidden');
    this.score.classList.add('hidden');
    this.turn.classList.add('hidden');
    this.playerName1.classList.add('hidden');
    this.playerName2.classList.add('hidden');
    this.chat.classList.add('hidden');
    this.chatContent.classList.add('hidden');
  }

  showGameElements() {
    this.hint.classList.remove('hidden');
    this.nbMots.classList.remove('hidden');
    this.btn_definir.classList.remove('hidden');
    this.score.classList.remove('hidden');
    this.turn.classList.remove('hidden');
    this.playerName1.classList.remove('hidden');
    this.playerName2.classList.remove('hidden');
    this.chat.classList.remove('hidden');
    this.chatContent.classList.remove('hidden');
  }

  addHintToChat(hint, nbMots) {
    const hintElement = document.createElement('div');
    hintElement.textContent = `L'indice est ${hint}, et correspond à ${nbMots} mots`;
    this.chatContent.appendChild(hintElement);
  }
}

export { MaitreMotView };
