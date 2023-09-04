package carrot.market.domain.member.controller;

import carrot.market.domain.member.dto.MemberSignUpDto;
import carrot.market.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/signUp")
    public void signUp(@Valid @RequestBody MemberSignUpDto memberSignUpDto) {
        memberService.signUp(memberSignUpDto);
    }


}
