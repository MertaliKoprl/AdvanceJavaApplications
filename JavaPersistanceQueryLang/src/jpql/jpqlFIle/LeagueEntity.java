package jpql.jpqlFIle;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "league", schema = "jpql", catalog = "")
public class LeagueEntity {
    private String id;
    private String dtype;
    private String lname;
    private String sport;
    private Collection<TeamEntity> teamsById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dtype")
    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    @Basic
    @Column(name = "lname")
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Basic
    @Column(name = "sport")
    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueEntity that = (LeagueEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dtype, that.dtype) &&
                Objects.equals(lname, that.lname) &&
                Objects.equals(sport, that.sport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtype, lname, sport);
    }

    @OneToMany(mappedBy = "leagueByLid")
    public Collection<TeamEntity> getTeamsById() {
        return teamsById;
    }

    public void setTeamsById(Collection<TeamEntity> teamsById) {
        this.teamsById = teamsById;
    }
}
