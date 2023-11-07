package spring.tp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.tp.entities.Equipe;
import spring.tp.entities.Joueur;


@Repository
public interface JoueurRepository extends JpaRepository<spring.tp.entities.Joueur, Long> {

	List<Joueur> findByEquipe(Equipe a); 

}
