package sperias.group.GroupStore;

import sperias.group.Entity.PlayerGroup;
import sperias.group.Entity.Group.Grade;
import sperias.group.Entity.Group.Rank;

import java.util.*;

public class GroupStore {

    private Map<Integer, Grade> GradeList;
    private Map<Integer, Rank> RankList;
    private Map<UUID, PlayerGroup> PlayerGroupList = new HashMap<>();

    public GroupStore(Map<Integer, Grade> gradeList, Map<Integer, Rank> rankList) {
        GradeList = gradeList;
        RankList = rankList;
    }

    public Map<Integer, Grade> getGradeList() {
        return GradeList;
    }
    public Map<Integer, Rank> getRankList() {
        return RankList;
    }
    public Map<UUID, PlayerGroup> getPlayerGroupList() {
        return PlayerGroupList;
    }

    public List<Grade> getLowerGradeList(int GradeOfPlayerClassment)
    {
        List<Grade> GradeList = new ArrayList<>();
        for(Grade grade : this.GradeList.values())
        {
            if(GradeOfPlayerClassment >= grade.getId() )
            {
                GradeList.add(grade);
            }
        }
        return GradeList;
    }
    public List<Rank> getLowerRankList(int RankOfPlayerClassment)
    {
        List<Rank> rankList = new ArrayList<>();
        for(Rank aRank : this.RankList.values())
        {
            if(RankOfPlayerClassment >= aRank.getId() )
            {
                rankList.add(aRank);
            }
        }
        return rankList;
    }

    public Grade getGradeByName(String GradeName)
    {
        int GradeID = 1;
        Grade NewGrade = null;
        while(GradeID <= this.GradeList.size() && NewGrade == null)
        {
            if(this.GradeList.get(GradeID) != null && this.GradeList.get(GradeID).getName().equalsIgnoreCase(GradeName))
            {
                NewGrade = this.GradeList.get(GradeID);
            }
            GradeID++;
        }
        return NewGrade;
    }

    public Rank getRankByName(String RankName)
    {
        int RankID = 1;
        Rank newRank = null;
        while(RankID <= this.RankList.size() && newRank == null)
        {
            if(this.RankList.get(RankID) != null && this.RankList.get(RankID).getName().equalsIgnoreCase(RankName))
            {
                newRank = this.RankList.get(RankID);
            }
            RankID++;
        }
        return newRank;
    }
}
