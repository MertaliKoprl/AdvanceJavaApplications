package Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ccr", schema = "last_project", catalog = "")
@IdClass(CcrEntityPK.class)
public class CcrEntity {
    private String studentNo;
    private String yearTaken;
    private String termTaken;
    private String slotNo;
    private String courseCode;
    private String grade;
    private String credit;
    private StudentEntity studentByStudentNo;

    public CcrEntity(){


    }

    @Id
    @Column(name = "studentNo")
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @Id
    @Column(name = "yearTaken")
    public String getYearTaken() {
        return yearTaken;
    }

    public void setYearTaken(String yearTaken) {
        this.yearTaken = yearTaken;
    }

    @Id
    @Column(name = "termTaken")
    public String getTermTaken() {
        return termTaken;
    }

    public void setTermTaken(String termTaken) {
        this.termTaken = termTaken;
    }

    @Id
    @Column(name = "slotNo")
    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    @Basic
    @Column(name = "courseCode")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "credit")
    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CcrEntity ccrEntity = (CcrEntity) o;
        return Objects.equals(studentNo, ccrEntity.studentNo) &&
                Objects.equals(yearTaken, ccrEntity.yearTaken) &&
                Objects.equals(termTaken, ccrEntity.termTaken) &&
                Objects.equals(slotNo, ccrEntity.slotNo) &&
                Objects.equals(courseCode, ccrEntity.courseCode) &&
                Objects.equals(grade, ccrEntity.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNo, yearTaken, termTaken, slotNo, courseCode, grade);
    }

    @ManyToOne
    @JoinColumn(name = "studentNo", referencedColumnName = "studentNo", nullable = false,insertable = false,updatable = false)
    public StudentEntity getStudentByStudentNo() {
        return studentByStudentNo;
    }

    public void setStudentByStudentNo(StudentEntity studentByStudentNo) {
        this.studentByStudentNo = studentByStudentNo;
    }

    @Override
    public String toString() {
        return " "+getStudentNo()+" , "+getYearTaken()+" , "+getTermTaken()+" , "+getCourseCode()+" , "+getSlotNo()+" , "+getGrade()+" , "+getCredit();
    }
}
