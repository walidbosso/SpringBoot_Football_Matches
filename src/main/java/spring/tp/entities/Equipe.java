package spring.tp.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipe {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id; 
	String nom;
	String pays;
	 
	@OneToMany(mappedBy = "equipe1")
	@JsonIgnore
     List<Matche> matchesAsEquipe1;

    @OneToMany(mappedBy = "equipe2")
    @JsonIgnore
     List<Matche> matchesAsEquipe2;
    
	@OneToMany(mappedBy = "equipe") 
	@JsonIgnore //on recupere list de groups we ignore the students, because if we want to recupere a student it will recupere id nom et note et son groupe ( et groupe contient id nom and students, we ignore students)
	List<Joueur> joueurs; 
}
