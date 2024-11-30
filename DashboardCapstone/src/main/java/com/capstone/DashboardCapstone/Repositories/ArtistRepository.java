package com.capstone.DashboardCapstone.Repositories;
 
import java.awt.print.Pageable;
import java.util.List;
 
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.capstone.DashboardCapstone.Entites.Artists;
 
public interface ArtistRepository extends JpaRepository<Artists, Long> {
	@Query("SELECT a FROM Artists a WHERE a.isDeleted = false")
    List<Artists> findAllActive();
	@Query("SELECT a FROM Artists a WHERE a.manager.id = :managerId")
    List<Artists> findByManagerId(@Param("managerId") Long managerId);
	@Query("SELECT a.artistId, a.stageName,SUM(s.streams), SUM(s.royalty) FROM Streams s JOIN s.song so JOIN so.artist a WHERE a.manager.id = :managerId GROUP BY a.artistId ORDER BY SUM(s.royalty) DESC")
	    List<Object[]> findTop5ArtistsByManagerId(@Param("managerId") Long managerId);
	@Query("SELECT a.artistId, a.stageName,a.country,m.managerName, SUM(s.streams),SUM(s.royalty) FROM Streams s JOIN s.song so JOIN so.artist a LEFT JOIN a.manager m GROUP BY a.artistId ORDER BY SUM(s.streams) DESC")
	     List<Object[]> findTop5ArtistsByTotalStreams(PageRequest pageRequest);
	@Query("SELECT a.stageName,m.managerName From Artists a JOIN a.manager m WHERE a.artistId = :artistId")
	List<Object[]> findArtistAndManager(@Param("artistId") Long artistId);
}