import { MaitreMotView } from '../views/maitreMotView.js';
import { Grid } from '../models/grid.js';


class MaitreMotController {
  constructor() {
    this.view = new MaitreMotView();
    this.grid = new Grid();

    this.grid.addCard('Pomme', 'red', 1);
    this.grid.addCard('Banane', 'yellow', 2);

    this.view.bindScreenClick(this.handleScreenClick.bind(this));
    this.view.renderGrid(this.grid.getAllCards());
  }

  handleScreenClick() {
    this.view.hideInstructions();
  }
}

export { MaitreMotController };