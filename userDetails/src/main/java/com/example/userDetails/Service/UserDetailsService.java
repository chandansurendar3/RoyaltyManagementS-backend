package com.example.userDetails.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userDetails.Repositories.ArtistRepository;
import com.example.userDetails.Repositories.ManagerRepository;
import com.example.userDetails.Repositories.UserDetailsDTO;
import com.example.userDetails.Repositories.UserRepository;
import com.example.userDetails.model.Artist;
import com.example.userDetails.model.Manager;
import com.example.userDetails.model.User;


//@Service
//public class UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ArtistRepository artistRepository;
//
//    @Autowired
//    private ManagerRepository managerRepository;
//
//    public UserDetailsDTO getUserDetailsByUserId(Long userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//       //     Artist artist = artistRepository.findByUser(user);
//        //    Manager manager = managerRepository.findByUser(user);
//
//            String detailType = null;
//            if (artist != null) {
//                detailType = "artist";
//            } else if (manager != null) {
//                detailType = "manager";
//            }
//
//            return new UserDetailsDTO(user, artist, manager, detailType);
//        }
//        return null;
//    }
//
//    public Artist updateArtistDetails(Long userId, Artist artistDetails) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            Artist artist = artistRepository.findByUser(user);
//            if (artist != null) {
//                artist.setCountry(artistDetails.getCountry());
//                artist.setPhone_no(artistDetails.getPhone_no());
//                return artistRepository.save(artist);
//            }
//        }
//        return null;
//    }
//
//    public Manager updateManagerDetails(Long userId, Manager managerDetails) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            Manager manager = managerRepository.findByUser(user);
//            if (manager != null) {
//                manager.setManager_name(managerDetails.getManager_name());
//                manager.setCountry(managerDetails.getCountry());
//                manager.setPhone(managerDetails.getPhone());
//                return managerRepository.save(manager);
//            }
//        }
//        return null;
//    }
//
//    public User updateUserEmail(Long userId, String email) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setEmailid(email);
//            return userRepository.save(user);
//        }
//        return null;
//    }
//
//    public List<Long> getArtistsByManagerid(Long managerid) {
//        return artistRepository.findByManagerid(managerid);
//    }
//}

@Service
public class UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ManagerRepository managerRepository;

//    public UserDetailsDTO getUserDetailsByUserId(Long userId) {
//    	System.out.println(userId);
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            System.out.println(user);
//            Long artistId = user.getUserid();
//            
////            Long managerId = user.getUserid();
////            System.out.println(managerId);
//
//            Artist artist = artistRepository.findById(artistId).orElse(null);
//            System.out.println(artist);
//            Long managerId = user.getUserid();
////            System.out.println(managerId);
//            
//            Manager manager = managerRepository.findById(managerId).orElse(null);
//            System.out.println(manager);
//
//            String detailType = null;
//            if (artist != null) {
//                detailType = "artist";
//            } else if (manager != null) {
//                detailType = "manager";
//            }
//
//            return new UserDetailsDTO(user, artist, manager, detailType);
//        }
//        return null;
//    }
    
    public UserDetailsDTO getUserDetailsByUserId(Long userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
        User user = userOptional.get();
        System.out.println(user);
        Long artistId = user.getUserid();
        
//        Long managerId = user.getUserid();
//        System.out.println(managerId);

        Artist artist = artistRepository.findById(artistId).orElse(null);
        System.out.println(artist);
        Long managerId = user.getUserid();
//        System.out.println(managerId);
        
        Manager manager = managerRepository.findById(managerId).orElse(null);
        System.out.println(manager);
    	String managerName=null;
        if(artist!=null) {
        managerName = managerName(artist.getArtistid());}
        

//        String userType = null;
//        if (artist != null) {
//            userType = "artist";
//        } else if (manager != null) {
//            userType = "manager";
//        }

        return new UserDetailsDTO(user, artist, manager,managerName); //userType
    }
    return null;
}
    
    public   String managerName (Long artistid) {
    	return artistRepository.findManagerNameByArtistId(artistid);
    }
    

    public Artist updateArtistDetails(Long artistId, Artist artistDetails) {
        return artistRepository.findById(artistId).map(artist -> {
            artist.setCountry(artistDetails.getCountry());
            artist.setPhone_no(artistDetails.getPhone_no());
            return artistRepository.save(artist);
        }).orElse(null);
    }

    public Manager updateManagerDetails(Long managerId, Manager managerDetails) {
        return managerRepository.findById(managerId).map(manager -> {
           // manager.setManager_name(managerDetails.getManager_name());
            manager.setCompany(managerDetails.getCompany());
           // manager.setPhone(managerDetails.getPhone());
            return managerRepository.save(manager);
        }).orElse(null);
    }

    public User updateUserEmail(Long userId, String email) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmailid(email);
            return userRepository.save(user);
        }
        return null;
    }

    public Artist getArtistByArtistId(Long artistId) {
        return artistRepository.findById(artistId).orElse(null);
    }

    public Manager getManagerByManagerId(Long managerId) {
        return managerRepository.findById(managerId).orElse(null);
    }
    
    public List<Long> getArtistsByManagerid(Long managerid) {
        return artistRepository.findByManagerid(managerid);
    }
}