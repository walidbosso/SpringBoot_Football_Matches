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

import spring.tp.entities.Arbitre;
import spring.tp.entities.Matche;

import spring.tp.services.ArbitreService;

@RestController
public class ArbitreController {
	
	@Autowired
	ArbitreService as;
	
	
	@PostMapping("arbitre")
	public Arbitre addArbitre(@RequestBody Arbitre g) {
	return as.addArbitre(g);
	}
	
	@PostMapping("arbitre/{id}/matche/{id2}")
	public Matche addMatcheToArbitre(@PathVariable Long id, @RequestBody Matche m, @PathVariable Long id2) {
		
		return as.addMatcheToArbitre(id, m, id2);}
	
	
	@GetMapping("arbitre")
	List<Arbitre> getAllArbitres(){
	return as.getAllArbitres();
	}
	
	
	@GetMapping("arbitre/{id}")
	public Arbitre getArbitreById(@PathVariable Long id) {
	return as.getArbitreById(id);
	
	}
	
	@GetMapping("arbitre/{id}/matche")
	public List<Matche> getMatcheByarbitreId(@PathVariable Long id) {
	
	return as.getMatcheByarbitreId(id);
	}
	
	@DeleteMapping("arbitre/{id}")
	public void deleteArbitre(@PathVariable Long id) {
	as.deleteArbitre(id);
	}
	
	@PutMapping("arbitre")
	public Arbitre updateArbitre(@RequestBody Arbitre s) {
	return as.updateArbitre(s);
	}
	}




