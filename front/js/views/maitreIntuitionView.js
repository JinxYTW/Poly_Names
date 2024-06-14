import { MaitreIntuitionServices } from '../../services/maitreintuition-services.js';

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

      // Instance de MaitreMotServices
    this.maitreIntuitionServices = new MaitreIntuitionServices();

    // Appel pour mettre à jour les noms des joueurs
    this.updatePlayersNames();
    this.updateGameInfo();
  
      this.hideGameElements();
    }
    async updateGameInfo() {
      try {
        const uniqueCode = window.location.search.split('=')[1];
        const info = await this.maitreIntuitionServices.getInfo(uniqueCode);
  
        // Mettre à jour l'interface utilisateur avec les informations reçues
        this.turn.textContent = `Tour: ${info.tour}`;
        this.score.textContent = `Score: ${info.score}`;
  
      } catch (error) {
        console.error('Erreur lors de la récupération des informations du jeu:', error);
      }
    }
  
    async updatePlayersNames() {
      const uniqueCode = window.location.search.split('=')[1]; // Obtenez uniqueCode depuis l'URL
  
      // Appel pour récupérer le nom du joueur 1
      const player1Name = await this.maitreIntuitionServices.getPlayer1Name(uniqueCode);
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
      
      this.grilleDiv.style.display = 'block';
      this.showGameElements();
    }
  
    bindScreenClick(handler) {
      document.body.addEventListener('click', handler);
    }
  
    
  
    renderGrid(cards) {
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
  
        // Ajouter Couleur par def
        // Créer le span pour le rond avec l'image d'index
        const indexBadge = document.createElement('span');
        indexBadge.classList.add('index-badge');
        indexBadge.dataset.idPosition = card.position;
        const indexImage = document.createElement('img');
        indexImage.src = "../img/click.png"; // Mettez votre chemin d'image ici
        indexImage.alt = "Error";
        indexBadge.appendChild(indexImage);
        cardElement.appendChild(indexBadge);
  
        this.cardsContainer.appendChild(cardElement);
  
        indexBadge.addEventListener('click', async (event) => {
          const idPosition = event.target.closest('span').dataset.idPosition;
          console.log("Span cliqué avec id_position:", idPosition);
  
          const uniqueCode = window.location.search.split('=')[1];
          const maitreIntuitionServices = new MaitreIntuitionServices();
          const response = await maitreIntuitionServices.submitCard(uniqueCode, idPosition);
  
          
          const cardData = { id_couleur: 1 }; 
          maitreIntuitionController.submitCard(
            cardData.id_carte,
            cardData.mot,
            cardData.etat,
            cardData.position,
            cardData.id_couleur,
            cardData.id_mot,
            cardData.id_partie,
            uniqueCode
          );
        });
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

    addHintToChat(hint, nbMots) {
      const hintElement = document.createElement('div');
      hintElement.textContent = `L'indice est ${hint}, et correspond à ${nbMots} mots`;
      this.chatContent.appendChild(hintElement);
    }

    updateCardColor(position, colorClass) {
      const cardElement = this.cardsContainer.querySelector(`[data-index="${position}"]`);
      if (cardElement) {
        // Supprimez les classes de couleur existantes
        cardElement.classList.remove('blue', 'grey', 'black');
        // Ajoutez la nouvelle classe de couleur
        cardElement.classList.add(colorClass);
      }
    }
  
}
  
  export { MaitreIntuitionView };
  