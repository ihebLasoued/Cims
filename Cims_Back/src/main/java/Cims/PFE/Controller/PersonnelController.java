package Cims.PFE.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.AffectationPartielleRepository;
import Cims.PFE.Dao.CompteRepository;
import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Dao.RoleRepository;
import Cims.PFE.Entities.AffectationPartielle;
import Cims.PFE.Entities.Compte;
import Cims.PFE.Entities.ERole;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Entities.Role;
import Cims.PFE.Service.PersonnelService;
import Cims.PFE.payload.request.SignupRequest;
import Cims.PFE.payload.response.MessageResponse;
import Cims.PFE.security.jwt.JwtUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class PersonnelController {
	
	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private AffectationPartielleRepository Affrepo;

	@Autowired
	JwtUtils jwtUtils;
	
	// liste des personnels sans affectation totale
	@GetMapping(value="/listPersonnelsSansAffecT")
	public List<Personnel> listPersonnelSAffect(){
		List<Personnel> liste=personnelService.listAll();
		List<Personnel> l =new ArrayList<Personnel>();
		for(int i=0;i<liste.size();i++) {
			if(liste.get(i).getAffectationt().isEmpty()) {
				
				l.add(liste.get(i));
			}
		}
		
		return l;
	}
	
	
	@GetMapping(value="/listPersonnels")
	public List<Personnel> listPersonnel(){
		return personnelService.listAll();
	}
	
	@PutMapping(value="/updatePersonnel/{id}")
	public ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody Personnel p){
		Personnel pers=personnelService.getById(id);
		pers.setNom(p.getNom());
		pers.setPrenom(p.getPrenom());
		pers.setSexe(p.getSexe());
		pers.setTelephone(p.getTelephone());
		pers.setDate_recrutement(p.getDate_recrutement());
		pers.setGrade(p.getGrade());
		pers.setDepartement(p.getDepartement());
		pers.setEmail(p.getEmail());
		final Personnel updatedPersonnel=personnelService.save(pers);
		return ResponseEntity.ok(new MessageResponse("Modifier"));
	}
	
	@GetMapping(value="/getPersonnel/{id}")
	public Personnel get(@PathVariable(name="id") Long id) {
		return personnelService.getById(id);
	}
	
	@DeleteMapping(value="/deletePersonnel/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
		List<AffectationPartielle> list=Affrepo.getAffectPartielle(id);
		if(list.isEmpty()) {
			if(personnelService.delete(id)==true) {
				return ResponseEntity.ok(new MessageResponse("Personnel supprimer"));
			}	else return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Échec lors de suppression"));	
		}
		else return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Vous devez supprimer les affectations de personnel avant de supprimer"));
			
		//return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Échec lors de suppression"));	
			
	}
	
	
	@PostMapping("/addPersonnel")
	public ResponseEntity<?> registerUser(@RequestBody Personnel p ,SignupRequest signUpRequest) {
		signUpRequest.setUsername(p.getPrenom()+"."+p.getNom());
		
				
		signUpRequest.setPassword(p.getNom()+"cims");
		Compte compte = new Compte(signUpRequest.getUsername(), 
							 encoder.encode(signUpRequest.getPassword()),null);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_PERSONNEL)
		.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_PERSONNEL)
					 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		if(personnelRepository.existsByEmail(p.getEmail())==false) {
		if( personnelService.save(p)!= null) {
			compte.setRoles(roles);
			p.setUser(compte);
			compte.setPersonnel(p);
			compteRepository.save(compte);
			
		}}
		else return ResponseEntity.badRequest().body(new MessageResponse("Echec d'ajout de personnel :personnel existe déja "));
		 

		return ResponseEntity.ok(new MessageResponse("Personnel ajouté avec succés"));
	}
	
	}