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

// import org.json.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    // TODO change to delete from db
    // int size = tournaments.size();
    // tournaments.removeIf(tournament -> tournament.getId() == id);

    // return size - tournaments.size();
    return 0;
  }

  @Override
  public List<Tournament> findAll() {
    // TODO change to query all from db
    // return tournaments;

    return jdbcTemplate.query("SELECT * from tournaments",
        (rs, rownum) -> {
          return new Tournament(
              rs.getLong("id"),
              rs.getString("name"),
              rs.getDate("date").toLocalDate(),
              Arrays.stream((Integer[]) rs.getArray("rankRange").getArray()).mapToInt(e -> (int) e).toArray(),
              rs.getString("status"),
              rs.getString("region"),
              getPlayerListFromJson(rs.getString("playerlist"))
            );
        });
  }

  private ArrayList<Player> getPlayerListFromJson(String json) {
    ObjectMapper objectMapper = new ObjectMapper();  // Create an instance of ObjectMapper
    ArrayList<Player> playerList = new ArrayList<>();

    if (json == null || json.isEmpty()) {
      return playerList; // Return an empty list if no players
    }

    try {
        // Convert JSON string to ArrayList<Player>
        // Assuming Player class has a default constructor and proper getters/setters
        Player[] playersArray = objectMapper.readValue(json, Player[].class);
        for (Player player : playersArray) {
            playerList.add(player);
        }
    } catch (JsonProcessingException e) {
        e.printStackTrace();  // Handle exception
    }

    return playerList;  // Return the populated ArrayList
  }

  @Override
  public Optional<Tournament> findById(Long id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public Long save(Tournament tournament) {
    // TODO change to add to db
    // TODO set id into tournament after adding into db
    // tournaments.add(tournament);
    String sql = "INSERT INTO tournaments (name, date, rankRange, status, region) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id";  // RETURNING id

    Date sqlDate = Date.valueOf(tournament.getDate());

    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update((Connection conn) -> {
      PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, tournament.getName());
      statement.setDate(2, sqlDate);
      statement.setArray(3, conn.createArrayOf("integer",  Arrays.stream(tournament.getRankRange()).boxed().toArray( Integer[]::new )));
      statement.setString(4, tournament.getStatus());
      statement.setString(5, tournament.getRegion());
      return statement;
    }, holder);

    Long primaryKey = holder.getKey().longValue();
    // System.out.println(primaryKey);
    return primaryKey;


    // return jdbcTemplate.queryForObject(sql, new Object[]{
    //         tournament.getName(),
    //         sqlDate,
    //         tournament.getRankRange(),
    //         tournament.getStatus(),
    //         tournament.getRegion()
    // }, Long.class);
  }

  @Override
  public int update(Tournament tournament) {
    // TODO Auto-generated method stub
    return 0;
  }

}
