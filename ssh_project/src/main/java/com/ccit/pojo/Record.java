package com.ccit.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "t_record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String initlog;
    private String mainlog;
    private String relatelog;
    private String positivesign;
    private String checkresult;
    private String treatproject;
    private String dutydoctor;
    private Timestamp creattime;
    private String nexttime;
    @ManyToOne
    @JoinColumn(name = "fileid")
    private File file;
    @ManyToOne
    @JoinColumn(name = "illid")
    private Ill ill;
    @ManyToOne
    @JoinColumn(name = "officeid")
    private Office office;
    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patient;

    public Timestamp getCreattime() {
        return creattime;
    }

    public void setCreattime(Timestamp creattime) {
        this.creattime = creattime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainlog() {
        return mainlog;
    }

    public void setMainlog(String mainlog) {
        this.mainlog = mainlog;
    }

    public String getRelatelog() {
        return relatelog;
    }

    public void setRelatelog(String relatelog) {
        this.relatelog = relatelog;
    }

    public String getPositivesign() {
        return positivesign;
    }

    public void setPositivesign(String positivesign) {
        this.positivesign = positivesign;
    }

    public String getCheckresult() {
        return checkresult;
    }

    public void setCheckresult(String checkresult) {
        this.checkresult = checkresult;
    }

    public String getTreatproject() {
        return treatproject;
    }

    public void setTreatproject(String treatproject) {
        this.treatproject = treatproject;
    }

    public String getDutydoctor() {
        return dutydoctor;
    }

    public void setDutydoctor(String dutydoctor) {
        this.dutydoctor = dutydoctor;
    }

    public String getNexttime() {
        return nexttime;
    }

    public void setNexttime(String nexttime) {
        this.nexttime = nexttime;
    }

    public Ill getIll() {
        return ill;
    }

    public void setIll(Ill ill) {
        this.ill = ill;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getInitlog() {
        return initlog;
    }

    public void setInitlog(String initlog) {
        this.initlog = initlog;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", initlog='" + initlog + '\'' +
                ", mainlog='" + mainlog + '\'' +
                ", relatelog='" + relatelog + '\'' +
                ", positivesign='" + positivesign + '\'' +
                ", checkresult='" + checkresult + '\'' +
                ", treatproject='" + treatproject + '\'' +
                ", dutydoctor='" + dutydoctor + '\'' +
                ", creattime=" + creattime +
                ", nexttime='" + nexttime + '\'' +
                ", file=" + file +
                ", ill=" + ill +
                ", office=" + office +
                ", patient=" + patient +
                '}';
    }
}
