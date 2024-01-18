package com.iron.rms.member.domain;

import com.iron.rms.constant.MemberType;
import com.iron.rms.reservation.domain.Reservation;
import com.iron.rms.party.domain.Party;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_MEMBER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)	// 기본생성자는 막고 싶을 때, JPA 스펙상 PROTECTED는 열어둬야할 때 사용
@ToString(of = {"memberId", "memberName", "phone", "memberType", "createdTime"})
@EntityListeners(AuditingEntityListener.class)
public class Member {

	@Schema(description = "회원ID", example = "1")
	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long memberId;

	@Schema(description = "회원이름", example = "iron")
	@Column(name = "member_name")
	private String memberName;

	@Schema(description = "회원전화번호", example = "01012345678")
	@Column(name = "phone")
	private String phone;

	@Schema(description = "비밀번호", example = "xxxxxxxxx")
	@Column(name = "memberPw")
	private String memberPw;

	@OneToMany(mappedBy = "member")
	List<Party> parties = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	List<Reservation> reservations = new ArrayList<>();

	@Schema(description = "회원유형", example = "ADMIN")
	@Enumerated(EnumType.STRING)
	@Column(name = "member_type")
	private MemberType memberType;        // ADMIN(관리자), MASTER(채널주인), MEMBER(일반회원), BOTH(채널주인+일반회원)

	@Schema(description = "회원가입일시", example = "2023-11-01 15:23:39")
	@CreatedDate
	@Column(name = "created_time")
	private LocalDateTime createdTime;

	public Member(String memberName, String phone, String memberPw, MemberType memberType) {
		this.memberName = memberName;
		this.phone = phone;
		this.memberPw = memberPw;
		this.memberType = memberType;
	}
}
