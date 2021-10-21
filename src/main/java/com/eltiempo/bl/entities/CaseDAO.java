package com.eltiempo.bl.entities;

import com.eltiempo.bl.data.CaseDTO;
import com.eltiempo.bl.utils.exception.AlreadyExistsException;
import com.eltiempo.bl.utils.exception.NotFoundException;

import java.util.ArrayList;

/**
 * @author Gabriel Blanco
 * @version 1.0
 */
public class CaseDAO {

    private ArrayList<CaseDTO> cases = new ArrayList<>();

    public int createCase(long caseID, String caseName, String caseDescription, boolean hasToFail) throws AlreadyExistsException {
        for (CaseDTO found : cases) {
            if (found.getCaseID() == caseID) {
                throw new AlreadyExistsException();
            }
        }
        var newCase = new CaseDTO(caseID, caseName, caseDescription, hasToFail);
        cases.add(newCase);
        return 0;
    }

    public CaseDTO searchExact(long caseID) throws NotFoundException {
        for (CaseDTO found : cases) {
            if (found.getCaseID() == caseID) {
                return found;
            }
        }
        throw new NotFoundException();
    }

    public ArrayList<CaseDTO> searchByName(String caseName) {
        var result = new ArrayList<CaseDTO>();
        for (CaseDTO found : cases) {
            if (found.getCaseName().equalsIgnoreCase(caseName)) {
                result.add(found);
                result.sort(found);
            }
        }
        return result;
    }

    public ArrayList<CaseDTO> searchByDescription(String caseDescription) {
        var result = new ArrayList<CaseDTO>();
        for (CaseDTO found : cases) {
            if (found.getCaseDescription().equalsIgnoreCase(caseDescription)) {
                result.add(found);
                result.sort(found);
            }
        }
        return result;
    }

    public ArrayList<CaseDTO> searchByFailure(boolean hasToFail) {
        var result = new ArrayList<CaseDTO>();
        for (CaseDTO found : cases) {
            if (found.isHasToFail() == hasToFail) {
                result.add(found);
                result.sort(found);
            }
        }
        return result;
    }

    public int updateCase(long caseID, String caseName, String caseDescription, boolean hasToFail) {
        var updatedCase = new CaseDTO(caseID, caseName, caseDescription, hasToFail);
        cases.set(Integer.parseInt(String.valueOf(caseID)), updatedCase);
        return 0;
    }

    public int deleteCase(CaseDTO case2Del) {
        cases.remove(case2Del);
        return 0;
    }
}
