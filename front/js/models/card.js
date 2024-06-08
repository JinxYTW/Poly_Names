import { Word } from "./word.js";

class Card {
    #mot;
    #color;
    #id;
    #colorHidden;

    constructor(mot, color, id, colorHidden = false) {
        this.#mot = new Word(mot);
        this.#color = color;
        this.#id = id;
        this.#colorHidden = colorHidden;
    }

    get mot() {
        return this.#mot;
    }

    get color() {
        return this.#color;
    }

    get id() {
        return this.#id;
    }

    show() {
        this.#colorHidden = false;
    }

    hide() {
        this.#colorHidden = true;
    }

    
}

export { Card };