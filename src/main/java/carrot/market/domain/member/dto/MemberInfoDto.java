package carrot.market.domain.member.dto;

import carrot.market.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberInfoDto {
    private String name;
    private String email;
    private String phone;

    @Builder
    public MemberInfoDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
    }

}