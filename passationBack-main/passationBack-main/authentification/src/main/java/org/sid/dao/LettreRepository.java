package org.sid.dao;


import org.sid.entites.Dossier;
import org.sid.entites.Lettre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LettreRepository extends JpaRepository<Lettre,Integer> {


}
