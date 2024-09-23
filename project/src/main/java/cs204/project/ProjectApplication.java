package cs204.project;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import io.github.cdimascio.dotenv.Dotenv;

import cs204.project.tournament.Tournament;
import cs204.project.tournament.TournamentRepository;

import java.util.List; 
import java.util.Arrays;
import java.time.LocalDate;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure().load();
    System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));


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
                 "playerList JSON" +
                 ")");
  
    // List<Tournament> listTournaments = Arrays.asList(
    //   new Tournament("t1", LocalDate.of(2024, 9, 23), new int[]{1,2}, "open", "asia"),
    //   new Tournament("t2", LocalDate.of(2024, 9, 23), new int[]{3,6}, "open", "west")
    // );

    // listTournaments.forEach(tournament -> {
    //   repo.save(tournament);
    // });
	}

}
