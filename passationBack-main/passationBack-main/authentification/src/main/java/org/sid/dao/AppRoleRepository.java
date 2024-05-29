package org.sid.dao;

import org.sid.entites.AppRole;
import org.sid.entites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {



    public AppRole findByRoleName(String roleName);
}
