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
import spring.tp.entities.Stade;
import spring.tp.entities.Matche;
import spring.tp.repositories.StadeRepository;
import spring.tp.repositories.MatcheRepository;

@RestController
public class StadeController {

	@Autowired
	StadeRepository sr;
	@Autowired
	MatcheRepository mr;
	
	@PostMapping("stade")
	public Stade addStade(@RequestBody Stade g) {
	return sr.save(g);
	}
	
	@GetMapping("stade")
	List<Stade> getAllStades(){
	return sr.findAll();
	}
	
	
	@GetMapping("stade/{id}")
	public Stade getStadeById(@PathVariable Long id) {
	return sr.findById(id).get(); 
	
	}
	
	@GetMapping("stade/{id}/matche")
	public List<Matche> getMatcheByStadeId(@PathVariable Long id) {
	Stade g = sr.findById(id).get();
	//we pass that group 
	return mr.findByStade(g); 
	}
	
	@PostMapping("stade/{id}/matche/{id2}")
	public Matche addMatcheTostade(@PathVariable Long id, @RequestBody Matche m, @PathVariable Long id2) {
		Stade e= sr.findById(id).orElseThrow(() -> new EntityNotFoundException("stade not found"));
		Matche m2= mr.findById(id2).orElse(null);
		//System.out.println(m2);
		if(m2==null) {m.setStade(e); return mr.save(m);}
		else {m2.setStade(e);
		return mr.save(m2);}
	}
	
	@DeleteMapping("stade/{id}")
	public void deleteStade(@PathVariable Long id) {
	List<Matche> matches=getMatcheByStadeId(id); 
	matches.forEach(m->{//expression lambda
	m.setStade(null);
	});
	sr.deleteById(id);
	}
	
	@PutMapping("stade")
	public Stade updateStade(@RequestBody Stade s) {
	return sr.save(s);
	}
	
}
