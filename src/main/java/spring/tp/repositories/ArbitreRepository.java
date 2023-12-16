package spring.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.tp.entities.Arbitre;

@Repository
public interface ArbitreRepository extends JpaRepository<Arbitre, Long> {

}
