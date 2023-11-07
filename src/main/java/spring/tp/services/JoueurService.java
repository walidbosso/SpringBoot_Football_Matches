package spring.tp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import spring.tp.entities.Joueur;
import spring.tp.repositories.JoueurRepository;

@Service
public class JoueurService {

	@Autowired 
	JoueurRepository jr;
	
	public List<Joueur> getAllJoueurs(){
		return jr.findAll();
	}
	public Joueur addJoueur( Joueur s) {
		return jr.save(s);
	}
	public void deleteJoueur( Long id) {
		jr.deleteById(id);
	}
	public Joueur updateJoueur( Joueur s) {
		return jr.save(s);
	}
}
