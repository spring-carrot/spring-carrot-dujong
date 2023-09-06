package carrot.market.domain.member.repository;

import carrot.market.domain.member.Member;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
//@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @AfterEach
    private void after() {
        em.clear();
    }
    private void clear() {
        em.flush();
        em.clear();
    }
    @Test
    public void save() {
        //given
        Member member = Member.builder().name("JD").password("1234").email("parksn5029").phone("01062058826").build();
        //when
        memberRepository.save(member);
//        em.flush();

        //then
        List<Member> memberList = memberRepository.findAll();
        log.info("memberList={}", memberList);
        assertThat(memberList.size()).isEqualTo(1);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = Member.builder().name("JD1").password("1234").email("parksn5029").phone("01062058826").build();
        Member member2 = Member.builder().name("JD2").password("1234").email("parksn5029").phone("01062058826").build();
        memberRepository.save(member1);
        memberRepository.save(member2);
//        clear();
        //when
        Optional<Member> findMember1 = memberRepository.findByName("JD1");
        Optional<Member> findMember2 = memberRepository.findByName("JD2");
        //then
        assertThat(findMember1.get().getEmail()).isEqualTo(member1.getEmail());
        assertThat(findMember1.get().getPassword()).isEqualTo(member1.getPassword());
    }

}