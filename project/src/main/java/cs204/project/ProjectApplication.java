package cs204.project;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import io.github.cdimascio.dotenv.Dotenv;

import cs204.project.Repo.TournamentRepository;
import cs204.project.Entity.Tournament;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

@SpringBootApplication
public class ProjectApplication {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(ProjectApplication.class, args);

    JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
    // TournamentRepository repo = ctx.getBean(TournamentRepository.class);

    template.execute("CREATE TABLE IF NOT EXISTS tournaments (" +
        "id BIGSERIAL PRIMARY KEY," +
        "name VARCHAR(255) NOT NULL," +
        "date DATE NOT NULL," +
        "rankRange INT[] NOT NULL," +
        "status VARCHAR(50) NOT NULL," +
        "region VARCHAR(100) NOT NULL," +
        "playerList JSON," +
        "round INT" +
        ")");

    // List<Long> playerList = new ArrayList<>();
    // for (int i = 1; i <= 32; i++){
    // playerList.add((long)i);
    // }

    // List<Tournament> listTournaments = Arrays.asList(
    // new Tournament("t1", LocalDate.of(2024, 9, 23), new int[]{0,2000}, "Open",
    // "Asia", playerList, 1),
    // new Tournament("t2", LocalDate.of(2024, 9, 23), new int[]{0,1000}, "Open",
    // "America", playerList, 1)
    // );

    // listTournaments.forEach(tournament -> {
    // repo.save(tournament);
    // });
  }

}
