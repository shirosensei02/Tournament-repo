package cs204.project.Entity;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

// @Entity
public class Tournament {
  // @Id
  // @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message= "name: not null")
  private String name;

  @NotNull(message = "date: not null")
  private LocalDate date;

  @NotNull(message = "rankRange: not null")
  private int[] rankRange;

  @NotNull(message = "status: not null")
  private String status;

  @NotNull(message = "region: not null")
  private String region;

  private List<Long> playerList;
  private int round = 1;

  public Tournament() {
  }

  public Tournament(Long id, String name, LocalDate date, int[] rankRange, String status, String region,
      List<Long> playerList, int round) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.rankRange = rankRange;
    this.status = status;
    this.region = region;
    this.playerList = playerList;
    this.round = round;
  }

  public Tournament(String name, LocalDate date,
      int[] rankRange, String status, String region, List<Long> playerList, int round) {
    this.name = name;
    this.date = date;
    this.rankRange = rankRange;
    this.status = status;
    this.region = region;
    this.playerList = playerList;
    this.round = round;
  }

  public Tournament(String name, LocalDate date,
      int[] rankRange, String status, String region) {
    this.name = name;
    this.date = date;
    this.rankRange = rankRange;
    this.status = status;
    this.region = region;
    this.playerList = new ArrayList<>();
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

  public List<Long> getPlayerList() {
    return playerList;
  }

  public void setPlayerList(List<Long> playerList) {
    this.playerList = playerList;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getRound() {
    return round;
  }

  public void setRound(int round) {
    this.round = round;
  }

}
