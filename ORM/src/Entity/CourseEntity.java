package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "courses", catalog = "")
public class CourseEntity {
    private String courseId;
    private String subjectId;
    private Integer courseNum;
    private String title;
    private Integer numCredit;

    @Id
    @Column(name = "courseID")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "subjectID")
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "courseNum")
    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "numCredit")
    public Integer getNumCredit() {
        return numCredit;
    }

    public void setNumCredit(Integer numCredit) {
        this.numCredit = numCredit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return Objects.equals(courseId, that.courseId) &&
                Objects.equals(subjectId, that.subjectId) &&
                Objects.equals(courseNum, that.courseNum) &&
                Objects.equals(title, that.title) &&
                Objects.equals(numCredit, that.numCredit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, subjectId, courseNum, title, numCredit);
    }
}
