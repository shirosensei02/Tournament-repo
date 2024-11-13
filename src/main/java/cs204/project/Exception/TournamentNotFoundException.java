package cs204.project.Exception;

public class TournamentNotFoundException extends RuntimeException {
  public TournamentNotFoundException(long id) {
    super("Tournament with ID " + id + " not found");
  }
}