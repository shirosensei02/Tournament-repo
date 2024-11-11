package cs204.project.Repo;

import java.util.List;
import java.util.Optional;
import cs204.project.Entity.Tournament;

public interface TournamentRepository {
    Long save(Tournament tournament);
    int update(Tournament tournament);
    int deleteById(Long id);
    List<Tournament> findAll();

    Optional<Tournament> findById(Long id);
}
