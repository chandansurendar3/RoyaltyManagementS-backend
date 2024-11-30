package com.example.userDetails.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.userDetails.model.Artist;
import com.example.userDetails.model.User;

public interface ArtistRepository extends JpaRepository<Artist,Long> {

//	Artist findByUser(User user);
	

//    List<Artist> findAllByManagerId(@Param("managerid") Long managerid); 
	@Query("SELECT a.artistid FROM Artist a WHERE a.managerid = :managerid")
	List<Long> findByManagerid(Long managerid);

	@Query("SELECT m.manager_name FROM Manager m WHERE m.managerid = (SELECT a.managerid FROM Artist a WHERE a.artistid = :artistid)")
    String findManagerNameByArtistId(@Param("artistid") Long artistid);
	
	
}


