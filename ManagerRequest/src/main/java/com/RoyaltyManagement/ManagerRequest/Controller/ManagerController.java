//package com.RoyaltyManagement.ManagerRequest.Controller;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.RoyaltyManagement.ManagerRequest.Entity.Managers;
//import com.RoyaltyManagement.ManagerRequest.Repo.ManagerRepository;
//
//@RestController
//@RequestMapping("/managers")
//public class ManagerController {
//	
//	@Autowired
//    private ManagerRepository managerRepository;
 
//    @PutMapping("/{managerId}")
//    public ResponseEntity<Manager> updateManager(@PathVariable Long managerId, @RequestBody Map<String, Long> updates) {
//        return managerRepository.findById(managerId)
//                .map(manager -> {
//                    Long artistId = updates.get("artistId");
//                    if (artistId != null) {
//                        manager.setArtistid(artistId);
//                        managerRepository.save(manager);
//                    }
//                    return ResponseEntity.ok(manager);
//                }).orElse(ResponseEntity.notFound().build());
//    }
//
//}
