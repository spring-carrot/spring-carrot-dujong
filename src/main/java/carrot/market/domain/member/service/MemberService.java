package carrot.market.domain.member.service;

import carrot.market.domain.member.dto.MemberSignUpDto;

public interface MemberService {
    void signUp(MemberSignUpDto memberSignUpDto);

//    void update(MemberUpdateDto memberUpdateDto) throws Exception;
//    void updatePassword(String checkPassword, String toBePassword) throws Exception;
//    void withdraw(String checkPassword) throws Exception;
//    MemberInfoDto getInfo(Long id) throws Exception;
//    MemberInfoDto getMyInfo() throws Exception;
}
