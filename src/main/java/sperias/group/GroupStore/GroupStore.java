package sperias.group.GroupStore;

import sperias.group.Entity.PlayerGroup;
import sperias.group.Entity.Group.Grade;
import sperias.group.Entity.Group.Rank;

import java.util.*;

public class GroupStore {

    private final Map<Integer, Grade> gradeList;
    private final Map<Integer, Rank> rankList;
    private final Map<UUID, PlayerGroup> playerGroupList = new HashMap<>();

    public GroupStore(Map<Integer, Grade> gradeList, Map<Integer, Rank> rankList) {
        this.gradeList = gradeList;
        this.rankList = rankList;
    }

    public Map<Integer, Grade> getGradeList() {
        return gradeList;
    }
    public Map<Integer, Rank> getRankList() {
        return rankList;
    }
    public Map<UUID, PlayerGroup> getPlayerGroupList() {
        return playerGroupList;
    }

    public Grade getGradeByName(String GradeName)
    {
        int GradeID = 1;
        Grade NewGrade = null;
        while(GradeID <= this.gradeList.size() && NewGrade == null)
        {
            if(this.gradeList.get(GradeID) != null && this.gradeList.get(GradeID).getName().equalsIgnoreCase(GradeName))
            {
                NewGrade = this.gradeList.get(GradeID);
            }
            GradeID++;
        }
        return NewGrade;
    }

    public Rank getRankByName(String RankName)
    {
        int RankID = 1;
        Rank newRank = null;
        while(RankID <= this.rankList.size() && newRank == null)
        {
            if(this.rankList.get(RankID) != null && this.rankList.get(RankID).getName().equalsIgnoreCase(RankName))
            {
                newRank = this.rankList.get(RankID);
            }
            RankID++;
        }
        return newRank;
    }
}
