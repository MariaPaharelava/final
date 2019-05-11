package by.epam.finalTask.hr.entity;

import by.epam.finalTask.hr.entity.enums.InterviewType;

import java.util.Date;
import java.util.Objects;

public class Interview implements Indentifable {
    private int interviewID;
    private int hiringId;
    private Date interviewDate;
    private InterviewType interviewType;
    private String result;
    private String comment;

    public Interview(int hiringId, Date interviewDate, String interviewType, String result, String comment) {
        this.hiringId = hiringId;
        this.interviewDate = interviewDate;
        setInterviewType(interviewType);
        this.result = result;
        this.comment = comment;
    }

    public Interview() {
    }

    public int getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(int interviewID) {
        this.interviewID = interviewID;
    }


    public int getHiringId() {
        return hiringId;
    }

    public void setHiringId(int hiringId) {
        this.hiringId = hiringId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interview interview = (Interview) o;
        return interviewID == interview.interviewID &&
                hiringId == interview.hiringId &&
                Objects.equals(interviewDate, interview.interviewDate) &&
                interviewType == interview.interviewType &&
                Objects.equals(result, interview.result) &&
                Objects.equals(comment, interview.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewID, hiringId, interviewDate, interviewType, result, comment);
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewID=" + interviewID +
                ", hiringId=" + hiringId +
                ", interviewDate=" + interviewDate +
                ", interviewType=" + interviewType +
                ", result='" + result + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public InterviewType getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(InterviewType interviewType) {
        this.interviewType = interviewType;
    }

    public void setInterviewType(String interviewType) {
        interviewType = interviewType.replace('-', '_');
        interviewType = interviewType.toUpperCase();
        switch (interviewType) {
            case "PHONE_INTERVIEW":
                this.interviewType = InterviewType.PHONE_INTERVIEW;
                break;
            case "FACE_TO_FACE_INTERVIEW":
                this.interviewType = InterviewType.FACE_TO_FACE_INTERVIEW;
                break;
        }
    }

    @Override
    public Integer getID() {
        return interviewID;
    }
}
