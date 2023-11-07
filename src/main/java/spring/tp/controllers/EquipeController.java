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

import spring.tp.entities.Joueur;
import spring.tp.entities.Matche;
import spring.tp.entities.Equipe;

import spring.tp.services.EquipeService;

@RestController
public class EquipeController {
	
	@Autowired
	EquipeService es;
	
	@PostMapping("equipe")
	public Equipe addEquipe(@RequestBody Equipe e) {
		return es.addEquipe(e);
	}
	
	@GetMapping("equipe")
	List<Equipe> getAllEquipes(){
		return es.getAllEquipes();
	}	
	
	@GetMapping("equipe/{id}")
	public Equipe getEquipeById(@PathVariable Long id) {
		return es.getEquipeById(id);
	}
	
	//we'll add player to equipe with id in url
	@PostMapping("equipe/{id}/joueur")
	public Joueur addJoueurToEquipe(@PathVariable Long id, @RequestBody Joueur m) {
		
		return es.addJoueurToEquipe(id, m);
	}
	
	@GetMapping("equipe/maroc")
	public List<Equipe> findByPays() {
		
		return es.findByPays();
	}
	
	//show list of players that belongs to an equipe with id url
	@GetMapping("equipe/{id}/joueur")
	public List<Joueur> getJoueurByEquipeId(@PathVariable Long id) {
		return es.getJoueurByEquipeId(id);
	}
	
	//show list of players that belongs to an equipe with nom in url
		@GetMapping("equipe/nom/{nom}/joueur")
		public List<Joueur> getJoueurByEquipeNom(@PathVariable String nom) {
			
			return es.getJoueurByEquipeNom(nom);
		}
		
		@GetMapping("equipe/nom/{nom}/post/{post}")
		public List<Joueur> getJoueurByEquipeNomAndPost(@PathVariable String nom,@PathVariable String post) {
			
			
			return es.getJoueurByEquipeNomAndPost(nom, post);
			
		}
	
	//we'll add a match to an equipe, as an equipe1, match will save all infos of that equipe as an equipe1
	@PostMapping("equipe1/{id}/matche/{id2}")
	public Matche addMatcheToEquipe1(@PathVariable Long id, @RequestBody Matche m, @PathVariable Long id2) {
		return es.addMatcheToEquipe1(id, m, id2);
	}

	@PostMapping("equipe2/{id}/matche/{id2}")
	public Matche addMatcheToEquipe2(@PathVariable Long id, @RequestBody Matche m, @PathVariable Long id2) {
		return es.addMatcheToEquipe2(id, m, id2);
	}
	
	@GetMapping("equipe1/{id}/match")
	public List<Matche> getMatchByEquipe1Id(@PathVariable Long id) {
	return es.getMatchByEquipe1Id(id);
	}
	
	@GetMapping("equipe2/{id}/match")
	public List<Matche> getMatchByEquipe2Id(@PathVariable Long id) {
	return es.getMatchByEquipe2Id(id);
	}
	
	@PutMapping("equipe")
	public Equipe updateEquipe(@RequestBody Equipe e) {
		
return es.addEquipe(e);	
}
	
	
	@DeleteMapping("equipe/{id}")
	public void deleteEquipe(@PathVariable Long id) {
	
		es.deleteEquipe(id);
	}
	
	

}
