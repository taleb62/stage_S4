package org.sid.dao;

import org.sid.entites.Contractant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContractantRepository extends JpaRepository<Contractant, Long> {
}
