package org.sid;

import lombok.Data;
import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entites.AppRole;
import org.sid.entites.AppUser;
import org.sid.services.AccountService;
import org.sid.services.AccountServiceImpl;
//import org.sid.web.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/rest/AppUsers")
public class ResetPasswordController {

    @Autowired
    private AccountServiceImpl service;

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AccountService accountService;

    @CrossOrigin(origins = "*")
    @PatchMapping("/updateUser")
    public ResponseEntity<String> updateMvmToAdmin(@RequestBody AppUser tulM) {
        ResponseEntity<String> response = null;
        AppUser tula = service.loadUserByUserName(tulM.getUsername());
        System.out.println(tulM.getUsername() + " - " + tulM.getPassword() + " - " + tula.getId());
        if (tula != null) {
            service.updateUser(tulM.getUsername(), tulM.getPassword(), tula.getId());
            response = new ResponseEntity<String>("Mise à Jour reussie de l'utilisateur : " + tula.getUsername(), HttpStatus.OK);
        } else {
            return response = new ResponseEntity<String>("Erreur d'identifaction ou Utilisateur Non Trouver", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllRole")
    public ResponseEntity getRoles() {
        ResponseEntity response = null;
        List<AppRole> list = service.getRole();
        if (list == null || list.isEmpty()) {
            String message = " nous avons pas trouver de donnees ...";
            response = new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            response = new ResponseEntity<List<AppRole>>(list, HttpStatus.OK);
        }
        return response;
    }



    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm){
        System.out.println(userForm);
        return accountService.saveUser(userForm.getUsername(),userForm.getNom(),userForm.getPrenom(),userForm.getFonction(),userForm.getPassword(),userForm.getConfirmedPassword(),userForm.getRoles());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/isUserActived/{username}")
    public ResponseEntity<String> isUserActived(@PathVariable String username){
        ResponseEntity<String> response = null;
        AppUser tula = service.loadUserByUserName(username);
        System.out.println(tula);
        if(tula != null && tula.isActived()){
            response = new ResponseEntity<String>(tula.getUsername()+" est Actif (ve)!",HttpStatus.OK);
        }else{
            return response = new ResponseEntity<String>(tula.getUsername()+" est Inactif (ve)!",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Data
    class UserForm{
        private String username;
        private String nom;
        private String prenom;
        private String fonction;
        private String password;
        private String confirmedPassword;
        private Collection<String> roles = new ArrayList<>();
    }
}

