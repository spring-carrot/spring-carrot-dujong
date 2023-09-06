package carrot.market.domain.member.service;

import carrot.market.domain.member.Member;
import carrot.market.domain.member.dto.MemberInfoDto;
import carrot.market.domain.member.dto.MemberSignUpDto;

public interface MemberService {
    Member signUp(MemberSignUpDto memberSignUpDto) throws Exception;
    MemberInfoDto getInfo(String name) throws Exception;
    MemberInfoDto getMyInfo() throws Exception;

//    void update(MemberUpdateDto memberUpdateDto) throws Exception;
//    void updatePassword(String checkPassword, String toBePassword) throws Exception;
//    void withdraw(String checkPassword) throws Exception;

}
