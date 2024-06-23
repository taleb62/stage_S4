package org.sid.dao;

import org.sid.entites.DTO.PaaProcedure;
import org.sid.entites.Etablissement;
import org.sid.entites.plan_anuell_achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface planRepository extends JpaRepository<plan_anuell_achat, Integer> {

    List<plan_anuell_achat> findByEtablissementId(Integer etablissementId);

    @Query(value = "select p from plan_anuell_achat p where id=?1")
    plan_anuell_achat getPaa(Integer id);

    // @Query(value = "select * from plan_anuell_achat p inner join mod_passation m
    // on p.fkid_mod_passation=m.id " +
    // "inner join type_marche t on p.fkid_type_marche=t.id inner join files f on
    // p.id = f.id_elm where f.fk_id_tbl = 1;", nativeQuery = true)
    // List<Map<String, Object>> getPaas();

    // @Query(value="select
    // p.id,p.origine,p.destinataire,p.objet_depense,p.date_previ_lancement,p.date_previ_attribution,p.inpu_budgetaire,p.mnt_estimatif,m.mode_passation
    // from plan_anuell_achat p INNER JOIN mod_passation m ON p.id=m.id where
    // p.id=?1",nativeQuery = true)
    // PaaProcedure getPaaReport(Integer id);

    @Query(value = "select new org.sid.entites.DTO.PaaProcedure(p.id,p.origine,p.destinataire,p.objetDepense,p.datePreviLancement,p.datePreviAttribution,p.inpuBudgetaire,p.mntEstimatif,m.modePassation) from plan_anuell_achat p inner join mod_passation m ON p.id=m.id where p.id=?1")
    List<PaaProcedure> getPaaReport(Integer id);

    // @Query(value="select new
    // org.sid.entites.DTO.PaaProcedure(p.id,p.origine,p.destinataire,p.objetDepense,p.datePreviLancement,"
    // +
    // "p.datePreviAttribution,p.inpuBudgetaire,p.mntEstimatif,f.name,f.fileNameOnDisc,f.fkIdTbl,m.modePassation)
    // " +
    // "from plan_anuell_achat p inner join mod_passation m ON p.id=m.id inner join
    // FileDB f on p.id=f.idElm and f.fkIdTbl=1")
    // List<PaaProcedure> getAllPaas();

    @Modifying
    @Transactional
    @Query(value = "update plan_anuell_achat set origine=?1,destinataire=?2,enprocedure=?3 where id=?4")
    void updatePaa(String origine, String destinataire, Boolean enprocedure, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update plan_anuell_achat set dosssierCree=?1 where id=?2")
    void updatePaaDossier(Boolean dosssierCree, Integer id);


}
