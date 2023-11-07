package spring.tp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityNotFoundException;
import spring.tp.entities.Matche;
import spring.tp.entities.Stade;
import spring.tp.repositories.MatcheRepository;
import spring.tp.repositories.StadeRepository;

@Service
public class StadeService {

	@Autowired
	StadeRepository sr;
	@Autowired
	MatcheRepository mr;
	
	public Stade addStade( Stade g) {
	return sr.save(g);
	}
	
	public List<Stade> getAllStades(){
	return sr.findAll();
	}
	
	
	public Stade getStadeById( Long id) {
	return sr.findById(id).get(); 
	
	}
	
	public List<Matche> getMatcheByStadeId( Long id) {
	Stade g = sr.findById(id).get();
	//we pass that group 
	return mr.findByStade(g); 
	}
	
	public Matche addMatcheTostade( Long id,  Matche m,  Long id2) {
		Stade e= sr.findById(id).orElseThrow(() -> new EntityNotFoundException("stade not found"));
		Matche m2= mr.findById(id2).orElse(null);
		//System.out.println(m2);
		if(m2==null) {m.setStade(e); return mr.save(m);}
		else {m2.setStade(e);
		return mr.save(m2);}
	}
	
	public void deleteStade( Long id) {
	List<Matche> matches=getMatcheByStadeId(id); 
	matches.forEach(m->{//expression lambda
	m.setStade(null);
	});
	sr.deleteById(id);
	}
	
	public Stade updateStade( Stade s) {
	return sr.save(s);
	}
}
