package org.sid.web;

import lombok.Data;
import org.sid.entites.AppRole;
import org.sid.entites.AppUser;
import org.sid.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/rest/AppRigister")
public class UserController {
    @Autowired
    AccountService accountService;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserForm userForm){
        ResponseEntity<String> response = null;
        try {
            // see note 1
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(accountService.saveUser(userForm.getUsername(),userForm.getNom(),userForm.getPrenom(),userForm.getFonction(),userForm.getPassword(),userForm.getConfirmedPassword(),userForm.getRoles())
);
        }
        catch(Exception e) {
            System.out.println( ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage()));

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

    }
    @CrossOrigin(origins = "*")
    @PatchMapping("/updateActivation")
    public AppUser updateActivation(@RequestBody UserFormActivation data){
        return accountService.updateActivation(data.isActived(),data.getUsername());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/getAllUsers")
    public List<AppUser> getAllUsers(){
        return accountService.getAllUsers();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllRoles")
    public List<AppRole> getAllRoles(){
        return accountService.getAllRoles();
    }
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
@Data
class UserFormActivation{
    private String username;
    private boolean actived;
}