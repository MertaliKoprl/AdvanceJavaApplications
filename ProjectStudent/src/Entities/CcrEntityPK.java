package Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CcrEntityPK implements Serializable {
    private String studentNo;
    private String yearTaken;
    private String termTaken;
    private String slotNo;

    @Column(name = "studentNo")
    @Id
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @Column(name = "yearTaken")
    @Id
    public String getYearTaken() {
        return yearTaken;
    }

    public void setYearTaken(String yearTaken) {
        this.yearTaken = yearTaken;
    }

    @Column(name = "termTaken")
    @Id
    public String getTermTaken() {
        return termTaken;
    }

    public void setTermTaken(String termTaken) {
        this.termTaken = termTaken;
    }

    @Column(name = "slotNo")
    @Id
    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CcrEntityPK that = (CcrEntityPK) o;
        return Objects.equals(studentNo, that.studentNo) &&
                Objects.equals(yearTaken, that.yearTaken) &&
                Objects.equals(termTaken, that.termTaken) &&
                Objects.equals(slotNo, that.slotNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNo, yearTaken, termTaken, slotNo);
    }
}
