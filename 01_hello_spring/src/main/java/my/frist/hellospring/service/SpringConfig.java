package my.frist.hellospring.service;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.frist.hellospring.repository.JpaMemberRepository;
import my.frist.hellospring.repository.MemberRepository;

@Configuration
public class SpringConfig {

    // private DataSource dataSource;

    // @Autowired
    // public SpringConfig(DataSource dataSource) {
    //     this.dataSource = dataSource;
    // }

    private EntityManager em;
   
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    // @Bean
    // public MemberService memberService() {
    //     return new MemberService(memberRepository());
    // }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
