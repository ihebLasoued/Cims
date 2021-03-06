package Cims.PFE.Dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import Cims.PFE.Entities.DashMissParAffPers;
import Cims.PFE.Entities.DashMissParMois;
import Cims.PFE.Entities.Mission;

public interface MissionRepository extends JpaRepository<Mission,Long> {
	@Query(value="SELECT * from mission   where id_affectation_p=?1 ",nativeQuery = true)
	List<Mission> getMission(Long idAffect);
	
	@Query(value="SELECT * from mission   where   etat_accomplie=true order by date DESC",nativeQuery = true)
	List<Mission> getMissionAccomplie();
	
	@Query(value="SELECT count(id_mission) from mission where date=(sELECT DATE( NOW() )) ",nativeQuery = true)
	Long getNbrMissionToday();
	
	@Query(value="SELECT distinct count(id_mission)as nbrMission,id_affectation_p as affectP FROM public.mission natural join affectation_partielle where id_personnel =?1 group by id_affectation_p ; ",nativeQuery = true)
	List<DashMissParAffPers> getMissParAffecP(Long id_personnel);
	
	@Query(value="SELECT  count(id_mission) as nbr,date_trunc ('months', date ) as mois FROM public.mission natural join affectation_partielle where id_personnel =?1 group by date_trunc ('months', date ) ; ",nativeQuery = true)
    List<DashMissParMois> getMissionParMois(Long id_personnel);
	
	@Query(value="SELECT  count(id_mission) as nbr FROM public.mission natural join affectation_partielle where id_personnel =?1 and etat_accomplie=true  ; ",nativeQuery = true)
	Long getNbrMissionAccomplie(Long id_personnel);
	
	@Query(value="SELECT  count(id_mission) as nbr FROM public.mission natural join affectation_partielle where id_personnel =?1 and etat_accomplie=false  ; ",nativeQuery = true)
	Long getNbrMissionNonAccomplie(Long id_personnel);
	
	@Transactional
	@Modifying
	@Query(value="delete from mission   where id_affectation_p=?1 ",nativeQuery = true)
	void deleteMission(Long idAffect);
	
	List<Mission> findByDate(LocalDate d);
	
	@Query(value="SELECT id_personnel FROM public.mission join affectation_partielle using(id_affectation_p) where file_id_file=?1 ",nativeQuery = true)
	Long getIdPersonnel(Long id_file);
	
	@Query(value="SELECT * FROM public.mission  where file_id_file=?1",nativeQuery = true)
	Mission getMissionFILE(Long id_file);
	@Query(value="SELECT * FROM public.mission m join affectation_partielle a on m.id_affectation_p=a.id_affectation_p  where a.id_personnel=?1 and ?2 BETWEEN a.date_debut and a.date_fin ",nativeQuery = true)
	List<Mission> verificationMission(long idPersonnel,Date date);
}
