package org.l11gr05.classes.game.arena;

import org.l11gr05.classes.game.elements.*;
import org.l11gr05.classes.game.elements.ghost.Blinky;
import org.l11gr05.classes.game.elements.ghost.Clyde;
import org.l11gr05.classes.game.elements.ghost.Inky;
import org.l11gr05.classes.game.elements.ghost.Pinky;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArenaFactory {

    private int height;
    private int width;

    public Arena createArena(String arenaName) throws IOException {
        Arena arena = new Arena();
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/" + arenaName;

        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());

        width = Integer.parseInt(br.readLine());
        height = Integer.parseInt(br.readLine());
        arena.setSize(width, height);
        readElements(arena, br);
        return arena;
    }

    @SuppressWarnings("MissingCasesInEnumSwitch")
    private void readElements(Arena arena, BufferedReader br) throws IOException {
        for (int i = 0; i < height; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '#' -> arena.addWall(new Wall(new Position(j, i)));
                    case '.' -> arena.addPacDot(new PacDot(new Position(j, i)));
                    case 'o' -> arena.addPowerPellet(new PowerPellet(new Position(j, i)));
                    case 'P' -> arena.setPacman(new Pacman(new Position(j, i), '0'));
                    case 'B' -> arena.setBlinky(new Blinky(j, i));
                    case 'I' -> arena.setInky(new Inky(j, i));
                    case 'N' -> arena.setPinky(new Pinky(j, i));
                    case 'C' -> arena.setClyde(new Clyde(j, i));

                }
            }
        }
    }
}



