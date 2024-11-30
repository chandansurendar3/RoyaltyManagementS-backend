package com.capstone.DashboardCapstone.Modules;
 
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
 
import com.capstone.DashboardCapstone.Entites.Artists;
import com.capstone.DashboardCapstone.Entites.Managers;
import com.capstone.DashboardCapstone.Entites.Songs;
import com.capstone.DashboardCapstone.Repositories.ArtistRepository;
import com.capstone.DashboardCapstone.Repositories.ManagerRepository;
import com.capstone.DashboardCapstone.Repositories.SongRepository;
import com.capstone.DashboardCapstone.Repositories.StreamRepository;
 
@Service
public class DashboardService {
    @Autowired
    private ArtistRepository artistRepository;
 
    @Autowired
    private ManagerRepository managerRepository;
 
    @Autowired
    private StreamRepository streamRepository;
    @Autowired
    private SongRepository songRepository;
 
    public List<Artists> getAllArtists() {
        return artistRepository.findAllActive();
    }
 
    public List<Managers> getAllManagers() {
        return managerRepository.findAllActive();
    }
    
    public List<Songs> findSongsByArtistId(Long artistId) {
        return songRepository.findByArtistIdUsingQuery(artistId);
    }
    
    public Map<LocalDate, Long> findTotalStreamsByArtistIdGroupedByDate(Long artistId) {
        List<Object[]> groupedStreams = streamRepository.findTotalStreamsByArtistIdGroupedByDate(artistId);
        Map<LocalDate, Long> resultMap = new LinkedHashMap<>();
        for (Object[] row : groupedStreams) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate(); // Convert java.sql.Date to LocalDate
            Long totalStreams = (Long) row[1];
            resultMap.put(date, totalStreams);
        }
        return resultMap;
    }

 
    public Map<LocalDate, Double> findTotalRoyaltyByArtistIdGroupedByDate(Long artistId) {
        List<Object[]> groupedRoyalty = streamRepository.findTotalRoyaltyByArtistIdGroupedByDate(artistId);
        Map<LocalDate, Double> resultMap = new LinkedHashMap<>();
        for (Object[] row : groupedRoyalty) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate(); // Convert java.sql.Date to LocalDate
            Double totalRoyalty = (Double) row[1];
            resultMap.put(date, totalRoyalty);
        }
        return resultMap;
    }
    public List<Map<String, Object>> findTop5SongsByArtistId(Long artistId) {
    	List<Object[]> results = streamRepository.findSongsByArtistIdWithStreamsAndRoyalty(artistId);
        return results.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Songid", record[0]);
            map.put("SongName", record[1]);
            map.put("Collab", record[2]);
            map.put("Stream", record[3]);
            map.put("Revenue", record[4]);
            return map;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> findTop5ArtistsByManagerId(Long managerId) {
    	List<Object[]> results=artistRepository.findTop5ArtistsByManagerId(managerId);
        return results.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Artistid", record[0]);
            map.put("ArtistName", record[1]);
            map.put("Totalstream", record[2]);
            map.put("Revenue", record[3]);
            return map;
        }).collect(Collectors.toList());
    }

    public List<Artists> findArtistsByManagerId(Long managerId) {
        return artistRepository.findByManagerId(managerId);
    }
    
    
    public Map<LocalDate, Long> findSumOfStreamsGroupedByDate() {
        List<Object[]> groupedStreams = streamRepository.findAllStreamsGroupedByDate();
        Map<LocalDate, Long> resultMap = new LinkedHashMap<>();
        for (Object[] row : groupedStreams) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Long sumOfStreams = (Long) row[1];
            resultMap.put(date, sumOfStreams);
        }
        return resultMap;
    }
    public Map<LocalDate, Double> findSumOfRoyaltyGroupedByDate() {
        List<Object[]> groupedRoyalty = streamRepository.findAllRoyaltyGroupedByDate();
        Map<LocalDate, Double> resultMap = new LinkedHashMap<>();
        for (Object[] row : groupedRoyalty) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Double sumOfRoyalty = (Double) row[1];
            resultMap.put(date, sumOfRoyalty);
        }
        return resultMap;
    }
    public Map<LocalDate, Long> findSumOfStreamsGroupedByDateManager(Long managerId) {
        List<Object[]> groupedStreams = streamRepository.findTotalStreamsByManagerIdGroupedByDate(managerId);
        Map<LocalDate, Long> resultMap = new LinkedHashMap<>();
        for (Object[] row : groupedStreams) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Long sumOfStreams = (Long) row[1];
            resultMap.put(date, sumOfStreams);
        }
        return resultMap;
    }
    public Map<LocalDate, Double> findSumOfRoyaltyGroupedByDateManager(Long managerId) {
        List<Object[]> groupedStreams = streamRepository.findTotalRoyaltyByManagerIdGroupedByDate(managerId);
        Map<LocalDate, Double> resultMap = new LinkedHashMap<>();
        for (Object[] row : groupedStreams) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Double sumOfRoyalty = (Double) row[1];
            resultMap.put(date, sumOfRoyalty);
        }
        return resultMap;
    }
    
    public List<Map<String, Object>> findTop5ArtistsByTotalStreams() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<Object[]> results=artistRepository.findTop5ArtistsByTotalStreams(pageRequest);
        return results.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Artistid", record[0]);
            map.put("ArtistName", record[1]);
            map.put("Country", record[2]);
            map.put("Manager", record[3]);
            map.put("Totalstream", record[4]);
            map.put("Revenue", record[5]);
            return map;
        }).collect(Collectors.toList());
    }
    
    public List<Map<String, Object>> findTop5ManagersByTotalRoyalty() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<Object[]> results=managerRepository.findTop5ManagersByTotalRoyalty(pageRequest);
        return results.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Managerid", record[0]);
            map.put("Company", record[1]);
            map.put("ManagerName", record[2]);
            map.put("Totalstream", record[3]);
            map.put("Revenue", record[4]);
            return map;
        }).collect(Collectors.toList());
    }
    public Map<String, Object> findArtistAndManager(Long artistId) {
        List<Object[]> results = artistRepository.findArtistAndManager(artistId);
        if (results.isEmpty()) {
            return new HashMap<>(); // or throw an exception if no record is found
        }
        Object[] record = results.get(0); // Get the first element
        Map<String, Object> map = new HashMap<>();
        map.put("ArtistName", record[0]);
        map.put("ManagerName", record[1]);
        return map;
    }
    public Map<String, Object> findManagerAndCompany(Long managerId) {
        List<Object[]> results = managerRepository.findManagerNameAndCompany(managerId);
        if (results.isEmpty()) {
            return new HashMap<>(); // or throw an exception if no record is found
        }
        Object[] record = results.get(0); // Get the first element
        Map<String, Object> map = new HashMap<>();
        map.put("ManagerName", record[0]);
        map.put("Company", record[1]);
        return map;
    }
 
}