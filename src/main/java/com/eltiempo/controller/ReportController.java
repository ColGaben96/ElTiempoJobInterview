package com.eltiempo.controller;

import com.eltiempo.bl.data.CaseDTO;
import com.eltiempo.bl.data.ReportDTO;
import com.eltiempo.bl.entities.ReportDAO;
import com.eltiempo.bl.utils.df.DateProcessor;
import com.eltiempo.bl.utils.exception.AlreadyExistsException;
import com.eltiempo.bl.utils.exception.NotFoundException;
import com.eltiempo.bl.utils.io.FileProcessor;
import com.eltiempo.bl.utils.io.LogMaker;
import com.eltiempo.bl.utils.io.ScreenshotMaker;
import com.eltiempo.bl.utils.states.CaseExecutionState;
import org.apache.commons.io.FileExistsException;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReportController {

    private ReportDAO reports = new ReportDAO();
    private String dExec = new DateProcessor().parse2BasicDateTime(Timestamp.valueOf(LocalDateTime.now()));

    public void createReport(long reportID, CaseDTO linkedCase, CaseExecutionState state) {
        var fp = new FileProcessor();
        try {
            reports.createReport(reportID, linkedCase, state, Timestamp.valueOf(LocalDateTime.now()), 0);
            fp.writeLog(new LogMaker().reportUpdateState(reports.searchExact(reportID)), dExec);
        } catch (NotFoundException | IOException not) {
            try {
                fp.writeLog(new LogMaker().caseNotFound(), dExec);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (AlreadyExistsException ex) {
            try {
                fp.writeLog(new LogMaker().reportFailedCreation(), dExec);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteReport(long reportID) {
        try {
            reports.deleteReport(reports.searchExact(reportID));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateReport(long reportID, CaseExecutionState state, long timeTaken) {
        var fp = new FileProcessor();
        try {
            reports.updateState(reportID, state, timeTaken);
            fp.writeLog(new LogMaker().reportUpdateState(reports.searchExact(reportID)), dExec);
        } catch (NotFoundException | IOException not) {
            try {
                fp.writeLog(new LogMaker().caseNotFound(), dExec);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void captureScreen(long reportID, File screen) {
        var sm = new ScreenshotMaker();
        try {
            sm.capture(reports.searchExact(reportID), screen);
        } catch (FileExistsException e) {
            try {
                new FileProcessor().writeLog(new DateProcessor().parse2String(Timestamp.valueOf(LocalDateTime.now()))+": ERROR: Was trying to capture multiple screens at the same time for the case "+reports.searchExact(reportID).getExecCase().getCaseID()+". Testing will continue", dExec);
            } catch (IOException | NotFoundException ex) {
                ex.printStackTrace();
            }
        }
        catch (IOException | NotFoundException io) {
            io.printStackTrace();
        }
    }
}
