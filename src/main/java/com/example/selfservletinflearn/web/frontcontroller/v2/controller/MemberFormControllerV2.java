package com.example.selfservletinflearn.web.frontcontroller.v2.controller;

import com.example.selfservletinflearn.web.frontcontroller.MyView;
import com.example.selfservletinflearn.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    // 여기 request response도 필요 없는데 스팩상 받아야함.
    // v3에서 개선해보자.
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
