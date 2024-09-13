package cs204.project.tournament;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter 
@ToString 
@AllArgsConstructor 
@NoArgsConstructor 
@EqualsAndHashCode
public class Tournament {
    private @Id @GeneratedValue 
}
