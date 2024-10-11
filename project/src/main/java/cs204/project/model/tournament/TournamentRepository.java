package cs204.project.model.tournament;

import java.time.LocalDate;
// import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.dao.EmptyResultDataAccessException;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{
    // These 2 methods should be in controller
    // Controller has add/removePlayer, which calls on Service's getPlayerList() which then
    // calls on findById(Tournament tournament) from repo to get the tournament
    // It then uses tournament.getPlayerList() to get the list of players
    // Once the list of players is returned by Service to Controller, controller modifies the list, 
    // adding or removing the player

    // Long addPlayer(Player player, Tournament tournament);
    // Long removePlayer(Player player, Tournament tournament);
    // @Query("SELECT t FROM Tournament t WHERE t.id = :id")
    // TournamentPlayersProjection findPlayersJsonById(@Param("id") Long id);
    // Tournament findTournamentById(Long id);

    public Optional<Tournament> findTournamentByDate(LocalDate date);
    public Optional<Tournament> findTournamentByName(String name);
    public Optional<Tournament> findTournamentByStatus(Tournament.Status status);

    // @Query("SELECT t FROM Tournament t WHERE t.id = :id")
    // Uhh idt can actually do this, cause in SQL the range is saved as a JSON string. 
    // This might have to be a serviceImpl method that loops through the whole thing
    // Which is gonna take a while
    public Optional<Tournament> findTournamentByRankRange(int[] rankRange);
    
    // int update(Tournament tournament);
    // @Query(value = "SELECT * FROM tournament t inner join player p on t.region = p.region where p.id = ?1", nativeQuery = true)
    // // find the player's match history based on region played
    // int deleteById(Long id);
    
    // @Query("SELECT COUNT(t) FROM Tournament t WHERE t.region = :region")
    // long countByRegion(@Param("region") String region);
}
