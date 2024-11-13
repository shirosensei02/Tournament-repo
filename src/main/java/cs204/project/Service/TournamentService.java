package cs204.project.Service;

import java.util.List;
import cs204.project.Entity.Tournament;

public interface TournamentService {
    List<Tournament> getTournamentList();
    Tournament getTournament(Long id);

    List<Tournament> getAvailableTournaments(Long pid);
    List<Tournament> getPlayerJoinedTournaments(Long pid);
    void playerJoinTournament(Long tid, Long pid);

    // return newly added tournament
    Tournament addTournament(Tournament tournament);

    /**  return updated tournament
    @param id
    @param tournament
    @return
    */ 
    Tournament updateTournament(Long id, Tournament tournament);

    /**
     * return status of delete
     * 1 if remove
     * 0 if does not exist
     * @param id
     * @return
     */
    int deleteTournament(Long id);
}
