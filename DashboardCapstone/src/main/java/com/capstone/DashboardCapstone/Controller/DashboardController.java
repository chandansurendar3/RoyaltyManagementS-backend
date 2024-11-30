package com.capstone.DashboardCapstone.Controller;
 
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.capstone.DashboardCapstone.Entites.Artists;
import com.capstone.DashboardCapstone.Entites.Managers;
import com.capstone.DashboardCapstone.Entites.Songs;
import com.capstone.DashboardCapstone.Modules.DashboardService;
 
@RestController
@CrossOrigin(value = {"http://localhost:3000/","https://royalty-management-system-kdrb.vercel.app/"})
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;
       //artist and associated manager name for artist details seacrh bar
    @GetMapping("/admin/artists-managers")
    public ResponseEntity<?> getAllArtistsAndManagers() {
        List<Artists> artists = dashboardService.getAllArtists();
        List<Managers> managers = dashboardService.getAllManagers();
 
        // Extracting id:name pairs from the lists
        Map<Long, String> artistIdNameMap = artists.stream()
                .collect(Collectors.toMap(Artists::getArtistId, Artists::getStageName));
 
        Map<Long, String> managerIdNameMap = managers.stream()
                .collect(Collectors.toMap(Managers::getManagerId, Managers::getCompany));
 
        Map<String, Object> response = new HashMap<>();
        response.put("artist", artistIdNameMap);
        response.put("manager", managerIdNameMap);
        return ResponseEntity.ok(response);
    }
 
    @GetMapping("/admin/top-artists")
    public List<Map<String, Object>> getTop5ArtistsByTotalStreams() {
        return dashboardService.findTop5ArtistsByTotalStreams();
    }
    
    @GetMapping("/admin/top-managers")
    public List<Map<String, Object>> getTop5ManagersByTotalRoyalty() {
        return dashboardService.findTop5ManagersByTotalRoyalty();
    }
    
    @GetMapping("/admin/daily-streams")
    public ResponseEntity<Map<LocalDate, Long>> getSumOfStreamsGroupedByDate() {
        Map<LocalDate, Long> resultMap = dashboardService.findSumOfStreamsGroupedByDate();
        return ResponseEntity.ok().body(resultMap);
    }
    //total revenue of company in chart
  @GetMapping("/admin/daily-revenue")
  public ResponseEntity<Map<LocalDate, Double>> getSumOfRoyaltyGroupedByDate() {
      Map<LocalDate, Double> resultMap = dashboardService.findSumOfRoyaltyGroupedByDate();
      return ResponseEntity.ok().body(resultMap);
  }
  //songs
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<?> getSongsByArtistId(@PathVariable Long artistId) {
    	List<String> songNames = dashboardService.findSongsByArtistId(artistId).stream().map(Songs::getTitle).collect(Collectors.toList());
    	Map<String, Object> response = new HashMap<>();
        response.put("songNames", songNames);
        return ResponseEntity.ok(response);
    }
    //streams of the astist for the chart
    @GetMapping("/artist/total-by-artist/{artistId}")
    public Map<LocalDate, Long> getTotalStreamsByArtistIdGroupedByDate(@PathVariable Long artistId) {
        return dashboardService.findTotalStreamsByArtistIdGroupedByDate(artistId);
    }
    //royalty charts
    @GetMapping("/artist/total-by-artist-royalty/{artistId}")
    public Map<LocalDate, Double> getTotalRoyaltyByArtistIdGroupedByDate(@PathVariable Long artistId) {
        return dashboardService.findTotalRoyaltyByArtistIdGroupedByDate(artistId);
    }
    //songs on the artist dash based on streams
    @GetMapping("/artist/top5-by-artist/{artistId}")
    public List<Map<String, Object>> getTop5SongsByArtistId(@PathVariable Long artistId) {
        return dashboardService.findTop5SongsByArtistId(artistId);
    }
    //artist and asscoiated manager name in the artist details
    @GetMapping("/artist-manager/{artistId}")
    public Map<String, Object> getArtistManagerName(@PathVariable Long artistId) {
        return dashboardService.findArtistAndManager(artistId);
    }
 
    
    @GetMapping("/manager/{managerId}")
    public ResponseEntity<?> getArtistsByManagerId(@PathVariable Long managerId) {
    	List<String> artistNames= dashboardService.findArtistsByManagerId(managerId).stream().map(Artists::getStageName).collect(Collectors.toList());
    	Map<String, Object> response = new HashMap<>();
        response.put("artistNames", artistNames);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/manager-company/{managerId}")
    public Map<String, Object> getManagerCompany(@PathVariable Long managerId) {
        return dashboardService.findManagerAndCompany(managerId);
    }
 
    @GetMapping("/manager/total-streams-by-manager/{managerId}")
    public Map<LocalDate, Long> getTotalStreamsByManagerIdGroupedByDate(@PathVariable Long managerId) {
        return dashboardService.findSumOfStreamsGroupedByDateManager(managerId);
    }
    @GetMapping("/manager/total-royalty-by-manager/{managerId}")
    public Map<LocalDate, Double> getTotalRoyaltyByManagerIdGroupedByDate(@PathVariable Long managerId) {
        return dashboardService.findSumOfRoyaltyGroupedByDateManager(managerId);
    }
    @GetMapping("/manager/top5-artists/{managerId}")
    public List<Map<String, Object>> getTop5ArtistsByManagerId(@PathVariable Long managerId) {
        return dashboardService.findTop5ArtistsByManagerId(managerId);
    }

}