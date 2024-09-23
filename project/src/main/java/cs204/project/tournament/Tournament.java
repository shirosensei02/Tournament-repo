package cs204.project.tournament;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

import jakarta.annotation.Generated;

// @Entity
public class Tournament {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private LocalDate date;
    private int[] rankRange;
    private String status;
    private String region;
    private List<Player> playerList;

    public Tournament() {
    }
    
    public Tournament(String name, LocalDate date, int[] rankRange, String status, String region) {
      this.name = name;
      this.date = date;
      this.rankRange = rankRange;
      this.status = status;
      this.region = region;
      playerList = new ArrayList<>();
    }

    public Tournament(long id, String name, LocalDate date, 
        int[] rankRange, String status, String region, List<Player> playerList) {
      this.id = id;
      this.name = name;
      this.date = date;
      this.rankRange = rankRange;
      this.status = status;
      this.region = region;
      this.playerList = playerList;
    }

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public LocalDate getDate() {
      return date;
    }
    public void setDate(LocalDate date) {
      this.date = date;
    }
    public int[] getRankRange() {
      return rankRange;
    }
    public void setRankRange(int[] rankRange) {
      this.rankRange = rankRange;
    }
    public String getStatus() {
      return status;
    }
    public void setStatus(String status) {
      this.status = status;
    }
    public String getRegion() {
      return region;
    }
    public void setRegion(String region) {
      this.region = region;
    }
    public List<Player> getPlayerList() {
      return playerList;
    }
    public void setPlayerList(List<Player> playerList) {
      this.playerList = playerList;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    
}
