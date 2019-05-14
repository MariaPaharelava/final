package by.epam.finalTask.hr.util;

import by.epam.finalTask.hr.entity.enums.HiringStatus;

public class HiringForShow {
    private String hrName;
    private String hrSurname;
    private String candidateName;
    private String candidateSurname;
    private String vacancyName;
    private Double offerEmount;
    private String comment;
    private HiringStatus hiringStatus;

    public HiringForShow(String hrName, String hrSurname, String candidateName,
                         String candidateSurname, String vacancyName, Double offerEmount,
                         String comment, HiringStatus hiringStatus) {
        this.hrName = hrName;
        this.hrSurname = hrSurname;
        this.candidateName = candidateName;
        this.candidateSurname = candidateSurname;
        this.vacancyName = vacancyName;
        this.offerEmount = offerEmount;
        this.comment = comment;
        this.hiringStatus = hiringStatus;
    }

    public HiringForShow(String hrName, String hrSurname, String candidateName,
                         String candidateSurname, String vacancyName, HiringStatus hiringStatus) {
        this.hrName = hrName;
        this.hrSurname = hrSurname;
        this.candidateName = candidateName;
        this.candidateSurname = candidateSurname;
        this.vacancyName = vacancyName;
        this.hiringStatus = hiringStatus;
    }

    public HiringForShow() {
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getHrSurname() {
        return hrSurname;
    }

    public void setHrSurname(String hrSurname) {
        this.hrSurname = hrSurname;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateSurname() {
        return candidateSurname;
    }

    public void setCandidateSurname(String candidateSurname) {
        this.candidateSurname = candidateSurname;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public Double getOfferEmount() {
        return offerEmount;
    }

    public void setOfferEmount(Double offerEmount) {
        this.offerEmount = offerEmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public HiringStatus getHiringStatus() {
        return hiringStatus;
    }

    public void setHiringStatus(HiringStatus hiringStatus) {
        this.hiringStatus = hiringStatus;
    }

}
