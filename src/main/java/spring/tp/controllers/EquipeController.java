package spring.tp.controllers;

import java.util.ArrayList;
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
import spring.tp.entities.Joueur;
import spring.tp.entities.Matche;
import spring.tp.entities.Equipe;
import spring.tp.repositories.JoueurRepository;
import spring.tp.repositories.MatcheRepository;
import spring.tp.repositories.EquipeRepository;

@RestController
public class EquipeController {
	
	@Autowired
	EquipeRepository er;
	@Autowired
	JoueurRepository jr;
	@Autowired
	MatcheRepository mr;
	
	@PostMapping("equipe")
	public Equipe addEquipe(@RequestBody Equipe e) {
		return er.save(e);
	}
	
	@GetMapping("equipe")
	List<Equipe> getAllEquipes(){
		return er.findAll();
	}	
	
	@GetMapping("equipe/{id}")
	public Equipe getEquipeById(@PathVariable Long id) {
		return er.findById(id).get(); 
	}
	
	//we'll add player to equipe with id in url
	@PostMapping("equipe/{id}/joueur")
	public Joueur addJoueurToEquipe(@PathVariable Long id, @RequestBody Joueur m) {
		Equipe e= er.findById(id).get(); 
		m.setEquipe(e);
		return jr.save(m);
	}
	
	@GetMapping("equipe/maroc")
	public List<Equipe> findByPays() {
		
		return er.findByPaysIgnoreCase("Maroc");
	}
	
	//show list of players that belongs to an equipe with id url
	@GetMapping("equipe/{id}/joueur")
	public List<Joueur> getJoueurByEquipeId(@PathVariable Long id) {
		Equipe e = er.findById(id).get();
		return jr.findByEquipe(e); 
	}
	
	//show list of players that belongs to an equipe with nom in url
		@GetMapping("equipe/nom/{nom}/joueur")
		public List<Joueur> getJoueurByEquipeNom(@PathVariable String nom) {
			Equipe e = er.findByNom(nom);
			return jr.findByEquipe(e); 
		}
		
		@GetMapping("equipe/nom/{nom}/post/{post}")
		public List<Joueur> getJoueurByEquipeNomAndPost(@PathVariable String nom,@PathVariable String post) {
			Equipe e = er.findByNom(nom);
			//System.out.println(e.getNom());
			
			List<Joueur> joueurs= jr.findByEquipe(e); 
			System.out.println(joueurs.isEmpty());
			List<Joueur> attaquants = new ArrayList<>();
			joueurs.forEach(m->{//expression lambda
				if(m.getPoste().equals(post)) attaquants.add(m);
			});
			
			return attaquants;
			
		}
	
	//we'll add a match to an equipe, as an equipe1, match will save all infos of that equipe as an equipe1
	@PostMapping("equipe1/{id}/matche/{id2}")
	public Matche addMatcheToEquipe1(@PathVariable Long id, @RequestBody Matche m, @PathVariable Long id2) {
		//we see if that equipe with first id in url exists we fetch the whole equipe
		Equipe e= er.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipe not found"));
		//we check if that match already exists, we extract the whole match
		Matche m2= mr.findById(id2).orElse(null);
		//System.out.println(m2);
		//if the match doesn't exist, we take the Matche from body, add equipe 1 and save to bd
		if(m2==null) {m.setEquipe1(e); return mr.save(m);}
		//or else we take the already ex match, add equipe1 to it and update it
		else {m2.setEquipe1(e);
		return mr.save(m2);}
	}

	@PostMapping("equipe2/{id}/matche/{id2}")
	public Matche addMatcheToEquipe2(@PathVariable Long id, @RequestBody Matche m, @PathVariable Long id2) {
		Equipe e= er.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipe not found"));
		Matche m2= mr.findById(id2).orElse(null);
		//System.out.println(m2);
		if(m2==null) {m.setEquipe2(e); return mr.save(m);}
		else m2.setEquipe2(e);
		return mr.save(m2);
	}
	
	@GetMapping("equipe1/{id}/match")
	public List<Matche> getMatchByEquipe1Id(@PathVariable Long id) {
	Equipe e = er.findById(id).get();
	return mr.findByEquipe1(e); 
	}
	
	@GetMapping("equipe2/{id}/match")
	public List<Matche> getMatchByEquipe2Id(@PathVariable Long id) {
	Equipe e = er.findById(id).get();
	return mr.findByEquipe2(e); 
	}
	
	@PutMapping("equipe")
	public Equipe updateEquipe(@RequestBody Equipe e) {
	return er.save(e);
	}
	
	
	@DeleteMapping("equipe/{id}")
	public void deleteEquipe(@PathVariable Long id) {
	
	List<Joueur> joueurs=getJoueurByEquipeId(id); 
	List<Matche> matches1=getMatchByEquipe1Id(id);
	List<Matche> matches2=getMatchByEquipe2Id(id);
	
	joueurs.forEach(m->{//expression lambda
		m.setEquipe(null);
	});
	matches1.forEach(m->{
		m.setEquipe1(null);
	});
	matches2.forEach(m->{
		m.setEquipe2(null);
	});
	
	er.deleteById(id);
	}
	
	

}
