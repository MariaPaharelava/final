package by.epam.finalTask.hr.util;

import by.epam.finalTask.hr.entity.enums.HiringStatus;

import java.sql.Date;

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
}
