import { SelectionServices } from "../../services/selection-services.js";

class SelectionView {
  constructor() {
    this.intuitionJoinButton = document.getElementById('intuitionjoin_btn');
    this.randomJoinButton = document.getElementById('randomjoin_btn');
    this.motJoinButton = document.getElementById('motjoin_btn');
    this.selectionServices = new SelectionServices();

    this.intuitionJoinButton.addEventListener('click', () => this.chooseRole('maitreintuition'));
    this.randomJoinButton.addEventListener('click', () => this.chooseRole('randomchoice'));
    this.motJoinButton.addEventListener('click', () => this.chooseRole('maitremot'));
  }

  async chooseRole(role) {
    console.log('chooseRoleView');
    try {
      const { selectedRole, room } = await this.selectionServices.chooseRole(role);
      if (selectedRole && room) {
        window.location.href = `${selectedRole}.html?room=${room}`;
      }
    } catch (error) {
      console.error('Error choosing role:', error);
    }
  }

}

export { SelectionView };