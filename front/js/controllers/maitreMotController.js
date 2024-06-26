import { MaitreMotView } from '../views/maitreMotView.js';
import { Grid } from '../models/grid.js';
import { Player } from '../models/player.js';
import { Turn } from '../models/turn.js';
import { MaitreMotServices } from '../../services/maitremot-services.js';


class MaitreMotController {
  constructor() {
    this.view = new MaitreMotView();
    this.grid = new Grid();
    this.player1 = new Player('Joueur 1');
    this.player2 = new Player('Joueur 2');
    this.turn = new Turn();
    this.services = new MaitreMotServices();

    this.view.bindScreenClick(this.handleScreenClick.bind(this));
    this.view.btn_definir.addEventListener('click', this.handleHintSubmission.bind(this));

    const uniqueCode = window.location.search.split('=')[1];
    console.log("Code unique MaitreMot: ",uniqueCode);
    this.loadAndDisplayCards(uniqueCode);
  }

  async loadAndDisplayCards(uniqueCode) {
    
    const cards = await this.services.getCartes(uniqueCode);
    
    
    if (Array.isArray(cards)) {
      this.grid.setCards(cards);
      
      this.view.renderGrid(this.grid.getAllCards());
  
      this.view.updatePlayersNames(this.player1.getName(), this.player2.getName());
      this.view.updateScore(this.player1.getScore(), this.player2.getScore());
      this.view.updateTurn(this.turn.getTurn());
    } else {
      console.error("Les cartes retournées ne sont pas un tableau:", cards);
    }
  }

  handleScreenClick() {
    this.view.hideInstructions();
    this.view.showGrid();
  }

   showButton(){
    this.view.enableIndiceButton();
  }

  hideButton(){
    this.view.disableIndiceButton();
  }

  updateScore(player, points) {
    player.updateScore(points);
    this.view.updateScore(this.player1.getScore(), this.player2.getScore());
  }
  endGame(uniqueCode){ 
    console.log("Fin de la partie ayant le code :"+uniqueCode);
    window.location.href = 'gameover.html';
  }
  victoire(){
    console.log("victoire")
    window.location.href = 'victoire.html';
  }
  async submitCard(id_carte, mot, etat, position, id_couleur, id_mot, id_partie, uniqueCode) {    
    this.view.updateGameInfo()
    if (id_couleur == 1) {
      console.log(id_carte);
      console.log(mot);
      console.log(etat);
      console.log(position);
      console.log(id_couleur);
      console.log(id_mot);
      console.log(id_partie);
    }

    // Ajouter la classe contrastée à la carte sélectionnée
    const selectedCard = document.querySelector(`.card[data-index="${position}"]`);
    if (selectedCard) {
      selectedCard.classList.add('contrast');
    }
  }
  nextTurn() {
    this.turn.nextTurn();
    this.view.updateTurn(this.turn.getTurn());
  }

  async handleHintSubmission() {
    const hint = this.view.hint.value;
    
    const nbMots = this.view.nbMots.value;
    
    const uniqueCode = window.location.search.split('=')[1];
    

    if (hint && nbMots > 0) {
      
      const response = await this.services.submitHint(hint, nbMots, uniqueCode);
      
      this.hideButton();

      

      if (response.status === "success") {
        this.view.addHintToChat(hint, nbMots);
        this.view.hint.value = '';
        this.view.nbMots.value = '0';
      } else {
        console.error("Erreur lors de la soumission de l'indice");
      }
    }
  }
}

export { MaitreMotController };
