package org.godfreyslanding;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFrame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSaveWorld {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testSave() throws IOException {
        Path filePath = Files.createTempFile("world", ".sav.gz");
        System.out.println(filePath);
        
        WorldData w = new WorldData(new Body[1000][1000]);
        w.worldGen();
        
        JFrame frame = new JFrame();
        
        World world = new World(w, frame);
        
        World.saveWorld(world, filePath);
        
        World world2 = World.loadWorld(filePath, frame);
        
        assertEquals(world.spawnY, world2.spawnY);
        assertEquals(world.blocks.length, world2.blocks.length);
        assertEquals(world.blocks[0].length, world2.blocks[0].length);
        for(int i = 0; i < world.blocks.length; i++) {
            for(int j = 0; j < world.blocks[0].length; j++) {
                assertEquals(world.blocks[i][j].code, world2.blocks[i][j].code, "code differs at "+i+","+j);
            }
        }
    }

}
