package study.datajpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class TestData {
    private final TestService testService;

    //@PostConstruct
    public void test(){
        testService.test();
    }
    @RequiredArgsConstructor
    @Transactional
    @Component
    static class TestService{
        private final EntityManager em;
        public void test(){

            Team teamA = new Team("teamA");
            Team teamB = new Team("teamB");
            em.persist(teamA);
            em.persist(teamB);

            Member member1 = new Member("member1",10,teamA);
            Member member2 = new Member("member2",20,teamA);
            Member member3 = new Member("member3",30,teamB);
            Member member4 = new Member("member4",40,teamB);

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);

            em.flush();
            em.clear();
        }
    }
}
