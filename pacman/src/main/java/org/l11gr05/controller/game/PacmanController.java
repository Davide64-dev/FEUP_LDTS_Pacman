package org.l11gr05.controller.game;

import org.l11gr05.Game;
import org.l11gr05.classes.game.arena.Arena;
import org.l11gr05.classes.game.elements.Position;
import org.l11gr05.gui.GUI;

public class PacmanController extends GameController {
    public PacmanController(Arena arena) {
        super(arena);
    }

    private boolean canMove(Position position) {
        return getModel().isEmpty(position);
    }

    private void movePacman(Position position) {
        if (canMove(position)) this.getModel().getPacman().setPosition(position);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        Position pacmanPosition = this.getModel().getPacman().getPosition();

        if (action == GUI.ACTION.UP) {
            if (canMove(new Position(pacmanPosition.getX(), pacmanPosition.getY() - 1))) {
                this.getModel().getPacman().setDirection('u');
            }
        } else if (action == GUI.ACTION.DOWN) {
            if (canMove(new Position(pacmanPosition.getX(), pacmanPosition.getY() + 1))) {
                this.getModel().getPacman().setDirection('d');
            }
        } else if (action == GUI.ACTION.RIGHT) {
            if (canMove(new Position(pacmanPosition.getX() + 1, pacmanPosition.getY()))) {
                this.getModel().getPacman().setDirection('r');
            }
        } else if (action == GUI.ACTION.LEFT) {
            if (canMove(new Position(pacmanPosition.getX() - 1, pacmanPosition.getY()))) {
                this.getModel().getPacman().setDirection('l');
            }
        }

        switch (this.getModel().getPacman().getDirection()) {
            case 'u':
                movePacman(new Position(pacmanPosition.getX(), pacmanPosition.getY() - 1));
                break;
            case 'd':
                movePacman((new Position(pacmanPosition.getX(), pacmanPosition.getY() + 1)));
                break;
            case 'l':
                if(pacmanPosition.equals(new Position(0, 10))) movePacman(new Position(19, 10));
                else movePacman(new Position(pacmanPosition.getX() - 1, pacmanPosition.getY()));
                break;
            case 'r':
                if(pacmanPosition.equals(new Position(19, 10))) movePacman(new Position(0, 10));
                else movePacman(new Position(pacmanPosition.getX() + 1, pacmanPosition.getY()));
        }

        this.getModel().pacDotRemove(this.getModel().getPacman().getPosition());
        this.getModel().powerPelletRemove(this.getModel().getPacman().getPosition()); 
    }
}