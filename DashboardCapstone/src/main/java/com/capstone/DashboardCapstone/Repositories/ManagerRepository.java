package com.capstone.DashboardCapstone.Repositories;
 
import java.awt.print.Pageable;
import java.util.List;
 
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.capstone.DashboardCapstone.Entites.Managers;
 
public interface ManagerRepository extends JpaRepository<Managers, Long> {
	@Query("SELECT m FROM Managers m WHERE m.isDeleted = false")
    List<Managers> findAllActive();
	@Query("SELECT m.managerId, m.company,m.managerName, SUM(s.streams),SUM(s.royalty) FROM Streams s JOIN s.song so JOIN so.artist a JOIN a.manager m GROUP BY m.managerId ORDER BY SUM(s.royalty) DESC")
	    List<Object[]> findTop5ManagersByTotalRoyalty(PageRequest pageRequest);
	@Query("SELECT m.managerName,m.company FROM Managers m WHERE m.managerId = :managerId")
	List<Object[]> findManagerNameAndCompany(@Param("managerId") Long managerId);
}