package com.iron.rms.member.controller;

import com.iron.rms.dto.MemberDTO;
import com.iron.rms.dto.ResponseDTO;
import com.iron.rms.member.domain.Member;
import com.iron.rms.member.service.MemberService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@OpenAPIDefinition(info = @Info(title = "회원관련 요청 API", description = "회원관련 요청 API", version = "v1"))
@RestController
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 요청", description = "회원가입 가능", tags = {"addMember"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "501", description = "API EXCEPTION")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> addMember(
            HttpServletRequest request,
            @Valid @ModelAttribute("member") MemberDTO memberDTO,
            BindingResult bindingResult) throws Exception {

        ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();

        if (bindingResult.hasErrors()) {
            responseBuilder.code("501").message("valid error");
            return ResponseEntity.ok(responseBuilder.build());
        }


        String accountId = request.getHeader("accountId").toString().replace("[", "").replace("]", "");
        log.info("accountId = {}", accountId);

        // add Member
        Member newMember = memberDTO.toEntity();
        memberService.addMember(newMember);
        log.debug("request add member id = {}", newMember.getMemberId());

        responseBuilder.code("200").message("success");

        return ResponseEntity.ok(responseBuilder.build());
    }
}
