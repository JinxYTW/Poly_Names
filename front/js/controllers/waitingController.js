import { WaitingView } from "../views/waitingView.js";

class WaitingController {
    constructor() {
        this.waitingView = new WaitingView(this);
    }

    giveRoleChallenger(role) {
        console.log("Role p2: " + role);
        this.waitingView.giveRoleChallenger(role);
    }
}

export { WaitingController };