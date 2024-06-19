package org.sid.services;

import org.sid.entites.plan_anuell_achat;
import org.sid.entites.DTO.PaaFormProcedure;

import java.util.List;

public interface PaaService {

    public List<plan_anuell_achat> getAllPaa();
    
    plan_anuell_achat updatePaa(Integer id,String origine,String destinataire);

    public plan_anuell_achat addPaa(PaaFormProcedure data);
}
