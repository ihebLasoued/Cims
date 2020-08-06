package Cims.PFE.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.RecuperationSoldeReposRepository;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Entities.RecuperationSoldeRepos;

@Service
public class RecuperationSoldeReposService {
	
	@Autowired
	RecuperationSoldeReposRepository recuperationSoldeReposRepository;
	@Autowired
	PersonnelService  personnelService;
	
public RecuperationSoldeRepos save(RecuperationSoldeRepos Ac){
		
		return recuperationSoldeReposRepository.save(Ac);
	}
	
	public List<RecuperationSoldeRepos> listAll() {
		List<RecuperationSoldeRepos> ListeRecuperationSoldeR = new ArrayList<>();
		recuperationSoldeReposRepository.findAll().forEach(ListeRecuperationSoldeR::add);
		return ListeRecuperationSoldeR;
	}
	
	public  RecuperationSoldeRepos ajouterdemandeRSR (RecuperationSoldeRepos c,Long id_p){
		Personnel P = new Personnel();
		P=personnelService.getById(id_p);
		c.setP(P);
		c.setDatedemande(java.sql.Date.valueOf(LocalDate.now()));
		c.setEtat("En_attente");
		c.setSoldeRecuperer(0);
		c.setTitreAnnee(String.valueOf(LocalDate.now().getYear()-2));
		return save(c);

}
	//accepter la demande et changer le solde repos vers le solde recuperer
	public void AccepterDemandeRSR(Long id){
		RecuperationSoldeRepos RSR = new RecuperationSoldeRepos ();
		RSR=recuperationSoldeReposRepository.getOne(id);
		RSR.setEtat("Valider");
		RSR.setSoldeRecuperer(RSR.getP().getSoldeRepos());
		Personnel P = new Personnel();
		P=personnelService.getById(RSR.getP().getId_personnel());
		P.setSoldeRepos(0);
		personnelService.save(P);
		recuperationSoldeReposRepository.save(RSR);	
	}


}