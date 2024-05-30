import { ControllerMemory } from "../controllers/controller-memory.js";
import { ViewMemory } from "../views/view-memory.js";

export class ApplicationMemory
{
    #controllerMemory;
    #viewMemory;

    constructor()
    {
        this.#initControllers();
        this.#initViews();
    }

    #initControllers()
    {
        this.#controllerMemory = new ControllerMemory();
    }

    #initViews()
    {
        this.#viewMemory = new ViewMemory(this.#controllerMemory);
    }

}