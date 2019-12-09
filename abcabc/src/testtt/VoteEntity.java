package testtt;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vote", schema = "votingsystem", catalog = "")
public class VoteEntity {
    private String voteA;
    private String voteB;
    private String voteTitle;
    private Integer voteACount;
    private Integer voteBCount;

    @Basic
    @Column(name = "voteA")
    public String getVoteA() {
        return voteA;
    }

    public void setVoteA(String voteA) {
        this.voteA = voteA;
    }

    @Basic
    @Column(name = "voteB")
    public String getVoteB() {
        return voteB;
    }

    public void setVoteB(String voteB) {
        this.voteB = voteB;
    }

    @Id
    @Column(name = "voteTitle")
    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    @Basic
    @Column(name = "voteACount")
    public Integer getVoteACount() {
        return voteACount;
    }

    public void setVoteACount(Integer voteACount) {
        this.voteACount = voteACount;
    }

    @Basic
    @Column(name = "voteBCount")
    public Integer getVoteBCount() {
        return voteBCount;
    }

    public void setVoteBCount(Integer voteBCount) {
        this.voteBCount = voteBCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteEntity that = (VoteEntity) o;
        return Objects.equals(voteA, that.voteA) &&
                Objects.equals(voteB, that.voteB) &&
                Objects.equals(voteTitle, that.voteTitle) &&
                Objects.equals(voteACount, that.voteACount) &&
                Objects.equals(voteBCount, that.voteBCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteA, voteB, voteTitle, voteACount, voteBCount);
    }
}
