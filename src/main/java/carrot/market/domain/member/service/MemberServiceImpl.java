package carrot.market.domain.member.service;

import carrot.market.domain.member.Member;
import carrot.market.domain.member.dto.MemberInfoDto;
import carrot.market.domain.member.dto.MemberSignUpDto;
import carrot.market.domain.member.repository.MemberRepository;
import carrot.market.global.util.security.SecurityUtil;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Member signUp(MemberSignUpDto memberSignUpDto){
        // 중복검사
        if (memberRepository.findByName(memberSignUpDto.name()).isPresent()) {
            throw new DuplicateRequestException("이미 있는 이름입니다.");
        }

        // 회원 Builder
        Member member = memberSignUpDto.toEntity();
        // password 암호화
        member.encodePassword(passwordEncoder);
        member.addUserAuthority();
        memberRepository.save(member);

        return member;
    }

    @Override
    public MemberInfoDto getInfo(String name) throws Exception {
        return new MemberInfoDto(memberRepository.findByName(name).orElseThrow(() -> new NoSuchElementException()));
    }

    @Override
    public MemberInfoDto getMyInfo() throws Exception {
        Member findMember = memberRepository.findByName(String.valueOf(SecurityUtil.getLoginUsername())).orElseThrow(() -> new NoSuchElementException());
        return new MemberInfoDto(findMember);
        }

//    // 유저,권한 정보를 가져오는 메소드
//    @Transactional(readOnly = true)
//    public Optional<Member> getUserWithAuthorities(String username) {
//        return memberRepository.findOneWithAuthoritiesByUsername(username);
//    }
//
//    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
//    @Transactional(readOnly = true)
//    public Optional<Member> getMyUserWithAuthorities() {
//        return SecurityUtil.getLoginUsername()
//                .flatMap(memberRepository::findOneWithAuthoritiesByUsername);
//    }
}
