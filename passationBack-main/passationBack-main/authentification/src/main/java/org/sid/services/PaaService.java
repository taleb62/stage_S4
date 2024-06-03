package org.sid.services;

import org.sid.entites.plan_anuell_achat;

import java.util.List;

public interface PaaService {

    public List<plan_anuell_achat> getAllPaa();
    plan_anuell_achat updatePaa(Integer id,String origine,String destinataire);
}
