package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "enrollment", schema = "courses", catalog = "")
@IdClass(EnrollmentEntityPK.class)
public class EnrollmentEntity {
    private String ssn;
    private String courseId;
    private Date dateReg;
    private String grade;
    private CourseEntity courseByCourseId;

    @Id
    @Column(name = "ssn")
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Id
    @Column(name = "courseID")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "dateReg")
    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentEntity that = (EnrollmentEntity) o;
        return Objects.equals(ssn, that.ssn) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(dateReg, that.dateReg) &&
                Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssn, courseId, dateReg, grade);
    }

    @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "courseID", nullable = false)
    public CourseEntity getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(CourseEntity courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }
}
