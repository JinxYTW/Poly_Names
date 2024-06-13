import { WaitingView } from "../views/waitingView.js";

class WaitingController {
    constructor() {
        this.waitingView = new WaitingView();
    }
    giveRoleChallenger(role){

        localStorage.setItem('role', role);
        console.log(role);
    }
}

export { WaitingController };