package com.eltiempo.bl.entities;

import com.eltiempo.bl.data.CaseDTO;
import com.eltiempo.bl.data.ReportDTO;
import com.eltiempo.bl.utils.exception.AlreadyExistsException;
import com.eltiempo.bl.utils.exception.NotFoundException;
import com.eltiempo.bl.utils.states.CaseExecutionState;

import java.util.ArrayList;
import java.util.Date;

public class ReportDAO {

    private ArrayList<ReportDTO> reports = new ArrayList<>();

    public int createReport(long reportID, CaseDTO execCase, CaseExecutionState state, Date dExecuted, long timeTaken) throws AlreadyExistsException {
        var newReport = new ReportDTO(reportID, execCase, state, dExecuted, timeTaken);
        for (ReportDTO found : reports) {
            if (found.getReportID() == reportID) {
                throw new AlreadyExistsException();
            }
        }
        reports.add(newReport);
        return 0;
    }

    public ReportDTO searchExact(long reportID) throws NotFoundException {
        for(ReportDTO found : reports) {
            if (found.getReportID() == reportID) {
                return found;
            }
        }
        throw new NotFoundException();
    }

    public ArrayList<ReportDTO> searchByCase(CaseDTO execCase) {
        var result = new ArrayList<ReportDTO>();
        for (ReportDTO found : reports) {
            if (found.getExecCase() == execCase) {
                result.add(found);
                result.sort(found);
            }
        }
        return result;
    }

    public ArrayList<ReportDTO> searchByDate(Date dExecuted) {
        var result = new ArrayList<ReportDTO>();
        for (ReportDTO found : reports) {
            if (found.getdExecuted().equals(dExecuted)) {
                result.add(found);
                result.sort(found);
            }
        }
        return result;
    }

    public ArrayList<ReportDTO> searchByState(CaseExecutionState state) {
        var result = new ArrayList<ReportDTO>();
        for (ReportDTO found : reports) {
            if (found.getState() == state) {
                result.add(found);
                result.sort(found);
            }
        }
        return result;
    }

    public int updateState(long reportID, CaseExecutionState state, long timeTaken) throws NotFoundException {
        var report = searchExact(reportID);
        report.setState(state);
        report.setTimeTaken(timeTaken);
        return 0;
    }

    public int deleteReport(ReportDTO report) {
        reports.remove(report);
        return 0;
    }
}
