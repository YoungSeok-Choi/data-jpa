package study.datajpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter //별도 세터 함수를 만들어서 해당 함수 내에서 변경 허용을 판단하고 세팅해줄 수 있도록 한다 (세터는 변경이 강제되기 때문에 잘 사용하지 않는다고 한다)
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // 브이캣 코드에서도 굳이굳이 맵핑해주는 경우가 많았다.. 특별한 이유가 있을까>?
    private Long id;
    private int age;
    private String userName;


    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String userName) {
        this.userName = userName;
    }

    public Member(String username, int age, Team team) {
        this.userName = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public Member(String userName, int age) {
        this.age = age;
        this.userName = userName;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMemberList().add(this);
    }

}
