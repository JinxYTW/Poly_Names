import { joinView } from "../views/joinView.js";

class joinController {
    constructor() {
        this.view = new joinView();
        this.view.handlePageLoad();
    }
}

export { joinController };
