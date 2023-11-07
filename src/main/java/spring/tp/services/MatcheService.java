package spring.tp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import spring.tp.entities.Equipe;
import spring.tp.entities.Matche;
import spring.tp.entities.Stade;
import spring.tp.repositories.MatcheRepository;

@Service
public class MatcheService {

	@Autowired 
	MatcheRepository mr;
	
	public List<Matche> getAllMatches(){
		return mr.findAll();
	}
	
	public Matche getMatcheById( Long id) {
		return mr.findById(id).get(); 
	}
	
	public Stade getStadeByMatcheId( Long id) {
		return mr.findById(id).get().getStade(); 
}
	public List<Equipe> getEquipesByMatcheId( Long id) {
		List<Equipe> equipes=new ArrayList<>() ;
		
		Equipe equipe1 = mr.findById(id).get().getEquipe1(); 
		equipes.add(equipe1);
		Equipe equipe2 = mr.findById(id).get().getEquipe2();
		equipes.add(equipe2);
		return equipes;
	
}
	
	public List<Matche> findByDateMatchEquals(  LocalDate date) {
	
	    Date convertedDate = java.sql.Date.valueOf(date);
		return mr.findByDateMatchEquals(convertedDate); 

}
		public Matche addMatche( Matche s) {
		return mr.save(s);
	}
	
		public Matche updateMatche( Matche s) {
		return mr.save(s);
	}
	
		public void deleteMatche( Long id) {
		mr.deleteById(id);
	}
	
	public List<Matche> deleteMatchesLessThanNow() {
		List<Matche> matches= mr.findByDateMatchLessThan(new Date());
		mr.deleteAll(matches);
	return matches;
}	
	
	public List<Matche> getAllMatches(int page, int size, String feild){
		Pageable pg= PageRequest.of(page, size, Sort.by(feild));
		Page<Matche> MatchesPage = mr.findAll(pg); 
		return MatchesPage.getContent();
	}
}
