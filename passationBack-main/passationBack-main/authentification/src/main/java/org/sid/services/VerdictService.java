package org.sid.services;

import org.sid.entites.Verdict;
import java.util.List;

public interface VerdictService {
    List<Verdict> getAllVerdicts();
    Verdict getVerdictById(Integer id);
    Verdict createVerdict(Verdict verdict);
    Verdict updateVerdict(Integer id, Verdict verdict);
    void deleteVerdict(Integer id);
}
