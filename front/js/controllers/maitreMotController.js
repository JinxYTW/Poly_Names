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

  handleHintSubmission() {
    const hint = this.view.hint.value;
    const nbMots = this.view.nbMots.value;

    if (hint && nbMots > 0) {
      this.view.addHintToChat(hint, nbMots);
      this.view.hint.value = '';
      this.view.nbMots.value = '0';
    }
  }
}

export { MaitreMotController };
