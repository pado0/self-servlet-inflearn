package com.example.selfservletinflearn.web.frontcontroller.v2;

import com.example.selfservletinflearn.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    //V1에서 MyView를 넘기도록변경
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
