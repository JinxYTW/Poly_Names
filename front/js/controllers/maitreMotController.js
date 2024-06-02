import { MaitreMotView } from '../views/maitreMotView.js';
import { Grid } from '../models/grid.js';


class MaitreMotController {
  constructor() {
    this.view = new MaitreMotView();
    this.grid = new Grid();

    // Test création de cartes
    for (let i = 0; i < 25; i++) {
      const word = `Mot ${i + 1}`;
      const color = i % 2 === 0 ? 'Bleu' : 'Gris';
      this.grid.addCard(word, color, i);
    }

    this.view.bindScreenClick(this.handleScreenClick.bind(this));
    this.view.renderGrid(this.grid.getAllCards());
  }

  handleScreenClick() {
    this.view.hideInstructions();
    this.view.showGrid();
  }
}

export { MaitreMotController };
