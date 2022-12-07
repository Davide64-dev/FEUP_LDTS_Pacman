package org.l11gr05.states;

import org.l11gr05.classes.game.arena.Arena;
import org.l11gr05.controller.Controller;
import org.l11gr05.controller.game.ArenaController;
import org.l11gr05.controller.game.GameController;
import org.l11gr05.viewer.Viewer;

public class GameState extends State<Arena> {

    public GameState(Arena model) {
        super(model);
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

    @Override
    public Viewer<Arena> getViewer() {
        // isto não pode ser null
        return null;
    }
}
