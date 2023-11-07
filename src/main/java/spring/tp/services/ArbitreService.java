package spring.tp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityNotFoundException;
import spring.tp.entities.Arbitre;
import spring.tp.entities.Matche;
import spring.tp.repositories.ArbitreRepository;
import spring.tp.repositories.MatcheRepository;

@Service
public class ArbitreService {

	@Autowired
	ArbitreRepository ar;
	@Autowired
	MatcheRepository mr;
	
	public Arbitre addArbitre( Arbitre g) {
	return ar.save(g);
	}
	
	public Matche addMatcheToArbitre( Long id,  Matche m,  Long id2) {
		Arbitre e= ar.findById(id).orElseThrow(() -> new EntityNotFoundException("Arbitre not found"));
		Matche m2= mr.findById(id2).orElse(null);
		//System.out.println(m2);
		if(m2==null) {m.setArbitre(e); return mr.save(m);}
		else {m2.setArbitre(e);
		return mr.save(m2);}
	}
	
	public List<Arbitre> getAllArbitres(){
	return ar.findAll();
	}
	
	
	public Arbitre getArbitreById( Long id) {
	return ar.findById(id).get(); 
	
	}
	
	public List<Matche> getMatcheByarbitreId( Long id) {
	Arbitre g = ar.findById(id).get();
	//we pass that group 
	return mr.findByArbitre(g); 
	}
	
	public void deleteArbitre( Long id) {
	List<Matche> matches=getMatcheByarbitreId(id); 
	matches.forEach(m->{//expression lambda
	m.setArbitre(null);
	});
	ar.deleteById(id);
	}
	
	public Arbitre updateArbitre( Arbitre s) {
	return ar.save(s);
	}
}
