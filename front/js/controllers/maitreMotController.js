import { MaitreMotView } from '../views/maitreMotView.js';
import { Grid } from '../models/grid.js';


class MaitreMotController {
  constructor() {
    this.view = new MaitreMotView();
    this.grid = new Grid();

    for (let i = 0; i < 5; i++) {
      this.grid.addCard('Starfield', 'red', i);
      this.grid.addCard('CP2077', 'yellow', i + 5);
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