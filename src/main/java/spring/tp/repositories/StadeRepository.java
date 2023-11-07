package spring.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.tp.entities.Stade;

@Repository
public interface StadeRepository extends JpaRepository<Stade, Long> {

}
