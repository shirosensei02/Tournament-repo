package cs204.project;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cs204.project.tournament.Tournament;
import cs204.project.tournament.TournamentController;
import cs204.project.tournament.TournamentService;
import cs204.project.tournament.TournamentNotFoundException;

@WebMvcTest(TournamentController.class)
public class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getTournaments_ShouldReturnList() throws Exception {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
        tournament.setName("Test Tournament");
        tournament.setDate(LocalDate.now());
        tournament.setRankRange(new int[]{1000, 2000});
        tournament.setStatus("Open");
        tournament.setRegion("Asia");

        List<Tournament> tournaments = Arrays.asList(tournament);
        when(tournamentService.getTournamentList()).thenReturn(tournaments);

        mockMvc.perform(get("/tournaments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Tournament"));
    }

    @Test
    public void addTournament_ShouldReturnCreated() throws Exception {
        Tournament tournament = new Tournament();
        tournament.setName("New Tournament");
        tournament.setDate(LocalDate.now());
        tournament.setRankRange(new int[]{1000, 2000});
        tournament.setStatus("Open");
        tournament.setRegion("Asia");

        when(tournamentService.addTournament(any(Tournament.class))).thenReturn(tournament);

        mockMvc.perform(post("/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tournament)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getTournament_WhenNotFound_ShouldReturn404() throws Exception {
        when(tournamentService.getTournament(1L)).thenThrow(new TournamentNotFoundException(1L));

        mockMvc.perform(get("/tournaments/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTournament_ShouldReturn200() throws Exception {
        when(tournamentService.deleteTournament(1L)).thenReturn(1);

        mockMvc.perform(delete("/tournaments/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void joinTournament_ShouldReturn200() throws Exception {
        mockMvc.perform(post("/tournaments/1/player/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTournament_ShouldReturnOk() throws Exception {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
        tournament.setName("Updated Tournament");
        tournament.setDate(LocalDate.now());
        tournament.setRankRange(new int[]{1000, 2000});
        tournament.setStatus("Open");
        tournament.setRegion("Asia");

        when(tournamentService.updateTournament(eq(1L), any(Tournament.class))).thenReturn(tournament);

        mockMvc.perform(put("/tournaments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tournament)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPlayerTournaments_ShouldReturnList() throws Exception {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
        List<Tournament> tournaments = Arrays.asList(tournament);
        
        when(tournamentService.getPlayerJoinedTournaments(1L)).thenReturn(tournaments);

        mockMvc.perform(get("/tournaments/player/1"))
                .andExpect(status().isOk());
    }
}