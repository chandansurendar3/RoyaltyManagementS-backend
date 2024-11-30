//package com.RoyalityManagement.ArtistRequest.Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.RoyalityManagement.ArtistRequest.Entity.Managers;
//import com.RoyalityManagement.ArtistRequest.Service.ManagerService;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000") // Allow requests from this origin
//@RequestMapping("/managers")
//public class ManagerController {
//
//    @Autowired
//    private ManagerService managerService;
//    
//    //Fetching all the manager in manager table
//    @GetMapping("/allManagers")
//    public ResponseEntity<List<Managers>> getAllManagers() {
//        List<Managers> managers = managerService.getAllManagers();
//        return ResponseEntity.ok(managers); // Return 200 OK with the list of managers
//    }
//    
//
//}
