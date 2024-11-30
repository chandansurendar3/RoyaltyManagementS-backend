package com.capstone.DashboardCapstone.Repositories;
 
import java.util.List;
import java.util.Map;
 
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.capstone.DashboardCapstone.Entites.Songs;
import com.capstone.DashboardCapstone.Entites.Streams;
 
public interface StreamRepository extends JpaRepository<Streams, Long> {
	    @Query("SELECT s.date, SUM(s.streams) FROM Streams s GROUP BY s.date")
	    List<Object[]> findAllStreamsGroupedByDate();
		@Query("SELECT s.date, SUM(s.royalty) FROM Streams s GROUP BY s.date")
	    List<Object[]> findAllRoyaltyGroupedByDate();
	    @Query("SELECT s.date, SUM(s.streams) FROM Streams s JOIN s.song so JOIN so.artist a WHERE a.id = :artistId GROUP BY s.date")
	    List<Object[]> findTotalStreamsByArtistIdGroupedByDate(@Param("artistId") Long artistId);
	    @Query("SELECT s.date, SUM(s.royalty) FROM Streams s JOIN s.song so JOIN so.artist a WHERE a.id = :artistId GROUP BY s.date")
	    List<Object[]> findTotalRoyaltyByArtistIdGroupedByDate(@Param("artistId") Long artistId);
	    @Query("SELECT so.songId, so.title,ca.stageName, SUM(s.streams), SUM(s.royalty) FROM Streams s JOIN s.song so Left JOIN Artists ca ON ca.artistId = so.collaborationArtistId WHERE so.artist.id = :artistId GROUP BY so.songId, so.title ORDER BY SUM(s.streams) DESC")
	     List<Object[]> findSongsByArtistIdWithStreamsAndRoyalty(@Param("artistId") Long artistId);
	    @Query("SELECT s.date, SUM(s.streams) FROM Streams s JOIN s.song so JOIN so.artist a WHERE a.manager.managerId = :managerId GROUP BY s.date ORDER BY s.date")
	     List<Object[]> findTotalStreamsByManagerIdGroupedByDate(@Param("managerId") Long managerId);
		@Query("SELECT s.date, SUM(s.royalty) FROM Streams s JOIN s.song so JOIN so.artist a WHERE a.manager.managerId = :managerId GROUP BY s.date ORDER BY s.date")
		List<Object[]> findTotalRoyaltyByManagerIdGroupedByDate(@Param("managerId") Long managerId);
}