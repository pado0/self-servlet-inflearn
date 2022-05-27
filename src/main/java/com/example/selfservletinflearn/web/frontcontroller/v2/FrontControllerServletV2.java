package com.example.selfservletinflearn.web.frontcontroller.v2;

import com.example.selfservletinflearn.web.frontcontroller.MyView;
import com.example.selfservletinflearn.web.frontcontroller.v1.ControllerV1;
import com.example.selfservletinflearn.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.selfservletinflearn.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.selfservletinflearn.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import com.example.selfservletinflearn.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.example.selfservletinflearn.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.selfservletinflearn.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// *로 경로지정: v1 하위의 어떤 Url이 들어와도 일단 이 컨트롤러 서블릿 호출
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 매핑정보
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI();

        // 매핑정보를 보고 url에 맞는 컨트롤러를 호출. 다형성 사용 컨트롤러 구현체
        ControllerV2 controller = controllerMap.get(requestURI); // 다형성 쓰기
        //System.out.println("controller = " + controller.toString());
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        // MyView의 렌더를 호출하면, MyView쪽에서 렌더 호출해서 응답 보냄.
        // 컨트롤러에서는 화면관련 일을 하나도 안함.
        view.render(request,response);
    }
}
