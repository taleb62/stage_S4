package org.sid.dao;

import org.sid.entites.Verdict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerdictRepository extends JpaRepository<Verdict, Integer> {
}