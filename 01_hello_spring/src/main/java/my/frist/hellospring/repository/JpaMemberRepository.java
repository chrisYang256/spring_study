package my.frist.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import my.frist.hellospring.domain.Member;

@Transactional // jpa는 데이터 변경 시 모두 transaction 안에서 실행 되어야 함.
public class JpaMemberRepository implements MemberRepository {

    // jpa는 EntityManager를 통해 모든 것이 동작함(spring boot가 설정 등을 바탕으로 만들어줌)
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // setId 까지 알아서 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { // pk 기반이기 때문에 jpql 필요 없음
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member as m where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();
            
        return result.stream().findAny();
    }

    // 변수(result)에 command + . -> inline local variable 
    @Override
    public List<Member> findAll() {
        // jpql: sql이 아닌 객체(Member entity)를 대상으로 query를 날림(select m 인 이유)
        return em.createQuery("select m from Member m", Member.class)
            .getResultList(); 
    }
    // @Override
    // public List<Member> findAll() {
    //     List<Member> result =  em.createQuery("select m from Member m", Member.class).getResultList();
    //     return result;
    // }
    
}
