package spring.tp.controllers;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.tp.entities.Equipe;
import spring.tp.entities.Matche;
import spring.tp.entities.Stade;
import spring.tp.services.MatcheService;




@RestController
public class MatcheController {

	@Autowired 
	MatcheService ms;
	
	@GetMapping("matche")
		public List<Matche> getAllMatches(){
		return ms.getAllMatches();
	}
	
	@GetMapping("matche/{id}")
		public Matche getMatcheById(@PathVariable Long id) {
		return ms.getMatcheById(id); 
	}
	
	@GetMapping("matche/{id}/stade")
	public Stade getStadeByMatcheId(@PathVariable Long id) {
	return ms.getStadeByMatcheId(id); 
}
	@GetMapping("matche/{id}/equipes")
	public List<Equipe> getEquipesByMatcheId(@PathVariable Long id) {
		return ms.getEquipesByMatcheId(id);
	
}
	
	@GetMapping("matche/date/{date}")
	public List<Matche> findByDateMatchEquals(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
	
	    return ms.findByDateMatchEquals(date);

}
	@PostMapping("matche")
		public Matche addMatche(@RequestBody Matche s) {
		return ms.addMatche(s);
	}
	
	@PutMapping("matche")
		public Matche updateMatche(@RequestBody Matche s) {
		return ms.updateMatche(s);
	}
	
	@DeleteMapping("matche/{id}")
		public void deleteMatche(@PathVariable Long id) {
		ms.deleteMatche(id);
	}
	
	@DeleteMapping("matche")
	public List<Matche> deleteMatchesLessThanNow() {
		return ms.deleteMatchesLessThanNow();
}	
	
	public List<Matche> getAllMatches(int page, int size, String feild){
		return ms.getAllMatches(page, size, feild);
	}
}
