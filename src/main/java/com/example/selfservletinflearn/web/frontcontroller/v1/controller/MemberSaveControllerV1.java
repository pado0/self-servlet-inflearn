package com.example.selfservletinflearn.web.frontcontroller.v1.controller;

import com.example.selfservletinflearn.domain.member.Member;
import com.example.selfservletinflearn.domain.member.MemberRepository;
import com.example.selfservletinflearn.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        request.setAttribute("member", member);
        String viewPath = "/WEB-INF/views/save-result.jsp"; // todo: 이 경로에 webapp 추가하면 뷰 매핑이 안되는데, 이유가 무엇이었을까? 하위매핑?
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
