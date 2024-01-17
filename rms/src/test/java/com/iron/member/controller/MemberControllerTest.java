package com.iron.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iron.rms.constant.MemberType;
import com.iron.rms.dto.MemberDTO;
import com.iron.rms.member.controller.MemberController;
import com.iron.rms.member.domain.Member;
import com.iron.rms.member.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Slf4j
@Transactional
public class MemberControllerTest {


	@Autowired
	private MemberService memberService;

	@Autowired
	MockMvc mvc;

	@PersistenceContext
	EntityManager em;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(new MemberController(memberService))
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.build();

	}

	@Test
	@DisplayName("회원가입 테스트")
	public void add() throws Exception {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

//			log.info(objectMapper.writeValueAsString(getTestMember()));

			mvc.perform(post("/member/add")
							.contentType(MediaType.APPLICATION_JSON)
							.header("accountId", "admin")
							.accept(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(getTestMember())))
					.andDo(print())
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail(ExceptionUtils.getStackTrace(e));
		}
	}

	private MemberDTO getTestMember() {
		return new MemberDTO("김철수", "01011112222", "0000", MemberType.MEMBER);
	}
}
