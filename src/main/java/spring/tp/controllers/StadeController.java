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

import spring.tp.entities.Stade;
import spring.tp.entities.Matche;
import spring.tp.services.StadeService;

@RestController
public class StadeController {

	@Autowired
	StadeService ss;
	
	@PostMapping("stade")
	public Stade addStade(@RequestBody Stade g) {
	return ss.addStade(g);
	}
	
	@GetMapping("stade")
	List<Stade> getAllStades(){
	return ss.getAllStades();
	}
	
	
	@GetMapping("stade/{id}")
	public Stade getStadeById(@PathVariable Long id) {
	return ss.getStadeById(id);
	
	}
	
	@GetMapping("stade/{id}/matche")
	public List<Matche> getMatcheByStadeId(@PathVariable Long id) {
	
	return ss.getMatcheByStadeId(id);
	}
	
	@PostMapping("stade/{id}/matche/{id2}")
	public Matche addMatcheTostade(@PathVariable Long id, @RequestBody Matche m, @PathVariable Long id2) {
		return ss.addMatcheTostade(id, m, id2);
	}
	
	@DeleteMapping("stade/{id}")
	public void deleteStade(@PathVariable Long id) {
	ss.deleteStade(id);
	}
	
	@PutMapping("stade")
	public Stade updateStade(@RequestBody Stade s) {
	return ss.updateStade(s);
	}
	
}
