package spring.tp.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.tp.entities.Arbitre;
import spring.tp.entities.Equipe;
import spring.tp.entities.Matche;
import spring.tp.entities.Stade;


@Repository
public interface MatcheRepository extends JpaRepository<Matche, Long> {
	List<Matche> findByArbitre(Arbitre a); 
	List<Matche> findByStade(Stade a);
	List<Matche> findByEquipe1(Equipe a); 
	List<Matche> findByEquipe2(Equipe a); 
	List<Matche> findByDateMatchEquals(Date a);
	List<Matche> findByDateMatchLessThan(Date a); 


}
