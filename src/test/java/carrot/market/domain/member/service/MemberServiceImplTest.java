package carrot.market.domain.member.service;

import carrot.market.domain.member.Member;
import carrot.market.domain.member.dto.MemberSignUpDto;
import carrot.market.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberServiceImplTest {
    @Autowired
    MemberService memberService;
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
    public void signUp() throws Exception {
        //given
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("ParkJongDu", "qwer1234!", "parksn5029@naver.com", "010-6205-8826");

        //when
        memberService.signUp(memberSignUpDto);
        clear();

        //then
        Member member = memberRepository.findByName(memberSignUpDto.name()).orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));
        assertThat(member.getId()).isNotNull();
        assertThat(member.getPassword()).isNotNull();
        assertThat(member.getName()).isEqualTo(memberSignUpDto.name());
        assertThat(member.getEmail()).isEqualTo(memberSignUpDto.email());
        assertThat(member.getPhone()).isEqualTo(memberSignUpDto.phone());
    }

}