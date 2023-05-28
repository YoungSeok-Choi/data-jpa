package study.datajpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(of = {"id", "name"}) // 연관관계는 순환참조로 스택 오버플로우 발생할 수 있으니깐, DTO 객체 정도에 써서 편하게 쓸수 있겠다.
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;


    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY) //실제 맵핑되는 객체의 이름.. 실제 데이터베이스에는 존재하지 않고 객세 상에서만 존재하는 애다.. 헷갈림
    List<Member> memberList = new ArrayList<>();


    public Team(String name) {
        this.name = name;
    }
}
