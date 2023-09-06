package carrot.market.domain.member.controller;

import carrot.market.domain.member.Member;
import carrot.market.domain.member.dto.MemberLoginDto;
import carrot.market.domain.member.dto.MemberSignUpDto;
import carrot.market.domain.member.service.MemberService;
import carrot.market.global.jwt.TokenProvider;
import carrot.market.global.jwt.dto.TokenDto;
import carrot.market.global.jwt.filter.JwtFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@Controller
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 회원가입
     */
    @PostMapping("/signUp")
    public ResponseEntity<Member> signUp(@Valid @RequestBody MemberSignUpDto memberSignUpDto) throws Exception {
        Member member = memberService.signUp(memberSignUpDto);
        return ResponseEntity.ok(member);
    }

    /**
     *  Auth
     */
    @GetMapping("/api/member")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity getMyUserInfo() throws Exception {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/api/member/{name}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getUserInfo(@PathVariable String name) throws Exception {
        return ResponseEntity.ok(memberService.getInfo(name));
    }

    @PostMapping("/api/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody MemberLoginDto memberLoginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberLoginDto.getUsername(), memberLoginDto.getPassword());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        // tokenDto를 이용해 response body에도 넣어서 리턴
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }


}
