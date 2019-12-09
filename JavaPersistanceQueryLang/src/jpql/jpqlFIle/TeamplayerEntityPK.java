package jpql.jpqlFIle;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeamplayerEntityPK implements Serializable {
    private String teamid;
    private String playerid;

    @Column(name = "teamid")
    @Id
    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    @Column(name = "playerid")
    @Id
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
        TeamplayerEntityPK that = (TeamplayerEntityPK) o;
        return Objects.equals(teamid, that.teamid) &&
                Objects.equals(playerid, that.playerid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamid, playerid);
    }
}
