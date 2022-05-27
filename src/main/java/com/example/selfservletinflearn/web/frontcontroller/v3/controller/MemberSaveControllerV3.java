package com.example.selfservletinflearn.web.frontcontroller.v3.controller;

import com.example.selfservletinflearn.domain.member.Member;
import com.example.selfservletinflearn.domain.member.MemberRepository;
import com.example.selfservletinflearn.web.frontcontroller.ModelView;
import com.example.selfservletinflearn.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // http servlet request에서 getParam하지 않고,
        // 프론트컨트롤러에서 처리한 뒤 여기에 넣어줄 예정
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
