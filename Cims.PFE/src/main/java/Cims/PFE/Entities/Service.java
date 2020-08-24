package Cims.PFE.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity  
@Table(name="Service") 
public class Service {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="id_Service")
	private Long id_Service;
	
	@Column(name="nom_ServiceFr")
	private String nom_ServiceFr;
	
	@Column(name="nom_ServiceAr")
	private String nom_ServiceAr;
	
	@ManyToOne
	private Division division;

}