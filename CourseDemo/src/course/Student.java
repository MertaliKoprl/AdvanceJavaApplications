package course;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author emine
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
        , @NamedQuery(name = "Student.findByStudentNo", query = "SELECT s FROM Student s WHERE s.studentNo = :studentNo")
        , @NamedQuery(name = "Student.findByStudentName", query = "SELECT s FROM Student s WHERE s.studentName = :studentName")
        , @NamedQuery(name = "Student.findByDept", query = "SELECT s FROM Student s WHERE s.dept = :dept")})
public class Student implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "studentNo")
    private String studentNo;
    @Basic(optional = false)
    @Column(name = "studentName")
    private String studentName;
    @Basic(optional = false)
    @Column(name = "dept")
    private String dept;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Enrollment> enrollmentList;

    public Student() {
    }

    public Student(String studentNo) {
        this.studentNo = studentNo;
    }

    public Student(String studentNo, String studentName, String dept) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.dept = dept;
    }

    public Student(String studentNo, String studentName) {
        this.studentNo = studentNo;
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @XmlTransient
    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentNo != null ? studentNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentNo == null && other.studentNo != null) || (this.studentNo != null && !this.studentNo.equals(other.studentNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return studentNo + " ";
    }

}
