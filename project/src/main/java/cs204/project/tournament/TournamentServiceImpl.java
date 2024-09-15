package cs204.project.tournament;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements TournamentService{

    private TournamentRepository tournaments;
    public TournamentServiceImpl(TournamentRepository tournaments){
      this.tournaments = tournaments;
    }

    @Override
    public List<Tournament> getTournamentList() {
      return null;
    }

    @Override
    public Tournament addTournament(Tournament tournament) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public int deleteTournament(Long id) {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public Tournament getTournament(Long id) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Tournament updateTournament(Long id, Tournament tournament) {
      // TODO Auto-generated method stub
      return null;
    }

    
}
