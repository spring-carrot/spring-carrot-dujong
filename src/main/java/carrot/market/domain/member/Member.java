package carrot.market.domain.member;

import carrot.market.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Data
@Table(name = "MEMBER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String password;
    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

//    private String authorities;

    //== Member 정보 업데이트 ==//
    public void updateName(String name) {
        this.name = name;
    }
    public void updatePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }
    public void updateEmail(String email) {
        this.email = email;
    }
    public void updatePhone(String phone) {
        this.phone = phone;
    }

    //== 비밀번호 암호화 ==//
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    //회원가입시, USER의 권한을 부여하는 METHOD
    public void addUserAuthority() {
        this.role = Role.USER;
    }
}
