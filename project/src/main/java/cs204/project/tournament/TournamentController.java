package cs204.project.tournament;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class TournamentController {
    private TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService){
      this.tournamentService = tournamentService;
    }

    /**
     * List all tournaments in system
     * @return list of all tournaments
     */
    @GetMapping("/tournaments")
    public List<Tournament> getTournaments(){
      return tournamentService.getTournamentList();
    }

    /**
     * Search tournament with given id
     * if not found, throw TournamentNotFoundException
     * @param id
     * @return book with given id
     */
    @GetMapping("tournaments/{id}")
    public Tournament getTournament(@PathVariable Long id){
      return null;
    }
}
