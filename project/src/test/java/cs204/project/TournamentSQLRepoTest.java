package cs204.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;
import java.sql.Array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cs204.project.tournament.Tournament;
import cs204.project.tournament.TournamentSQLRepo;

@SpringBootTest
@SpringJUnitConfig
public class TournamentSQLRepoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private TournamentSQLRepo tournamentRepo;
    private Tournament testTournament;

    @BeforeEach
    void setUp() throws SQLException {
        tournamentRepo = new TournamentSQLRepo(jdbcTemplate);
        
        // Setup test tournament
        testTournament = new Tournament();
        testTournament.setId(1L);
        testTournament.setName("Test Tournament");
        testTournament.setDate(LocalDate.now());
        testTournament.setRankRange(new int[]{1000, 2000});
        testTournament.setStatus("Open");
        testTournament.setRegion("Asia");
        testTournament.setPlayerList(new ArrayList<>(Arrays.asList(1L, 2L)));
        testTournament.setRound(1);
    }

    @Test @SuppressWarnings("unchecked")
    void findAll_ShouldReturnListOfTournaments() {
        List<Tournament> expectedTournaments = Arrays.asList(testTournament);
        when(jdbcTemplate.query(
            eq("SELECT * from tournaments"), 
            any(RowMapper.class)
        )).thenReturn(expectedTournaments);

        List<Tournament> actualTournaments = tournamentRepo.findAll();

        assertEquals(expectedTournaments.size(), actualTournaments.size());
        assertEquals(expectedTournaments.get(0).getId(), actualTournaments.get(0).getId());
    }

    @Test @SuppressWarnings("unchecked")
    void findById_WhenExists_ShouldReturnTournament() {
        when(jdbcTemplate.queryForObject(
            eq("SELECT * FROM tournaments WHERE id = ?"),
            any(RowMapper.class),
            eq(1L)
        )).thenReturn(testTournament);

        Optional<Tournament> result = tournamentRepo.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(testTournament.getId(), result.get().getId());
    }

    @Test @SuppressWarnings("unchecked")
    void findById_WhenNotExists_ShouldReturnEmpty() {
        when(jdbcTemplate.queryForObject(
            eq("SELECT * FROM tournaments WHERE id = ?"),
            any(RowMapper.class),
            eq(999L)
        )).thenReturn(null);

        Optional<Tournament> result = tournamentRepo.findById(999L);

        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldReturnGeneratedId() throws SQLException {
        // Mock the update method to simulate successful insert
        when(jdbcTemplate.update(any(PreparedStatementCreator.class), any(GeneratedKeyHolder.class)))
            .thenAnswer(invocation -> {
                GeneratedKeyHolder keyHolder = invocation.getArgument(1);
                // Use reflection to set the key value
                Field keyField = GeneratedKeyHolder.class.getDeclaredField("keyList");
                keyField.setAccessible(true);
                keyField.set(keyHolder, Arrays.asList(Map.of("", 1L)));
                return 1;
            });

        Long result = tournamentRepo.save(testTournament);

        assertNotNull(result);
        assertEquals(1L, result);
        verify(jdbcTemplate).update(any(PreparedStatementCreator.class), any(GeneratedKeyHolder.class));
    }

    @Test
    void update_ShouldReturnNumberOfRowsAffected() throws SQLException {
        when(jdbcTemplate.update(any(PreparedStatementCreator.class))).thenReturn(1);

        int result = tournamentRepo.update(testTournament);

        assertEquals(1, result);
        verify(jdbcTemplate).update(any(PreparedStatementCreator.class));
    }

    @Test
    void deleteById_ShouldReturnNumberOfRowsAffected() {
        when(jdbcTemplate.update(eq("DELETE FROM tournaments WHERE id = ?"), eq(1L)))
            .thenReturn(1);

        int result = tournamentRepo.deleteById(1L);

        assertEquals(1, result);
        verify(jdbcTemplate).update(eq("DELETE FROM tournaments WHERE id = ?"), eq(1L));
    }

    @Test
    void mapRow_ShouldMapResultSetToTournament() throws SQLException {
        // Mock ResultSet responses
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("Test Tournament");
        when(resultSet.getDate("date")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));

        // Mock Array behavior
        Array sqlArray = mock(Array.class);
        when(sqlArray.getArray()).thenReturn(new Integer[]{1000, 2000});
        when(resultSet.getArray("rankRange")).thenReturn(sqlArray);

        when(resultSet.getString("status")).thenReturn("Open");
        when(resultSet.getString("region")).thenReturn("Asia");
        when(resultSet.getString("playerList")).thenReturn("[1,2]");
        when(resultSet.getInt("round")).thenReturn(1);

        Tournament result = tournamentRepo.mapRow(resultSet, 1);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Tournament", result.getName());
        assertEquals("Open", result.getStatus());
        assertEquals("Asia", result.getRegion());
        assertEquals(1, result.getRound());
    }
}