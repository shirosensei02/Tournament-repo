package cs204.project.tournament;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class TournamentSQLRepo implements TournamentRepository{

  // TODO implement SQL connection here

  // for testing
  List<Tournament> tournaments = new ArrayList<>();

  @Override
  public int deleteById(Long id) {
    // TODO change to delete from db
    int size = tournaments.size();
    tournaments.removeIf(tournament -> tournament.getId() == id);
    
    return size - tournaments.size();
  }

  @Override
  public List<Tournament> findAll() {
    // TODO change to query all from db
    return tournaments;
  }

  @Override
  public Optional<Tournament> findById(Long id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public Long save(Tournament tournament) {
    // TODO change to add to db
    // TODO set id into tournament after adding into db
    tournaments.add(tournament);
    return null;
  }

  @Override
  public int update(Tournament tournament) {
    // TODO Auto-generated method stub
    return 0;
  }

}
