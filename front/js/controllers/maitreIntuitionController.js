import { MaitreIntuitionView } from '../views/maitreIntuitionView.js';
import { Grid } from '../models/grid.js';
import { Player } from '../models/player.js';
import { Turn } from '../models/turn.js';

class MaitreIntuitionController {
  constructor() {
    this.view = new MaitreIntuitionView();
    this.grid = new Grid();
    this.player1 = new Player('Joueur 1');
    this.player2 = new Player('Joueur 2');
    this.turn = new Turn();

    // Test création de cartes
    for (let i = 0; i < 25; i++) {
      const word = `Mot ${i + 1}`;
      const color = i % 2 === 0 ? 'Bleu' : 'Gris';
      this.grid.addCard(word, color, i);
    }

    this.view.bindScreenClick(this.handleScreenClick.bind(this));
    this.view.renderGrid(this.grid.getAllCards());
    this.view.updatePlayersName(this.player1.getName(), this.player2.getName());
    this.view.updateScore(this.player1.getScore(), this.player2.getScore());
    this.view.updateTurn(this.turn.getTurn());
    
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
