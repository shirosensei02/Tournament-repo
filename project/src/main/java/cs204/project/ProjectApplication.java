package cs204.project;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import cs204.project.tournament.Tournament;
import cs204.project.tournament.TournamentRepository;

import java.util.List; 
import java.util.Arrays;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(ProjectApplication.class, args);

    JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
    // TournamentRepository repo = ctx.getBean(TournamentRepository.class);

    template.execute("CREATE TABLE IF NOT EXISTS tournaments (" + //
            "id BIGINT AUTO_INCREMENT PRIMARY KEY," + //
            "name VARCHAR(255) NOT NULL," + //
            "date DATE NOT NULL," + //
            "rankRange JSON NOT NULL," + //
            "status VARCHAR(50) NOT NULL," + //
            "region VARCHAR(100) NOT NULL," + //
            "playerList JSON" + //
            ")");

    // List<Tournament> listTournaments = Arrays.asList(
    //   new Tournament("t1", "11/11/11", new int[]{1,2}, "open", "asia"),
    //   new Tournament("t2", "11/11/11", new int[]{3,6}, "open", "west")
    // );

    // listTournaments.forEach(tournament -> {
    //   repo.save(tournament);
    // });
	}

}
