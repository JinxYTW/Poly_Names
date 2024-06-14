import { WaitingView } from "../views/waitingView.js";

class WaitingController {
    constructor() {
        this.waitingView = new WaitingView();
    }
    giveRoleChallenger(role){ // à modifier --------------------
        console.log(role);
    }
}

export { WaitingController };