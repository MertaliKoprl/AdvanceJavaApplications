package jpql.jpqlFIle;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teamplayer", schema = "jpql", catalog = "")
@IdClass(TeamplayerEntityPK.class)
public class TeamplayerEntity {
    private String teamid;
    private String playerid;
    private TeamEntity teamByTeamid;
    private PlayerEntity playerByPlayerid;

    @Id
    @Column(name = "teamid")
    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    @Id
    @Column(name = "playerid")
    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamplayerEntity that = (TeamplayerEntity) o;
        return Objects.equals(teamid, that.teamid) &&
                Objects.equals(playerid, that.playerid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamid, playerid);
    }

    @ManyToOne
    @JoinColumn(name = "teamid", referencedColumnName = "id", nullable = false)
    public TeamEntity getTeamByTeamid() {
        return teamByTeamid;
    }

    public void setTeamByTeamid(TeamEntity teamByTeamid) {
        this.teamByTeamid = teamByTeamid;
    }

    @ManyToOne
    @JoinColumn(name = "playerid", referencedColumnName = "id", nullable = false)
    public PlayerEntity getPlayerByPlayerid() {
        return playerByPlayerid;
    }

    public void setPlayerByPlayerid(PlayerEntity playerByPlayerid) {
        this.playerByPlayerid = playerByPlayerid;
    }
}
