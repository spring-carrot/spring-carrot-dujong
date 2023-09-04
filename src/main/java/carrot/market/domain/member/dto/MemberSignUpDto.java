package carrot.market.domain.member.dto;

import carrot.market.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MemberSignUpDto(
        @NotBlank(message = "아이디를 입력해주세요") @Size(min = 7, max = 25, message = "아이디는 7~25자 내외로 입력해주세요")
        String name,

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
                message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를  포함해야합니다.")
        String password,

        @NotBlank(message = "이메일을 입력해주세요")
        @Pattern(regexp = "^?=.*[@$!%*#?&]",
        message = "@를 포함해야합니다.")
        String email,
        @NotBlank
        @Pattern(regexp = "^?=.*[@$!%*#?&]",
                message = "-를 포함하지 말아주세요.")
        String phone) {
        public Member toEntity() {
                return Member.builder().name(name).password(password).email(email).phone(phone).build();
        }
}
