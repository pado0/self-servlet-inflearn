package com.example.selfservletinflearn.web.frontcontroller.v4.controller;

import com.example.selfservletinflearn.domain.member.Member;
import com.example.selfservletinflearn.domain.member.MemberRepository;
import com.example.selfservletinflearn.web.frontcontroller.v4.ControllerV4;

import java.util.Map;


public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 파라미터에서 값 꺼내고
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 내 비즈니스 로직 실행하고
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 모델에 값 담고
        model.put("member", member);

        // 문자로 뷰이름 리턴
        return "save-result";
    }
}
