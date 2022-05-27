package com.example.selfservletinflearn.web.frontcontroller.v1;

import com.example.selfservletinflearn.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.selfservletinflearn.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.selfservletinflearn.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// *로 경로지정: v1 하위의 어떤 Url이 들어와도 일단 이 컨트롤러 서블릿 호출
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 매핑정보
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        // 매핑정보를 보고 url에 맞는 컨트롤러를 호출. 다형성 사용 컨트롤러 구현체
        ControllerV1 controller = controllerMap.get(requestURI); // 다형성 쓰기
        //System.out.println("controller = " + controller.toString());
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}
