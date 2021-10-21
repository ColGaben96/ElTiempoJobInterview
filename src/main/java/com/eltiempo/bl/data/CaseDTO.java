package com.eltiempo.bl.data;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author Gabriel Blanco
 * @version 1.0
 * Description: Class to model the case and show on platform (if there is someone)
 */
public class CaseDTO implements Serializable, Comparator<CaseDTO> {
    /**
     * Attribute to serial the case
     */
    private static final long serialVersionUID = 1L;
    /**
     * Attribute to identify the case
     */
    private Long caseID;
    /**
     * Attribute to name the case
     */
    private String caseName;
    /**
     * Attribute to set a description to the case
     */
    private String caseDescription;
    /**
     * Attribute to set a positive or negative case
     */
    private boolean hasToFail;

    /**
     * @author Gabriel Blanco
     * @param caseID
     * @param caseName
     * @param caseDescription
     * @param hasToFail
     *
     * Constructor method to define the class attributes
     */
    public CaseDTO(Long caseID, String caseName, String caseDescription, boolean hasToFail) {
        this.caseID = caseID;
        this.caseName = caseName;
        this.caseDescription = caseDescription;
        this.hasToFail = hasToFail;
    }

    public Long getCaseID() {
        return caseID;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public boolean isHasToFail() {
        return hasToFail;
    }

    public void setHasToFail(boolean hasToFail) {
        this.hasToFail = hasToFail;
    }

    /**
     * Method to sort the cases in an array.
     * @param o1
     * @param o2
     * @return
     */

    @Override
    public int compare(CaseDTO o1, CaseDTO o2) {
        return o1.getCaseID().compareTo(o2.getCaseID());
    }
}
