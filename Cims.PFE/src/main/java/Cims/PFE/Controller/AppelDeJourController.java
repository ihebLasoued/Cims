
//package Cims.PFE.Controller;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import Cims.PFE.Entities.Personnel;
//import Cims.PFE.Entities.Pesonnel_Absent_SJ;
//import Cims.PFE.Service.AppelDeJourService;
//@CrossOrigin(origins = "http://localhost:4200")
//@RestController
//@RequestMapping("/api")
//public class AppelDeJourController {
//	@Autowired
//	AppelDeJourService appelDeJourService;
//	
//
//	@PutMapping(value = "ListeAbsence/{personnel_id}/{date}")
//	public void ajouteAuListeAbsence(@PathVariable("personnel_id") long personnel_id,
//			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//		appelDeJourService.ajouteAuListeAbsence(personnel_id, date);
//	}
//	@PutMapping(value = "supprimerPersonnelDeLaListe/{personnel_id}/{date}")
//	public void supprimerPersonnelDeLaListe(@PathVariable("personnel_id")long personnel_id,@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date)
//	{
//		appelDeJourService.supprimerPersonnelDeLaListe(personnel_id, date);
//	}
//	
//	/*@GetMapping(value = "/listPAbsence")
//	public List<Pesonnel_Absent_SJ> listAbsence() {
//		return appelDeJourService.listAllAbsent();
//	}*/
//	@GetMapping(value = "/listnonAbsent/{date}")
//	public List<Personnel> listnonAbsent(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//		return appelDeJourService.listnonAbsent(date);
//	}
//	@GetMapping(value = "/listAllAbsent")
//	public List<Object> listAllAbsent() {
//		return appelDeJourService.listAllAbsent();
//	}
//
//}

