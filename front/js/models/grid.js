    import { Card } from "./card.js";

    class Grid {
        constructor(size = 5) {
            this.size = size;
            this.cards = [];
        }

        addCard(mot, color, id) {
            if (this.cards.length < this.size * this.size) {
                const card = new Card(mot, color, id);
                this.cards.push(card);
            } else {
                throw new Error("Grille pleine");
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
