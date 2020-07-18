package Cims.PFE.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity  
@Table(name="ordreMission") 
public class OrdreMission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="idO_Miss")
	private Long idO_Miss;
	
	@Column(name="transport")
	private Transport transport;
	
	@Column(name="moyen_transport")
	private MoyenDeTransport moyenDeTransport;
	
	@Column(name="deplacement")
	private Deplacement deplacement;
	
	@Column(name="hebergement")
	private Hebergement hebergement;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idMission")
	private Mission Mission ;
	
	
	public Long getIdO_Miss() {
		return idO_Miss;
	}
	public void setIdO_Miss(Long idO_Miss) {
		this.idO_Miss = idO_Miss;
	}
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	public Mission getMission() {
		return Mission;
	}
	public void setMission(Mission mission) {
		Mission = mission;
	}
	
	
	
	public Deplacement getDeplacement() {
		return deplacement;
	}
	public void setDeplacement(Deplacement deplacement) {
		this.deplacement = deplacement;
	}
	public Hebergement getHebergement() {
		return hebergement;
	}
	public void setHebergement(Hebergement hebergement) {
		this.hebergement = hebergement;
	}
	
	
	public MoyenDeTransport getMoyenDeTransport() {
		return moyenDeTransport;
	}
	public void setMoyenDeTransport(MoyenDeTransport moyenDeTransport) {
		this.moyenDeTransport = moyenDeTransport;
	}
	
	
	public OrdreMission(Long idO_Miss, Transport transport, MoyenDeTransport moyenDeTransport, Deplacement deplacement,
			Hebergement hebergement, Cims.PFE.Entities.Mission mission) {
		super();
		this.idO_Miss = idO_Miss;
		this.transport = transport;
		this.moyenDeTransport = moyenDeTransport;
		this.deplacement = deplacement;
		this.hebergement = hebergement;
		Mission = mission;
	}
	@Override
	public String toString() {
		return "OrdreMission [idO_Miss=" + idO_Miss + ", transport=" + transport + ", Mission=" + Mission + "]";
	}
	public OrdreMission() {
		super();
	}
	

}
