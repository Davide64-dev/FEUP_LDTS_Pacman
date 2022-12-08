package org.l11gr05;

import org.l11gr05.classes.game.arena.Arena;
import org.l11gr05.gui.LanternaGUI;
import org.l11gr05.menu.Menu;
import org.l11gr05.states.GameState;
import org.l11gr05.states.MenuState;
import org.l11gr05.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(19, 22);
        this.state = new MenuState(new Menu());
        //this.state  = new GameState(new Arena());
    }

    public void setState(State state){
        this.state = state;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException{
        new Game().start();
    }

    private void start() throws IOException{
        int FPS = 10;
        int frameTime = 1000 / FPS;

        while(this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }

        gui.close();
    }
}
