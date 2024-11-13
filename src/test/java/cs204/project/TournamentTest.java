package cs204.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import cs204.project.Entity.Tournament;

public class TournamentTest {

    @Test
    void testFullConstructor() {
        Long id = 1L;
        String name = "Test Tournament";
        LocalDate date = LocalDate.now();
        int[] rankRange = {1000, 2000};
        String status = "Open";
        String region = "Asia";
        List<Long> playerList = Arrays.asList(1L, 2L);
        int round = 1;

        Tournament tournament = new Tournament(id, name, date, rankRange, status, region, playerList, round);

        assertEquals(id, tournament.getId());
        assertEquals(name, tournament.getName());
        assertEquals(date, tournament.getDate());
        assertArrayEquals(rankRange, tournament.getRankRange());
        assertEquals(status, tournament.getStatus());
        assertEquals(region, tournament.getRegion());
        assertEquals(playerList, tournament.getPlayerList());
        assertEquals(round, tournament.getRound());
    }

    @Test
    void testConstructorWithoutId() {
        String name = "Test Tournament";
        LocalDate date = LocalDate.now();
        int[] rankRange = {1000, 2000};
        String status = "Open";
        String region = "Asia";
        List<Long> playerList = Arrays.asList(1L, 2L);
        int round = 1;

        Tournament tournament = new Tournament(name, date, rankRange, status, region, playerList, round);

        assertNull(tournament.getId());
        assertEquals(name, tournament.getName());
        assertEquals(date, tournament.getDate());
        assertArrayEquals(rankRange, tournament.getRankRange());
        assertEquals(status, tournament.getStatus());
        assertEquals(region, tournament.getRegion());
        assertEquals(playerList, tournament.getPlayerList());
        assertEquals(round, tournament.getRound());
    }

    @Test
    void testConstructorWithoutPlayerListAndRound() {
        String name = "Test Tournament";
        LocalDate date = LocalDate.now();
        int[] rankRange = {1000, 2000};
        String status = "Open";
        String region = "Asia";

        Tournament tournament = new Tournament(name, date, rankRange, status, region);

        assertNull(tournament.getId());
        assertEquals(name, tournament.getName());
        assertEquals(date, tournament.getDate());
        assertArrayEquals(rankRange, tournament.getRankRange());
        assertEquals(status, tournament.getStatus());
        assertEquals(region, tournament.getRegion());
        assertTrue(tournament.getPlayerList().isEmpty());
        assertEquals(1, tournament.getRound()); 
    }

    @Test
    void testDefaultConstructor() {
        Tournament tournament = new Tournament();
        
        assertNull(tournament.getId());
        assertNull(tournament.getName());
        assertNull(tournament.getDate());
        assertNull(tournament.getRankRange());
        assertNull(tournament.getStatus());
        assertNull(tournament.getRegion());
        assertNull(tournament.getPlayerList());
        assertEquals(1, tournament.getRound()); 
    }

    @Test
    void testSettersAndGetters() {
        Tournament tournament = new Tournament();
        
        Long id = 1L;
        String name = "Test Tournament";
        LocalDate date = LocalDate.now();
        int[] rankRange = {1000, 2000};
        String status = "Open";
        String region = "Asia";
        List<Long> playerList = Arrays.asList(1L, 2L);
        int round = 2;

        tournament.setId(id);
        tournament.setName(name);
        tournament.setDate(date);
        tournament.setRankRange(rankRange);
        tournament.setStatus(status);
        tournament.setRegion(region);
        tournament.setPlayerList(playerList);
        tournament.setRound(round);

        assertEquals(id, tournament.getId());
        assertEquals(name, tournament.getName());
        assertEquals(date, tournament.getDate());
        assertArrayEquals(rankRange, tournament.getRankRange());
        assertEquals(status, tournament.getStatus());
        assertEquals(region, tournament.getRegion());
        assertEquals(playerList, tournament.getPlayerList());
        assertEquals(round, tournament.getRound());
    }
} 