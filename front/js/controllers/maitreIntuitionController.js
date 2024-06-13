import { MaitreIntuitionView } from '../views/maitreIntuitionView.js';
import { Grid } from '../models/grid.js';
import { Player } from '../models/player.js';
import { Turn } from '../models/turn.js';
import { MaitreIntuitionServices } from '../../services/maitreintuition-services.js';

class MaitreIntuitionController {
    constructor() {
    this.view = new MaitreIntuitionView();
    this.grid = new Grid();
    this.player1 = new Player('Joueur 1');
    this.player2 = new Player('Joueur 2');
    this.turn = new Turn();
    this.services = new MaitreIntuitionServices();

    this.view.bindScreenClick(this.handleScreenClick.bind(this));


    const uniqueCode = window.location.search.split('=')[1];
    console.log("Code unique MaitreIntuition: ",uniqueCode);
    this.loadAndDisplayCards(uniqueCode);
    
    
    
  }


  async loadAndDisplayCards(uniqueCode) {
    console.log("Chargement et affichage des cartes");
    const cards = await this.services.getCartes(uniqueCode);
    console.log("Type des cartes reçues:", typeof cards);
    console.log("Cartes reçues:", cards);
    
    if (Array.isArray(cards)) {
      this.grid.setCards(cards);
      console.log("Cartes: ", cards);
      this.view.renderGrid(this.grid.getAllCards());
  
      this.view.updatePlayersName(this.player1.getName(), this.player2.getName());
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

  updateScore(player, points) {
    player.updateScore(points);
    this.view.updateScore(this.player1.getScore(), this.player2.getScore());
  }

  nextTurn() {
    this.turn.nextTurn();
    this.view.updateTurn(this.turn.getTurn());
  }

 
}

export { MaitreIntuitionController };
