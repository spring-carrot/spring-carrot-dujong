package carrot.market.domain.member.service;

import carrot.market.domain.member.Member;
import carrot.market.domain.member.dto.MemberSignUpDto;
import carrot.market.domain.member.repository.MemberRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void signUp(MemberSignUpDto memberSignUpDto){
        // 중복검사
        if (memberRepository.findByName(memberSignUpDto.name()).isPresent()) {
            throw new DuplicateRequestException("이미 있는 이름입니다.");
        }

        // 회원 Builder
        Member member = memberSignUpDto.toEntity();
        // password 암호화
        member.encodePassword(passwordEncoder);
        memberRepository.save(member);
    }
}
