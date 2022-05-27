package com.example.selfservletinflearn.web.frontcontroller.v2.controller;

import com.example.selfservletinflearn.domain.member.Member;
import com.example.selfservletinflearn.domain.member.MemberRepository;
import com.example.selfservletinflearn.web.frontcontroller.MyView;
import com.example.selfservletinflearn.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보낸다
        request.setAttribute("member", member);

        // 프론트 컨트롤러를 활용하여 맨 아래 세줄을 이렇게 줄일 수 있음.
        return new MyView("/WEB-INF/views/save-result.jsp");

        //String viewPath = "/WEB-INF/views/save-result.jsp"; // todo: 이 경로에 webapp 추가하면 뷰 매핑이 안되는데, 이유가 무엇이었을까? 하위매핑?
        //RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //dispatcher.forward(request, response);
    }
}
