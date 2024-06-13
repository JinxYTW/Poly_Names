import { WaitingView } from "../views/waitingView.js";

class WaitingController {
    constructor() {
        this.waitingView = new WaitingView();
    }
    giveRoleChallenger(role){
        console.log(role);
    }
}

export { WaitingController };