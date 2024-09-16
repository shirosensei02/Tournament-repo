package cs204.project.tournament;

public class TournamentNotFoundException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  TournamentNotFoundException(Long id) {
        super("Could not find book " + id);
    }
}
