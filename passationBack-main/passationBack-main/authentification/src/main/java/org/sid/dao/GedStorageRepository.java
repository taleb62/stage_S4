package org.sid.dao;

import org.sid.entites.AppUser;
import org.sid.entites.GedStorage;
import org.sid.entites.plan_anuell_achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GedStorageRepository extends JpaRepository<GedStorage, Integer> {

    @Query(value="select g from GedStorage g where id=1")
    GedStorage getPath();

}
