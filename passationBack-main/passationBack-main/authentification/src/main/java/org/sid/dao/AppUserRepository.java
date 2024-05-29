package org.sid.dao;

import org.sid.entites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public AppUser findByUsername (String username);

    @Query(value="SELECT *FROM APPUSER where id=?1",nativeQuery=true)
    AppUser getUser(Integer id);

    @Modifying
    @Transactional
    @Query(value="UPDATE APPUSER set username=?1,password=?2 WHERE id=?3",nativeQuery=true)
    void updateUser(String username,String password, Integer id);

    @Modifying
    @Transactional
    @Query(value="UPDATE APPUSER set actived=?1 WHERE username=?2",nativeQuery=true)
    void updateActivation(Boolean actived,String username);
}
