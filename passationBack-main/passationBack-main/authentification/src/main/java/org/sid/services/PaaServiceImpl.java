package org.sid.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.io.FilenameUtils;
import org.sid.dao.planRepository;
import org.sid.entites.AppUser;
import org.sid.entites.plan_anuell_achat;
import org.sid.entites.DTO.PaaFormProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class PaaServiceImpl implements PaaService{
    @Autowired
   private planRepository repo ;

    @Override
    public List<plan_anuell_achat> getAllPaa() {
       return repo.findAll();
       // return  repo.getAllPaas();
    }

    public ArrayNode getListPaa() {

        List<Map<String, Object>> paas =repo.getPaas();
        ArrayNode res = new ObjectMapper().createArrayNode();

        paas.forEach(paa -> {
                    res.add(new ObjectMapper().createObjectNode()
                            .put("id", paa.get("id").toString())
                            .put("date_cration_procedure", paa.get("date_cration_procedure").toString())
                            .put("date_previ_attribution", paa.get("date_previ_attribution").toString())
                            .put("date_previ_lancement", paa.get("date_previ_lancement").toString())
                            .put("destinataire", paa.get("destinataire").toString())
                    );
                }
        );
        System.out.println(res);
        return res;
    }

    @Override
    public plan_anuell_achat updatePaa(Integer id,String origine, String destinataire) {
          plan_anuell_achat paa = repo.getPaa(id);
        if (paa == null) throw new RuntimeException("le PAA non trouvé !");
        repo.updatePaa(origine,destinataire,!paa.isEnprocedure(),id);
        plan_anuell_achat paa1 = repo.getPaa(id);
        return paa1;
    }
 @Override
    public plan_anuell_achat addPaa(PaaFormProcedure data) {
        plan_anuell_achat newPaa = new plan_anuell_achat();
        newPaa.setObjetDepense(data.getObjetDepense());
        newPaa.setInpuBudgetaire(data.getInpuBudgetaire());
        newPaa.setFkidTypeMarche(data.getFkidTypeMarche());
        newPaa.setFkidModPassation(data.getFkidModPassation());
        newPaa.setMntEstimatif(data.getMntEstimatif());
        newPaa.setDatePreviLancement(data.getDatePreviLancement());
        newPaa.setDatePreviAttribution(data.getDatePreviAttribution());
        newPaa.setDateCrationProcedure(data.getDateCrationProcedure());
        newPaa.setOrigine(data.getOrigine());
        newPaa.setDestinataire(data.getDestinataire());
        newPaa.setEnprocedure(false);
        newPaa.setDosssierCree(false);
        return repo.save(newPaa);
    }
}
