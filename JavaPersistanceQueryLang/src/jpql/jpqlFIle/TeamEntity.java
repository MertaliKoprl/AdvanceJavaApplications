package jpql.jpqlFIle;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "team", schema = "jpql", catalog = "")
public class TeamEntity {
    private String id;
    private String city;
    private String tname;
    private String lid;
    private LeagueEntity leagueByLid;
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
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "tname")
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Basic
    @Column(name = "lid")
    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamEntity that = (TeamEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(city, that.city) &&
                Objects.equals(tname, that.tname) &&
                Objects.equals(lid, that.lid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, tname, lid);
    }

    @ManyToOne
    @JoinColumn(name = "lid", referencedColumnName = "id")
    public LeagueEntity getLeagueByLid() {
        return leagueByLid;
    }

    public void setLeagueByLid(LeagueEntity leagueByLid) {
        this.leagueByLid = leagueByLid;
    }

    @OneToMany(mappedBy = "teamByTeamid")
    public Collection<TeamplayerEntity> getTeamplayersById() {
        return teamplayersById;
    }

    public void setTeamplayersById(Collection<TeamplayerEntity> teamplayersById) {
        this.teamplayersById = teamplayersById;
    }
}
