package my.frist.hellospring.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.frist.hellospring.repository.JpaMemberRepository;
import my.frist.hellospring.repository.MemberRepository;

@Configuration
public class SpringConfig {

    // private DataSource dataSource;
    // private EntityManager em;
    
    // @Autowired
    // public SpringConfig(DataSource dataSource, EntityManager em) {
    //     this.dataSource = dataSource;
    //     this.em = em;
    // }

    // interface SpringDataJpaMemberRepository extends JpaRepository를 통해 spring bean에 등록을 해놓기 때문에 따로 등록할 필요 없음
    private final MemberRepository memberRepository;
    
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // @Bean
    // public MemberService memberService() {
    //     return new MemberService(memberRepository());
    // }

    // @Bean
    // public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    // }
}
