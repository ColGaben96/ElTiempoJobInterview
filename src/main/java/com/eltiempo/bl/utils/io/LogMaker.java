package com.eltiempo.bl.utils.io;

import com.eltiempo.bl.data.CaseDTO;
import com.eltiempo.bl.data.ReportDTO;
import com.eltiempo.bl.utils.df.DateProcessor;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class LogMaker {
    public String reportUpdateState(ReportDTO reportDTO) {
        return new DateProcessor().parse2String(Timestamp.valueOf(LocalDateTime.now()))
                + ": Updated case "+reportDTO.getExecCase().getCaseID()
                + " state to "
                + reportDTO.getState()
                + " at "
                + reportDTO.getTimeTaken()
                + " seconds of testing";
    }

    public String caseCreation(CaseDTO caseDTO) {
        return new DateProcessor().parse2String(Timestamp.valueOf(LocalDateTime.now()))+": Created the test "+caseDTO.getCaseID()+": "+caseDTO.getCaseName();
    }

    public String caseFailedCreation() {
        return new DateProcessor().parse2String(Timestamp.valueOf(LocalDateTime.now()))+": Failed on creating this case because already exists";
    }

    public String reportFailedCreation() {
        return new DateProcessor().parse2String(Timestamp.valueOf(LocalDateTime.now()))+": Failed on creating this report because already exists";
    }

    public String caseNotFound() {
        return new DateProcessor().parse2String(Timestamp.valueOf(LocalDateTime.now()))+": Failed on searching this case because is not found";
    }
}
