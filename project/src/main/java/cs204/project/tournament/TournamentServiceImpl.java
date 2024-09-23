package cs204.project.tournament;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements TournamentService{

    private TournamentRepository tournaments;
    public TournamentServiceImpl(TournamentRepository tournaments){
      this.tournaments = tournaments;
    }

    @Override
    public List<Tournament> getTournamentList() {
      return tournaments.findAll();
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

      if (t.isPresent()){
        return t.get();
      }
      return null;
    }

    @Override
    public Tournament updateTournament(Long id, Tournament tournament) {
      // TODO Auto-generated method stub
      return null;
    }

    
}
