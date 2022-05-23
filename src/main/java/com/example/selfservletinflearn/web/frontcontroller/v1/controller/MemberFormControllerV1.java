package com.example.selfservletinflearn.web.frontcontroller.v1.controller;

import com.example.selfservletinflearn.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿 아니어도 된다.
public class MemberFormControllerV1 implements ControllerV1 {
    // 기존 서블릿과 동일
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/webapp/WEB-INF/views/new-form.jsp";
        System.out.println("viewPath = " + viewPath);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
