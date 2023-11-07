package spring.tp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import spring.tp.entities.Arbitre;
import spring.tp.entities.Matche;
import spring.tp.repositories.ArbitreRepository;
import spring.tp.repositories.MatcheRepository;

@RestController
public class ArbitreController {
	
	@Autowired
	ArbitreRepository ar;
	@Autowired
	MatcheRepository mr;
	
	@PostMapping("arbitre")
	public Arbitre addArbitre(@RequestBody Arbitre g) {
	return ar.save(g);
	}
	
	@PostMapping("arbitre/{id}/matche/{id2}")
	public Matche addMatcheToArbitre(@PathVariable Long id, @RequestBody Matche m, @PathVariable Long id2) {
		Arbitre e= ar.findById(id).orElseThrow(() -> new EntityNotFoundException("Arbitre not found"));
		Matche m2= mr.findById(id2).orElse(null);
		//System.out.println(m2);
		if(m2==null) {m.setArbitre(e); return mr.save(m);}
		else {m2.setArbitre(e);
		return mr.save(m2);}
	}
	
	@GetMapping("arbitre")
	List<Arbitre> getAllArbitres(){
	return ar.findAll();
	}
	
	
	@GetMapping("arbitre/{id}")
	public Arbitre getArbitreById(@PathVariable Long id) {
	return ar.findById(id).get(); 
	
	}
	
	@GetMapping("arbitre/{id}/matche")
	public List<Matche> getMatcheByarbitreId(@PathVariable Long id) {
	Arbitre g = ar.findById(id).get();
	//we pass that group 
	return mr.findByArbitre(g); 
	}
	
	@DeleteMapping("arbitre/{id}")
	public void deleteArbitre(@PathVariable Long id) {
	List<Matche> matches=getMatcheByarbitreId(id); 
	matches.forEach(m->{//expression lambda
	m.setArbitre(null);
	});
	ar.deleteById(id);
	}
	
	@PutMapping("arbitre")
	public Arbitre updateArbitre(@RequestBody Arbitre s) {
	return ar.save(s);
	}
	}




