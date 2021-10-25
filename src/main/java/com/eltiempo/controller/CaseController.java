package com.eltiempo.controller;

import com.eltiempo.bl.data.CaseDTO;
import com.eltiempo.bl.entities.CaseDAO;
import com.eltiempo.bl.utils.df.DateProcessor;
import com.eltiempo.bl.utils.exception.AlreadyExistsException;
import com.eltiempo.bl.utils.exception.NotFoundException;
import com.eltiempo.bl.utils.io.FileProcessor;
import com.eltiempo.bl.utils.io.LogMaker;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CaseController {

    private CaseDAO cases = new CaseDAO();
    private String dExec = new DateProcessor().parse2BasicDateTime(Timestamp.valueOf(LocalDateTime.now()));

    public void createCase(long caseID, String caseName, String caseDescription, boolean hasToFail) {
        var fp = new FileProcessor();
        try {
            cases.createCase(caseID, caseName, caseDescription, hasToFail);
            fp.writeLog(new LogMaker().caseCreation(cases.searchExact(caseID)), dExec);
        } catch (IOException | NotFoundException io) {
            io.printStackTrace();
        }
        catch (AlreadyExistsException e) {
            try {
                fp.writeLog(new LogMaker().caseFailedCreation(), dExec);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteCase(long caseID) {
        var fp = new FileProcessor();
        try {
            fp.writeLog(new LogMaker().caseDelete(cases.searchExact(caseID)), dExec);
            cases.deleteCase(cases.searchExact(caseID));
        } catch (IOException | NotFoundException io) {
            io.printStackTrace();
        }
    }

    public CaseDTO getCase(long caseID) throws NotFoundException {
        return cases.searchExact(caseID);
    }
}
