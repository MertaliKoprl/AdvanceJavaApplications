package Entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "last_project", catalog = "")
public class StudentEntity {
    private String studentNo;
    private String majorLeaving;
    private int minorDegree;


    public StudentEntity(){


    }

    @Id
    @Column(name = "studentNo")
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @Basic
    @Column(name = "majorLeaving")
    public String getMajorLeaving() {
        return majorLeaving;
    }

    public void setMajorLeaving(String majorLeaving) {
        this.majorLeaving = majorLeaving;
    }

    @Basic
    @Column(name = "minorDegree")
    public int getMinorDegree() {
        return minorDegree;
    }

    public void setMinorDegree(int minorDegree) {
        this.minorDegree = minorDegree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return minorDegree == that.minorDegree &&
                Objects.equals(studentNo, that.studentNo) &&
                Objects.equals(majorLeaving, that.majorLeaving);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNo, majorLeaving, minorDegree);
    }


    @Override
    public String toString() {
        return ""+getStudentNo()+" , "+getMajorLeaving()+" , "+getMinorDegree();
    }
}
