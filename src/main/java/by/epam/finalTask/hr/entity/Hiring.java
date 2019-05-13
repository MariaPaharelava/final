package by.epam.finalTask.hr.entity;


import by.epam.finalTask.hr.entity.enums.HiringStatus;

import java.sql.Date;
import java.util.Objects;

public class Hiring implements Indentifable{
    private Integer        hiringId;
    private Integer      hrId;
    private Integer      candidateId;
    private Integer      vacancyId;
    private Date         creationDate;
    private Double       offerEmount;
    private String       comment;
    private HiringStatus hiringStatus;

    public Hiring(int hiringId, int hrId, int candidateId, int vacancyId, Date creationDate, Double offerEmount, String comment, HiringStatus hiringStatus) {
        this.hiringId = hiringId;
        this.hrId = hrId;
        this.candidateId = candidateId;
        this.vacancyId = vacancyId;
        this.creationDate = creationDate;
        this.offerEmount = offerEmount;
        this.comment = comment;
        this.hiringStatus = hiringStatus;
    }
    public Hiring(int hrId, int candidateId, int vacancyId) {
        this.hrId = hrId;
        this.candidateId = candidateId;
        this.vacancyId = vacancyId;
    }

    public Hiring(int hrId, int candidateId, int vacancyId, Date creationDate, Double offerEmount, String comment, HiringStatus hiringStatus) {
        this.hrId = hrId;
        this.candidateId = candidateId;
        this.vacancyId = vacancyId;
        this.creationDate = creationDate;
        this.offerEmount = offerEmount;
        this.comment = comment;
        this.hiringStatus = hiringStatus;
    }

    public Hiring(){

    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public HiringStatus getHiringStatus() {
        return hiringStatus;
    }

    public void setHiringStatus(HiringStatus hiringStatus) {
        this.hiringStatus = hiringStatus;
    }

    public void setHiringStatus(String hiringStatus) {
        hiringStatus = hiringStatus.replace('-', '_');
        hiringStatus = hiringStatus.toUpperCase();
        switch (hiringStatus) {
            case "EMPLOYED":
                this.hiringStatus = HiringStatus.EMPLOYED;
                break;
            case "IN_ANTICIPATION":
                this.hiringStatus = HiringStatus.IN_ANTICIPATION;
                break;
            case "INITAL_CONTACT":
                this.hiringStatus = HiringStatus.INITAL_CONTACT;
                break;
            case "NOT_ACCEPTED":
                this.hiringStatus = HiringStatus.NOT_ACCEPTED;
                break;
            case "OFFER_MADE":
                this.hiringStatus = HiringStatus.OFFER_MADE;
                break;
            case "SCRENING_INTERVIEW":
                this.hiringStatus = HiringStatus.SCRENING_INTERVIEW;
                break;
            case "TECHNICAL_INTERVIEW":
                this.hiringStatus = HiringStatus.TECHNICAL_INTERVIEW;
                break;
        }
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

    public int getHiringId() {
        return hiringId;
    }

    public void setHiringId(int hiringId) {
        this.hiringId = hiringId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hiring hiring = (Hiring) o;
        return hiringId.equals(hiring.hiringId) &&
                hrId.equals(hiring.hrId) &&
                candidateId.equals(hiring.candidateId) &&
                vacancyId.equals(hiring.vacancyId) &&
                Objects.equals(creationDate, hiring.creationDate) &&
                Objects.equals(offerEmount, hiring.offerEmount) &&
                Objects.equals(comment, hiring.comment) &&
                hiringStatus == hiring.hiringStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hiringId, hrId, candidateId, vacancyId, creationDate, offerEmount, comment, hiringStatus);
    }

    @Override
    public String toString() {
        return "Hiring{" +
                "hiringId=" + hiringId +
                ", hrId=" + hrId +
                ", candidateId=" + candidateId +
                ", vacancyId=" + vacancyId +
                ", creationDate=" + creationDate +
                ", offerEmount=" + offerEmount +
                ", comment='" + comment + '\'' +
                ", hiringStatus=" + hiringStatus +
                '}';
    }

    @Override
    public Integer getID() {
        return hiringId;
    }
}
