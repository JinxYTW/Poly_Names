import { WaitingServices } from "../../services/waiting-services.js";


class WaitingView {
    constructor(controller) {
        this.controller = controller;
        this.waitingServices = new WaitingServices();
    }

    getIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('room');
    }

    async giveRoleChallenger(role) {
        const roleChallenger = role;
        const roomID = this.getIdFromURL();
        // Redirection based on role
        window.location.href = `${roleChallenger}.html?room=${roomID}`;
    }
}
export { WaitingView };