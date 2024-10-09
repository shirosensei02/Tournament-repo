package cs204.project.tournament;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Statement;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
// import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import org.json.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

@Repository
public class TournamentSQLRepo implements TournamentRepository {

  // TODO implement SQL connection here
  @Autowired
  private JdbcTemplate jdbcTemplate;
  // for testing
  // List<Tournament> tournaments = new ArrayList<>();

  public TournamentSQLRepo(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int deleteById(Long id) {
    String sql = "DELETE FROM tournaments WHERE id = ?";
    return jdbcTemplate.update(sql, id);
  }

  @Override
  public List<Tournament> findAll() {
    // TODO change to query all from db
    // return tournaments;

    return jdbcTemplate.query("SELECT * from tournaments",
        (rs, rownum) -> mapRow(rs, rownum));
  }

  @Override
  public Optional<Tournament> findById(Long id) {
    try {
      return Optional.ofNullable(
          jdbcTemplate.queryForObject(
              "SELECT * FROM tournaments WHERE id = ?",
              (rs, rowNum) -> mapRow(rs, rowNum),
              id));

    } catch (EmptyResultDataAccessException e) {
      // book not found - return an empty object
      return Optional.empty();
    }
  }

  @Override
  public Long save(Tournament tournament) {
    String sql = "INSERT INTO tournaments (name, date, rankRange, status, region, playerList) " +
        "VALUES (?, ?, ?, ?, ?, ?) RETURNING id"; // RETURNING id

    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update((Connection conn) -> {
      PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      try {
        setDB(conn, statement, tournament);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      return statement;
    }, holder);

    Long primaryKey = holder.getKey().longValue();
    System.out.println(primaryKey);
    return primaryKey;
  }

  @Override
  public int update(Tournament tournament) {
    String sql = "UPDATE tournaments SET name = ?, date = ?, rankRange = ?, status = ?, region = ?, playerList = ? WHERE id = ?";

    return jdbcTemplate.update((Connection conn) -> {
      PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      try {
        setDB(conn, statement, tournament);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      statement.setLong(7, tournament.getId());
      return statement;
    });
  }

  public PreparedStatement setDB(Connection conn, PreparedStatement statement, Tournament tournament)
      throws JsonProcessingException, SQLException {
    ObjectMapper objectMapper = new ObjectMapper();
    String playerListJson = null;
    if (tournament.getPlayerList() != null) {
      playerListJson = objectMapper.writeValueAsString(tournament.getPlayerList());
    }
    Date sqlDate = Date.valueOf(tournament.getDate());

    statement.setString(1, tournament.getName());
    statement.setDate(2, sqlDate);
    statement.setArray(3,
        conn.createArrayOf("integer", Arrays.stream(tournament.getRankRange()).boxed().toArray(Integer[]::new)));
    statement.setString(4, tournament.getStatus());
    statement.setString(5, tournament.getRegion());
    if (playerListJson != null) {
      statement.setString(6, playerListJson);
    } else {
      statement.setNull(6, java.sql.Types.OTHER); // Use the appropriate SQL type if needed
    }
    return statement;
  }

  public Tournament mapRow(ResultSet rs, int rowNum) throws SQLException {
    ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

    // Retrieve the playerList JSON
    String playerListJson = rs.getString("playerList");
    List<Long> playerList = new ArrayList<>();
    if (playerListJson != null && !playerListJson.isEmpty()) {
      // Convert JSON string to ArrayList<Long>
      try {
        Long[] playerArray = objectMapper.readValue(playerListJson, Long[].class);
        playerList = new ArrayList<>(List.of(playerArray)); // Convert array to ArrayList
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // Map the ResultSet data to a Tournament object
    return new Tournament(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getDate("date").toLocalDate(),
        Arrays.stream((Integer[]) rs.getArray("rankRange").getArray()).mapToInt(e -> (int) e).toArray(),
        rs.getString("status"),
        rs.getString("region"),
        playerList);
  }
}
