package study.datajpa.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
@Rollback(false)
class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {

        Member choi = new Member("choi");
        Member save = memberJpaRepository.save(choi);

        Member member = memberJpaRepository.find(save.getId());

        Assertions.assertThat(member.getId()).isEqualTo(choi.getId());
        // 트랜잭션 안의 같은 엔티는 동일성을 보장한다 신기함... 처음의 만들어진 choi도 id가 있다. jpa에서의 1차 캐시를 다시 공부해보기

    }

    @Test
    public void teset2() {
        Member choi = new Member("choi", 20);
        Member choi1 = new Member("choi", 50);

        memberJpaRepository.save(choi);
        memberJpaRepository.save(choi1);


        List<Member> choi2 = memberJpaRepository.findByUsernameAndAgeGreaterThen("choi", 40);

        System.out.println(choi2.size());


    }


}

//    No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
//        org.springframework.dao.InvalidDataAccessApiUsageException: No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
//        at app//org.springframework.orm.jpa.EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(EntityManagerFactoryUtils.java:400)
//        at app//org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:234)
//        at app//org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.translateExceptionIfPossible(AbstractEntityManagerFactoryBean.java:550)
//        at app//org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.java:61)
//        at app//org.springframework.dao.support.DataAccessUtils.translateIfNecessary(DataAccessUtils.java:242)
//        at app//org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:152)
//        at app//org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
//        at app//org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)
//        at app//org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:702)
//        at app//study.datajpa.repository.MemberJpaRepository$$SpringCGLIB$$0.save(<generated>)