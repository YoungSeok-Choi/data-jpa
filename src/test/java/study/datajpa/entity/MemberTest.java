package study.datajpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testEntity() {

        Team a = new Team("A");
        Team b = new Team("B");

        em.persist(a);
        em.persist(b);

        Member member1 = new Member("member1", 10, a);
        Member member2 = new Member("member2", 20, a);
        Member member3 = new Member("member3", 30, b);
        Member member4 = new Member("member4", 40, b);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        //초기화
        em.flush(); // 실제 디비 서버에 쿼리를 날리는 동작
        em.clear(); //  영속성 콘텍스트 비우는 동작.


        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        for (Member member : members) {
            System.out.println("member=" + member);
            System.out.println("-> member.team=" + member.getTeam());
        }

    }


}