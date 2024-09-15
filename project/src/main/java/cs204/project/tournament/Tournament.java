package cs204.project.tournament;

import java.util.ArrayList;
import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import com.fasterxml.jackson.annotation.JsonIgnore;

// import lombok.*;

// @Entity
// @Getter
// @Setter 
// @ToString 
// @AllArgsConstructor 
// @NoArgsConstructor 
// @EqualsAndHashCode
public class Tournament {
    // private @Id @GeneratedValue 
    private Long id;
    private String name;
    private String date;
    private int[] rankRange;
    private String status;
    private String region;
    private List<String> playerList = new ArrayList<>();

    public Tournament(String name, String date, int[] rankRange, String status, String region) {
      this.name = name;
      this.date = date;
      this.rankRange = rankRange;
      this.status = status;
      this.region = region;
    }

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getDate() {
      return date;
    }
    public void setDate(String date) {
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
    public List<String> getPlayerList() {
      return playerList;
    }
    public void setPlayerList(List<String> playerList) {
      this.playerList = playerList;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    
}
