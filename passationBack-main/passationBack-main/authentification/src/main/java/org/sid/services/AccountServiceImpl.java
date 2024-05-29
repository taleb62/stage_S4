package org.sid.services;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entites.AppRole;
import org.sid.entites.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public void updateUser(String username, String password, Integer id) {
        appUserRepository.updateUser(username,bCryptPasswordEncoder.encode(password),id);
    }
    @Override
    public AppUser getUser(Integer id) {
        return appUserRepository.getUser(id);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppRole> getAllRoles() {
        return appRoleRepository.findAll();
    }

    @Override
    public AppUser saveUser(String username, String nom, String prenom, String fonction, String password, String confirmedPassword, Collection<String> roles) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user!=null) throw new RuntimeException("L'utilisateur existe déjà");
        if (!password.equals(confirmedPassword))throw new  RuntimeException("Veuillez confirmer vortre mot de passe");
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setNom(nom);
        appUser.setPrenom(prenom);
        appUser.setFonction(fonction);
        appUser.setActived(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        roles.forEach(un->{
            addRoleToUser(username,un);
        });
        //addRoleToUser(username,"SAISIE");
        //addMinistereToUser(username,"67");
        return appUser;

    }

//    @Override
//    public AppUs er updateUser(String username) {
////        AppUser user = appUserRepository.findByUsername(username);
////        if (user!=null) throw new RuntimeException("L'utilisateur existe déjà");
//        if (!Npassword.equals(NconfirmedPassword))throw new  RuntimeException("Veuillez confirmer vortre mot de passe");
//        AppUser appUser = new AppUser();
//        appUser.setUsername(username);
//        appUser.setActived(true);
//        appUser.setPassword(bCryptPasswordEncoder.encode(password));
//        appUserRepository.save(appUser);
//        addRoleToUser(username,"SAISIE");
//
//        return appUser;
//    }

    @Override
    public AppRole save(AppRole role) {

        return appRoleRepository.save(role);
    }

    @Override
    public List<AppRole>  getRole() {
        return appRoleRepository.findAll();
    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName((roleName));
        appUser.getRoles().add(appRole);

    }

    @Override
    public void addMinistereToUser(String username, String cd_ministere) {
        AppUser appUser = appUserRepository.findByUsername(username);
        //Ministere appMinistere = ministereRepository.findById(cd_ministere).orElse(null);
        System.out.println(appUser);
        //appUser.getMinisteres().add(appMinistere);
    }

    @Override
    public AppUser updateActivation(Boolean actived, String username) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("Utilisateur non trouvé !");
        appUserRepository.updateActivation(!user.isActived(),user.getUsername());
        return user;
    }
}
