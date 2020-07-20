package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Entities.Personnel;

@Service
public class PersonnelService {
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	public List<Personnel> listAll(){
		List<Personnel> personnels = new ArrayList<>();
		personnelRepository.findAll().forEach(personnels::add);
		return personnelRepository.findAll();
	}
	
	public Personnel save(Personnel p) {
		return personnelRepository.save(p);
	}


	
	public Personnel update(Long id, Personnel p){
		p.setId_personnel(id);
		return personnelRepository.findById(id).get();
	}
	public Personnel getById(Long id) {
		return personnelRepository.findById(id).get();
	}
	public boolean delete(Long id){
		personnelRepository.deleteById(id);
		if(personnelRepository.existsById(id)==false)
		{
			return true;
		}
			return false;
		
	}
}
