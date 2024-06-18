package org.sid.services;

import org.sid.entites.AppRole;
import org.sid.entites.AppUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface AccountService {

    public AppUser saveUser(String username, String nom, String prenom, String fonction, String password,
            String confirmedPassword, Collection<String> roles);

    void updateUser(String username, String password, Integer id);

    public AppUser getUser(Integer id);

    public List<AppUser> getAllUsers();

    public List<AppRole> getAllRoles();

    // public AppUser updateUser(String username,String Apassword, String Npassword,
    // String NconfirmedPassword);
    public AppRole save(AppRole role);

    public List<AppRole> getRole();

    public AppUser loadUserByUserName(String username);

    public void addRoleToUser(String username, String roleName);

    public void addMinistereToUser(String username, String cd_ministere);

    AppUser updateActivation(Boolean actived, String username);

}
