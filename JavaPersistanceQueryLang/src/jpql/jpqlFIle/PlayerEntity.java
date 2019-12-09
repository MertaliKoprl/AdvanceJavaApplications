package jpql.jpqlFIle;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "player", schema = "jpql", catalog = "")
public class PlayerEntity {
    private String id;
    private String pname;
    private String position;
    private Double salary;
    private Collection<TeamplayerEntity> teamplayersById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pname")
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "salary")
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pname, that.pname) &&
                Objects.equals(position, that.position) &&
                Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pname, position, salary);
    }

    @OneToMany(mappedBy = "playerByPlayerid")
    public Collection<TeamplayerEntity> getTeamplayersById() {
        return teamplayersById;
    }

    public void setTeamplayersById(Collection<TeamplayerEntity> teamplayersById) {
        this.teamplayersById = teamplayersById;
    }
}
