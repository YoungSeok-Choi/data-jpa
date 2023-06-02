package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;



// 내가 사용하던게 Spring Data jpa였다
// 인터페이스기반으로 기본적인 CRUD제공을 하느다.
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
