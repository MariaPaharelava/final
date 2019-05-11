package by.epam.finalTask.hr.entity;

import java.util.Objects;

public class Vacancy implements Indentifable {
    private int vacancyID;
    private String vacancyDescrintion;
    private String vacancyPosition;
    private int userId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Vacancy(int vacancyID, String vacancyDescrintion, String vacancyPosition, int userId) {
        this.vacancyID = vacancyID;
        this.vacancyDescrintion = vacancyDescrintion;
        this.vacancyPosition = vacancyPosition;
        this.userId = userId;
    }

    public Vacancy(String vacancyDescrintion, String vacancyPosition, int userId) {
        this.vacancyDescrintion = vacancyDescrintion;
        this.vacancyPosition = vacancyPosition;
        this.userId = userId;
    }

    public Vacancy() {
    }

    public String getVacancyDescrintion() {
        return vacancyDescrintion;
    }

    public void setVacancyDescrintion(String vacancyDescrintion) {
        this.vacancyDescrintion = vacancyDescrintion;
    }

    public String getVacancyPosition() {
        return vacancyPosition;
    }

    public void setVacancyPosition(String vacancyPosition) {
        this.vacancyPosition = vacancyPosition;
    }

    public void setVacancyID(int vacancyID) {
        this.vacancyID = vacancyID;
    }

    @Override
    public Integer getID() {
        return vacancyID;
    }
}
