import { Card } from "./card.js";

class Grid {
    constructor(size = 5) {
        this.size = size;
        this.cards = [];
    }

    addCard(word, color, id) {
        if (this.cards.length < this.size * this.size) {
            const card = new Card(word, color, id);
            this.cards.push(card);
        } else {
            throw new Error("Rille pleine");
        }
    }

    getCard(id) {
        return this.cards.find(card => card.id === id);
    }

    getAllCards() {
        return this.cards;
    }
}

export { Grid };
