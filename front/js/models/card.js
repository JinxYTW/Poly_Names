import { Word } from "./word.js";

class Card {
    #word;
    #color;
    #id;
    #colorHidden;

    constructor(word, color, id, colorHidden = false) {
        this.#word = new Word(word);
        this.#color = color;
        this.#id = id;
        this.#colorHidden = colorHidden;
    }

    get word() {
        return this.#word;
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