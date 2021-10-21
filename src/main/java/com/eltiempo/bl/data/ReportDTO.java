package com.eltiempo.bl.data;

import com.eltiempo.bl.utils.states.CaseExecutionState;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class ReportDTO implements Serializable, Comparator<ReportDTO> {

    private static final long serialVersionUID = 1L;
    private long reportID;
    private CaseDTO execCase;
    private CaseExecutionState state;
    private Date dExecuted;
    private long timeTaken;

    public ReportDTO(long reportID, CaseDTO execCase, CaseExecutionState state, Date dExecuted, long timeTaken) {
        this.reportID = reportID;
        this.execCase = execCase;
        this.state = state;
        this.dExecuted = dExecuted;
        this.timeTaken = timeTaken;
    }

    public long getReportID() {
        return reportID;
    }

    public CaseDTO getExecCase() {
        return execCase;
    }

    public void setExecCase(CaseDTO execCase) {
        this.execCase = execCase;
    }

    public CaseExecutionState getState() {
        return state;
    }

    public void setState(CaseExecutionState state) {
        this.state = state;
    }

    public Date getdExecuted() {
        return dExecuted;
    }

    public void setdExecuted(Date dExecuted) {
        this.dExecuted = dExecuted;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    @Override
    public int compare(ReportDTO o1, ReportDTO o2) {
        return o1.getdExecuted().compareTo(o2.getdExecuted());
    }
}
