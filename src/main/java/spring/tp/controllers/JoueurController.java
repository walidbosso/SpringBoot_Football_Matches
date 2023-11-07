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
import spring.tp.repositories.JoueurRepository;
import spring.tp.entities.Joueur;

@RestController
public class JoueurController {

	@Autowired 
	JoueurRepository jr;
	
	@GetMapping("joueur")
	public List<Joueur> getAllJoueurs(){
		return jr.findAll();
	}
	@PostMapping("joueur")
	public Joueur addJoueur(@RequestBody Joueur s) {
		return jr.save(s);
	}
	@DeleteMapping("joueur/{id}")
	public void deleteJoueur(@PathVariable Long id) {
		jr.deleteById(id);
	}
	@PutMapping("joueur")
	public Joueur updateJoueur(@RequestBody Joueur s) {
		return jr.save(s);
	}
	}

