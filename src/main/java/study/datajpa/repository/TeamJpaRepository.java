package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Team;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamJpaRepository {

    @PersistenceContext //  jpa 의 엔티티 매니저를 인젝션 해주는 어노테이션이다. 자세한건 문서를 보자
    private EntityManager em;


    public Team save(Team team) {
        em.persist(team);
        return team;
    }

    public void delete(Team team) {
        em.remove(team);
    }

    public List<Team> findAll() {
        return em.createQuery("select t from Team t", Team.class).getResultList();
    }

    public Optional<Team> findById(Long id) {
        return Optional.ofNullable(em.find(Team.class, id));
    }

    public long count() {
        return em.createQuery("select count(t) from Team t", Long.class).getSingleResult();
    }

}

// 트랜잭션 안에서 엔티티 객체를 변경한것을 감지함으로써 더티체킹이 일어나고, 트랜잭션이 모두 끝나고 커밋이 발생할 때 해당 변경사항이 반영된다고 생각하면 되겠따
