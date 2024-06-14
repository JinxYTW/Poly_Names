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

  updateScore(player, points) {
    player.updateScore(points);
    this.view.updateScore(this.player1.getScore(), this.player2.getScore());
  }
  endGame(uniqueCode){ // à modifier --------------------
    console.log("Fin de la partie ayant le code :"+uniqueCode);
  }
  async submitCard(id_carte, mot, etat, position, id_couleur, id_mot, id_partie, uniqueCode) {
    if (id_couleur == 1) {
      console.log(id_carte);
      console.log(mot);
      console.log(etat);
      console.log(position);
      console.log(id_couleur);
      console.log(id_mot);
      console.log(id_partie);
    }
  }
  nextTurn() {
    this.turn.nextTurn();
    this.view.updateTurn(this.turn.getTurn());
  }

  async handleHintSubmission() {
    const hint = this.view.hint.value;
    console.log("Indice soumis:", hint);
    const nbMots = this.view.nbMots.value;
    console.log("Nombre de mots soumis:", nbMots);
    const uniqueCode = window.location.search.split('=')[1];
    console.log("Code unique MaitreMot: ",uniqueCode);

    if (hint && nbMots > 0) {
      console.log("Soumission de l'indice");
      const response = await this.services.submitHint(hint, nbMots, uniqueCode);
      console.log("Réponse de l'API:", response);

      console.log(response);

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
