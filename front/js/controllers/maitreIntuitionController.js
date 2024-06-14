import { MaitreIntuitionView } from '../views/maitreIntuitionView.js';
import { Grid } from '../models/grid.js';
import { Player } from '../models/player.js';
import { Turn } from '../models/turn.js';
import { MaitreIntuitionServices } from '../../services/maitreintuition-services.js';


class MaitreIntuitionController {
    constructor() {
    this.view = new MaitreIntuitionView(this);
    this.grid = new Grid();
    this.player1 = new Player('Joueur 1');
    this.player2 = new Player('Joueur 2');
    this.turn = new Turn();
    this.services = new MaitreIntuitionServices();

    this.view.bindScreenClick(this.handleScreenClick.bind(this));

    document.getElementById('endTourButton').addEventListener('click', () => this.endTour(uniqueCode));
  

    const uniqueCode = window.location.search.split('=')[1];
    
    this.loadAndDisplayCards(uniqueCode);
    
    
    
  }


  async loadAndDisplayCards(uniqueCode) {
    
    const cards = await this.services.getCartes(uniqueCode);
    
    
    if (Array.isArray(cards)) {
      this.grid.setCards(cards);
      
      this.view.renderGrid(this.grid.getAllCards());
  
      this.view.updatePlayersName(this.player1.getName(), this.player2.getName());
      this.view.updateScore(this.player1.getScore(), this.player2.getScore());
      this.view.updateTurn(this.turn.getTurn());
    } else {
      console.error("Les cartes retournées ne sont pas un tableau:", cards);
    }
  }

  async submitCard(id_carte, mot, etat, position, id_couleur, id_mot, id_partie, uniqueCode) {
    this.view.updateGameInfo()
    if (id_couleur == 1) {
      

      // Mise à jour de la classe de la carte
      this.view.updateCardColor(position, 'blue'); 
    } else if (id_couleur == 2) {
      
      this.view.updateCardColor(position, 'grey');
      this.services.endTour(uniqueCode);
    } else if (id_couleur == 3) {
      
      this.view.updateCardColor(position, 'black'); 
    }
  }
  showIndice(indice,nbWord){ 
    
    this.view.addHintToChat(indice,nbWord);
    
  }
  endGame(uniqueCode){ 
    console.log("Fin de la partie ayant le code :"+uniqueCode);
    window.location.href = 'gameover.html';
  }

  victoire(uniqueCode){
    console.log("victoire")
    window.location.href = 'victoire.html';
  }

  handleScreenClick() {
    this.view.hideInstructions();
    this.view.showGrid();
  }

  updateScore(player, points) {
    player.updateScore(points);
    this.view.updateScore(this.player1.getScore(), this.player2.getScore());
  }

  nextTurn() {
    this.turn.nextTurn();
    this.view.updateTurn(this.turn.getTurn());
  }

  async endTour(uniqueCode) {
    try {
      const response = await this.services.endTour(uniqueCode);
      if (response) {
        console.log('Tour terminé avec succès');
      } else {
        console.error('Erreur lors de la fin du tour');
      }
    } catch (error) {
      console.error('Erreur lors de l\'appel à endTour:', error);
    }
  }

 
}

export { MaitreIntuitionController };
