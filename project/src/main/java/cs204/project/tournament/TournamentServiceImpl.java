package cs204.project.tournament;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements TournamentService {

  private TournamentRepository tournaments;

  public TournamentServiceImpl(TournamentRepository tournaments) {
    this.tournaments = tournaments;
  }

  @Override
  public List<Tournament> getTournamentList() {
    return tournaments.findAll();
  }

  @Override
  public List<Tournament> getAvailableTournaments(){
        List<Tournament> tournamentsList = tournaments.findAll();
        Iterator<Tournament> iterator = tournamentsList.iterator();
        while (iterator.hasNext()) {
            Tournament tournament = iterator.next();
            if (tournament.getPlayerList().size() == 32) {
                iterator.remove();
            }
        }
        return tournamentsList; 
  }

  @Override
  public void playerJoinTournament(Long tid, Long pid){
    //Tournament tournament = tournamentService.getTournament(id);

    Optional<Tournament> t = tournaments.findById(tid);
    if (t.isPresent()) {
      Tournament tournament = t.get();
      List<Long> newPlayerList = tournament.getPlayerList();
      newPlayerList.add(pid);
      tournament.setPlayerList(newPlayerList);
      tournaments.update(tournament);
      //t.updateTournament(id, tournament);
    }
  }
  
  @Override
  public List<Tournament> getPlayerJoinedTournaments(Long pid){
    List<Tournament> tournamentsList = tournaments.findAll();
    Iterator<Tournament> iterator = tournamentsList.iterator();
    while (iterator.hasNext()) {
        Tournament tournament = iterator.next();
        if (!tournament.getPlayerList().contains(pid)) {
            iterator.remove();
        }
    }
    return tournamentsList;
  }

  @Override
  public Tournament addTournament(Tournament tournament) {
    tournament.setId(tournaments.save(tournament));
    return tournament;
  }

  @Override
  public int deleteTournament(Long id) {
    return tournaments.deleteById(id);
  }

  @Override
  public Tournament getTournament(Long id) {
    Optional<Tournament> t = tournaments.findById(id);

    if (t.isPresent()) {
      return t.get();
    }
    return null;
  }

  @Override
  public Tournament updateTournament(Long id, Tournament tournament) {
    // TODO Auto-generated method stub
    if (tournaments.update(tournament) > 0) {
      return tournament;
    }
    return null;
  }

}
