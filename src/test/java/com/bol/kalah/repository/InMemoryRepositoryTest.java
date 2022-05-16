package com.bol.kalah.repository;

import com.bol.kalah.model.Game;
import com.bol.kalah.repository.impl.InMemoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class InMemoryRepositoryTest {

    @Autowired
    private InMemoryRepository repository;

    @Test
    public void testSave() {
        // Given
        var game = new Game();

        // When
        var savedGame = repository.save(game);

        // Then
        assertEquals(game, savedGame);
    }

    @Test
    public void testFind() {
        // Given
        var game = new Game();
        var gameId = game.getId();
        repository.save(game);

        // When
        var foundGame = repository.find(gameId);

        // Then
        assertNotNull(foundGame);
        assertEquals(gameId, foundGame.getId());
    }
}
