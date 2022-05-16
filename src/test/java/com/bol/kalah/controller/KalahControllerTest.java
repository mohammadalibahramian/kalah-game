package com.bol.kalah.controller;

import com.bol.kalah.service.KalahService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class KalahControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private KalahService kalahService;

    private MockMvc mockMvc;

    @PostConstruct
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void createNewGameTest() throws Exception {
        // Given/when
        this.mockMvc.perform(post("/api/games"))
                // then
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.board").isNotEmpty())
                .andReturn();
    }

    @Test
    public void playGameTest() throws Exception {
        // Given
        var game = kalahService.createGame();
        // When
        this.mockMvc.perform(put("/api/games/" + game.getId() + "/pits/1"))
                // Then
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.size()", Matchers.is(14)))
                .andExpect(jsonPath("$.id").value(game.getId()))
                .andExpect(jsonPath("$.board.pits[0].stoneCount").value("0"))
                .andExpect(jsonPath("$.board.pits[1].stoneCount").value("7"))
                .andExpect(jsonPath("$.board.pits[2].stoneCount").value("7"))
                .andExpect(jsonPath("$.board.pits[3].stoneCount").value("7"))
                .andExpect(jsonPath("$.board.pits[4].stoneCount").value("7"))
                .andExpect(jsonPath("$.board.pits[5].stoneCount").value("7"))
                .andExpect(jsonPath("$.board.pits[6].stoneCount").value("1"))
                .andExpect(jsonPath("$.board.pits[7].stoneCount").value("6"))
                .andExpect(jsonPath("$.board.pits[8].stoneCount").value("6"))
                .andExpect(jsonPath("$.board.pits[9].stoneCount").value("6"))
                .andExpect(jsonPath("$.board.pits[10].stoneCount").value("6"))
                .andExpect(jsonPath("$.board.pits[11].stoneCount").value("6"))
                .andExpect(jsonPath("$.board.pits[12].stoneCount").value("6"))
                .andExpect(jsonPath("$.board.pits[13].stoneCount").value("0"))
                .andReturn();
    }
}
