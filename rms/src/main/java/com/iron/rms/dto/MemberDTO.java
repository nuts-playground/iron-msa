package com.iron.rms.dto;

import com.iron.rms.constant.MemberType;
import com.iron.rms.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.NONE)
public class MemberDTO {

    @NotEmpty(message = "사용자 이름은 필수 입니다.")
    @Size(max = 20, message = "사용자 이름은 20글자까지 작성가능합니다.")
    private String memberName;

    @NotEmpty(message = "전화번호는 필수 입니다.")
    @Pattern(regexp = "[0-9]{10,11}", message = "전화번호는 10~11자리까지 숫자만 입력가능합니다.")
    private String phone;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String memberPw;

    //	@NotEmpty(message = "사용자 유형은 필수 입니다.")
    private MemberType memberType;

    public Member toEntity() {
        return new Member(memberName, memberPw, phone, memberType);
    }


}
