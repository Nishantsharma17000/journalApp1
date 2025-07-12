package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import  org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

     private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

     private static final Logger logger= LoggerFactory.getLogger(UserService.class);
     public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }
         catch (Exception e){
            logger.info("hahahahahahhhhhhh");
//            logger.warn("hoglaf");
//            logger.debug("adfdf;jsd000000");
//            logger.trace("afjsdfjsdlfjsd");

            return false;
         }
     }

     public void saveAdmin(User user){
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(Arrays.asList("USER","ADMIN"));
         userRepository.save(user);
     }

     public void saveUser(User user){

         userRepository.save(user);
     }

     public List<User> getAll(){
         return userRepository.findAll();
     }

     public Optional<User> findById(ObjectId id){
         return userRepository.findById(id);
     }

     public void deleteById(ObjectId id){
         userRepository.deleteById(id);
     }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
