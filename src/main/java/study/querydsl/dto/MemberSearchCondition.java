package study.querydsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSearchCondition {
    // 회원명, 팀명, 나이(ageGoe, ageLoe)
    private String userName;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;

    public MemberSearchCondition(String userName) {
        this.userName = userName;
    }

    public MemberSearchCondition(String userName, int ageGoe) {
        this.userName = userName;
        this.ageGoe = ageGoe;
    }

    public MemberSearchCondition(String userName, int ageGoe, int ageLoe) {
        this.userName = userName;
        this.ageGoe = ageGoe;
        this.ageLoe = ageLoe;
    }

    public MemberSearchCondition(String userName, String teamName, int ageGoe, int ageLoe) {
        this.userName = userName;
        this.teamName = teamName;
        this.ageGoe = ageGoe;
        this.ageLoe = ageLoe;
    }
}
