import { WaitingServices } from "../../services/waiting-services.js";


class WaitingView {
    constructor(controller) {
        this.controller = controller;
        this.waitingServices = new WaitingServices();
    }

    async giveRoleChallenger(role) {
        const roleChallenger = role;
        // Redirection based on role
        window.location.href = `${roleChallenger}.html?room=${localStorage.getItem('room Challenger')}`;
    }
}
export { WaitingView };