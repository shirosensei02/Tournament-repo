package cs204.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cs204.project.tournament.Tournament;
import cs204.project.tournament.TournamentRepository;
import cs204.project.tournament.TournamentServiceImpl;



@ExtendWith(MockitoExtension.class)
public class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    private Tournament tournament1;
    private Tournament tournament2;

    @BeforeEach
    public void setUp() {
        tournament1 = new Tournament();
        tournament1.setId(1L);
        tournament1.setPlayerList(new ArrayList<>(Arrays.asList(100L, 101L)));

        tournament2 = new Tournament();
        tournament2.setId(2L);
        tournament2.setPlayerList(new ArrayList<>(Collections.singletonList(102L)));
    }

    @Test
    public void testGetTournamentList() {
        List<Tournament> expectedTournaments = Arrays.asList(tournament1, tournament2);
        when(tournamentRepository.findAll()).thenReturn(expectedTournaments);

        List<Tournament> actualTournaments = tournamentService.getTournamentList();

        assertEquals(expectedTournaments, actualTournaments);
        verify(tournamentRepository, times(1)).findAll();
    }

    @Test
    public void testGetAvailableTournaments() {
        Long pid = 103L;
        List<Tournament> allTournaments = Arrays.asList(tournament1, tournament2);
        when(tournamentRepository.findAll()).thenReturn(allTournaments);

        List<Tournament> availableTournaments = tournamentService.getAvailableTournaments(pid);

        assertEquals(2, availableTournaments.size());
        assertTrue(availableTournaments.contains(tournament1));
        assertTrue(availableTournaments.contains(tournament2));
    }

    @Test
    public void testPlayerJoinTournament() {
        Long tid = 1L;
        Long pid = 103L;
        when(tournamentRepository.findById(tid)).thenReturn(Optional.of(tournament1));
        when(tournamentRepository.update(any(Tournament.class))).thenReturn(1);

        tournamentService.playerJoinTournament(tid, pid);

        ArgumentCaptor<Tournament> tournamentCaptor = ArgumentCaptor.forClass(Tournament.class);
        verify(tournamentRepository).update(tournamentCaptor.capture());
        Tournament updatedTournament = tournamentCaptor.getValue();
        assertTrue(updatedTournament.getPlayerList().contains(pid));    }


    @Test
    public void testAddTournament() {
        Tournament newTournament = new Tournament();
        newTournament.setPlayerList(Collections.emptyList());
        Long generatedId = 3L;
        when(tournamentRepository.save(newTournament)).thenReturn(generatedId);

        Tournament savedTournament = tournamentService.addTournament(newTournament);

        assertEquals(generatedId, savedTournament.getId());
        verify(tournamentRepository, times(1)).save(newTournament);
    }

    @Test
    public void testDeleteTournament() {
        Long tid = 1L;
        when(tournamentRepository.deleteById(tid)).thenReturn(1);

        int result = tournamentService.deleteTournament(tid);

        assertEquals(1, result);
        verify(tournamentRepository, times(1)).deleteById(tid);
    }

    @Test
    public void testGetTournament() {
        Long tid = 1L;
        when(tournamentRepository.findById(tid)).thenReturn(Optional.of(tournament1));

        Tournament foundTournament = tournamentService.getTournament(tid);

        assertNotNull(foundTournament);
        assertEquals(tournament1, foundTournament);
        verify(tournamentRepository, times(1)).findById(tid);
    }

    @Test
    public void testUpdateTournament() {
        Long tid = 1L;
        Tournament updatedTournament = new Tournament();
        updatedTournament.setId(tid);
        updatedTournament.setPlayerList(Arrays.asList(100L, 101L, 103L));
        when(tournamentRepository.update(updatedTournament)).thenReturn(1);

        Tournament result = tournamentService.updateTournament(tid, updatedTournament);

        assertNotNull(result);
        assertEquals(updatedTournament, result);
        verify(tournamentRepository, times(1)).update(updatedTournament);
    }
}
