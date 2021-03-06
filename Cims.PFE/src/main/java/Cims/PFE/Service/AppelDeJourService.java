////package Cims.PFE.Service;
////
////import java.math.BigInteger;
////import java.util.ArrayList;
////import java.util.Date;
////import java.util.List;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import Cims.PFE.Dao.AppelDeJourRepository;
////import Cims.PFE.Dao.CongeRepository;
////import Cims.PFE.Dao.PersonnelRepository;
////import Cims.PFE.Dao.Pesonnel_Absent_SJRepository;
////import Cims.PFE.Entities.AppelDeJour;
////import Cims.PFE.Entities.Conge;
////import Cims.PFE.Entities.Personnel;
////import Cims.PFE.Entities.Pesonnel_Absent_SJ;
////
////
////@Service
////public class AppelDeJourService {
////
////	@Autowired
////	AppelDeJourRepository AppelDeJourRepository;
////
////	@Autowired
////	private PersonnelRepository personnelRepository;
////	@Autowired
////	private CongeRepository congeRepository;
////	
//////	@Autowired
//////    private Pesonnel_Absent_SJRepository PASJ;
////
////	// public void save(AppelDeJour a){
////	//
////	// List<Personnel> Listepersonnel = personnelRepository.findAll();
////	// for(Personnel Personnel1 : Listepersonnel)
////	// {
////	// a.setDatedujour(java.sql.Date.valueOf(LocalDate.now()));
////	// a.setEtat("P");
////	//// a.setPersonnels(Personnel1.);
////	//
////	// }
////	// AppelDeJourRepository.save(a);
////	//
////	// }
////
////	public void ajouteAuListeAbsence(long personnel_id, Date date) {
////		List<Conge> conges = new ArrayList<>();
////		congeRepository.congeparPersonnelParDate(personnel_id, date);
////		/*if(conges.isEmpty())
////		{
////			AppelDeJour aj = AppelDeJourRepository.findByDatedujour(date);
////			aj.setEtat("Sansjusitf");
////			AppelDeJourRepository.save(aj);
////		}
////		else
////		{
////			AppelDeJour aj = AppelDeJourRepository.findByDatedujour(date);
////			aj.setEtat("jusitf");
////			AppelDeJourRepository.save(aj);
////		}*/
////		Personnel p = personnelRepository.findById(personnel_id).get();
////		AppelDeJour a = AppelDeJourRepository.findByDatedujour(date);
////		if (a == null) {
////			AppelDeJour newAppel = new AppelDeJour();
////			newAppel.setDatedujour(date);
////
////			if ((newAppel.getPersonnels() == null)&& (conges.isEmpty())) {
////				List<Personnel> personnels = new ArrayList<>();
////				personnels.add(p);
////				newAppel.setPersonnels(personnels);
////				newAppel.setEtat("Sansjusitf");
////				AppelDeJourRepository.save(newAppel);
////			}
////			// else {
////			// newAppel.getPersonnels().add(p);
////			//
////			//
////			// }
////		} else {
////			if (a.getPersonnels() == null) {
////
////				List<Personnel> personnels = new ArrayList<>();
////				personnels.add(p);
////				a.setPersonnels(personnels);
////				a.setEtat("jusitf");
////				AppelDeJourRepository.save(a);
////
////			} else {
////				a.getPersonnels().add(p);
////				AppelDeJourRepository.save(a);
////
////			}
////		}
////
////		// return a.getPersonnels();
////	}
////	
////	public void supprimerPersonnelDeLaListe(long personnel_id, Date date)
////	{
////		AppelDeJour a = AppelDeJourRepository.findByDatedujour(date);
////		int nbPersonnel=a.getPersonnels().size();	
////		for(int i = 0; i < nbPersonnel; i++)
////		{
////			if(a.getPersonnels().get(i).getId_personnel() == personnel_id)
////			{
////				a.getPersonnels().remove(i);
////				AppelDeJourRepository.save(a);
////				break;
////			}
////			
////		}
////	}
/////*	public List<Pesonnel_Absent_SJ>listAllAbsent(){
////		//List <Pesonnel_Absent_SJ> idp=PASJ.ListeAbsenceSansJustifiaction();
////		
//////		for(Pesonnel_Absent_SJ i : idp){
//////			Personnel p1 = new Personnel ();
//////			p1=personnelRepository.getOne(i.getId_personnel());
//////			i.setMatricule(p1.getMatricule());
//////			i.setNom(p1.getNom());
//////			i.setPrenom(p1.getPrenom());
//////			i.setNom_AR(p1.getNom_AR());
//////			i.setPrenom_AR(p1.getPrenom_AR());
//////			
//////			
//////		}
////		return idp;
////		
////		
////	}*/
////	public List<Personnel>listnonAbsent(Date date){
////		List <Personnel> p = new ArrayList();
////		List <BigInteger> idp=personnelRepository.listNonAbsenceParJour(date);
////		
////		for(BigInteger i : idp){
////			Personnel p1 = new Personnel ();
////			p1=personnelRepository.getOne(i.longValue());
////			p.add(p1);
//
//package Cims.PFE.Service;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import Cims.PFE.Dao.AppelDeJourRepository;
//import Cims.PFE.Dao.PersonnelRepository;
//import Cims.PFE.Entities.AppelDeJour;
//import Cims.PFE.Entities.Personnel;
//import Cims.PFE.Entities.Pesonnel_Absent_SJ;
//
//@Service
//public class AppelDeJourService {
//
//	@Autowired
//	AppelDeJourRepository AppelDeJourRepository;
//
//	@Autowired
//	private PersonnelRepository personnelRepository;
//
//	// @Autowired
//	// private Pesonnel_Absent_SJRepository PASJ;
//
//	// public void save(AppelDeJour a){
//	//
//	// List<Personnel> Listepersonnel = personnelRepository.findAll();
//	// for(Personnel Personnel1 : Listepersonnel)
//	// {
//	// a.setDatedujour(java.sql.Date.valueOf(LocalDate.now()));
//	// a.setEtat("P");
//	//// a.setPersonnels(Personnel1.);
//	//
//	// }
//	// AppelDeJourRepository.save(a);
//	//
//	// }
//
//	/*public void ajouteAuListeAbsence(long personnel_id, Date date) {
//		Personnel p = personnelRepository.findById(personnel_id).get();
//		AppelDeJour a = AppelDeJourRepository.findByDatedujour(date);
//		if (a == null) {
//			AppelDeJour newAppel = new AppelDeJour();
//			newAppel.setDatedujour(date);
//
//			if (newAppel.getPersonnels() == null) {
//				List<Personnel> personnels = new ArrayList<>();
//				personnels.add(p);
//				newAppel.setPersonnels(personnels);
//				newAppel.setEtat("Sansjusitf");
//				AppelDeJourRepository.save(newAppel);
//			}
//			// else {
//			// newAppel.getPersonnels().add(p);
//			//
//			//
//			// }
//		} else {
//			if (a.getPersonnels() == null) {
//
//				List<Personnel> personnels = new ArrayList<>();
//				personnels.add(p);
//				a.setPersonnels(personnels);
//				AppelDeJourRepository.save(a);
//
//			} else {
//				a.getPersonnels().add(p);
//				AppelDeJourRepository.save(a);
//
//			}
//		}
//
//		// return a.getPersonnels();
//	}
//
//	public void supprimerPersonnelDeLaListe(long personnel_id, Date date) {
//		AppelDeJour a = AppelDeJourRepository.findByDatedujour(date);
//		int nbPersonnel = a.getPersonnels().size();
//		for (int i = 0; i < nbPersonnel; i++) {
//			if (a.getPersonnels().get(i).getId_personnel() == personnel_id) {
//				a.getPersonnels().remove(i);
//				AppelDeJourRepository.save(a);
//				break;
//			}
//
//		}
//	}
//
//	/*
//	 * public List<Pesonnel_Absent_SJ>listAllAbsent(){ //List
//	 * <Pesonnel_Absent_SJ> idp=PASJ.ListeAbsenceSansJustifiaction();
//	 * 
//	 * // for(Pesonnel_Absent_SJ i : idp){ // Personnel p1 = new Personnel ();
//	 * // p1=personnelRepository.getOne(i.getId_personnel()); //
//	 * i.setMatricule(p1.getMatricule()); // i.setNom(p1.getNom()); //
//	 * i.setPrenom(p1.getPrenom()); // i.setNom_AR(p1.getNom_AR()); //
//	 * i.setPrenom_AR(p1.getPrenom_AR()); // // // } return idp;
//	 * 
//	 * 
//	 * }
//	 */
//	/*public List<Personnel> listnonAbsent(Date date) {
//		List<Personnel> p = new ArrayList();
//		List<BigInteger> idp = personnelRepository.listNonAbsenceParJour(date);
//
//		for (BigInteger i : idp) {
//			Personnel p1 = new Personnel();
//			p1 = personnelRepository.getOne(i.longValue());
//			p.add(p1);
//
//		}
//		return p;
//
//	}*/
//
//	/*public List<Object>listAllAbsent() {
////		List<Pesonnel_Absent_SJ> listPSJ = new ArrayList<>();
//		ArrayList<Object> list = new ArrayList<Object>();
//		list=AppelDeJourRepository.ListeAbsenceSansJustifiaction2();
//		int n=list.size()/7;
////		for(Object i :list)
////		{
////			
////			
//>>>>>>> branch 'master' of https://github.com/Oussamadababi/Cims.git
////			
////			
////		}
//<<<<<<< HEAD
////		return p;
////		
////		
////	}
////
////	public List<Object>listAllAbsent(){
////		return AppelDeJourRepository.ListeAbsenceSansJustifiaction2();
////	}
////	
////
////}
//=======
//	
////			for(int j=0;j<n;j++)
////			{
////				Pesonnel_Absent_SJ PASJ= new Pesonnel_Absent_SJ();
////				for(int i=j*7;i<j*7+7;i++)
////				{
////					SimpleDateFormat formatter1=new SimpleDateFormat("yyyy/MM/dd/"); 
////				    PASJ.setDatedujour(formatter1.parse((String) list.get(i)));
////					PASJ.setNom((String) list.get(i+1));
////					PASJ.setPrenom((String) list.get(i+2));
////					PASJ.setNom_AR((String) list.get(i+3));
////					PASJ.setPrenom_AR((String) list.get(i+4));
////					PASJ.setMatricule(Integer.parseInt((String) list.get(i+5)));
////					PASJ.setPoste_Occupe((String) list.get(i+6));
////					
////				}
////				
////				listPSJ.add(PASJ);
////				
////			}
//			
//		
//		return list;
//	}
//	
//
//}*/

