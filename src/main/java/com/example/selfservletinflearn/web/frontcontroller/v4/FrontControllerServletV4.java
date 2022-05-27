package com.example.selfservletinflearn.web.frontcontroller.v4;

import com.example.selfservletinflearn.web.frontcontroller.ModelView;
import com.example.selfservletinflearn.web.frontcontroller.MyView;
import com.example.selfservletinflearn.web.frontcontroller.v3.ControllerV3;
import com.example.selfservletinflearn.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.selfservletinflearn.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.selfservletinflearn.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.selfservletinflearn.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.selfservletinflearn.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.selfservletinflearn.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// *로 경로지정: v1 하위의 어떤 Url이 들어와도 일단 이 컨트롤러 서블릿 호출
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 매핑정보
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = request.getRequestURI();

        // 컨트롤러와 매핑해줌
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 3. 모델뷰 반환받기
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model); // 2.컨트롤러 호출시 모델을 넘겨줌


        //String viewName = mv.getViewName(); // 이름 받아올 필요 없어짐
        MyView view = viewResolver(viewName);

        //view.render(mv.getModel(), request,response);
        view.render(model, request,response); // getModel 할 필요가 없
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        // iterator 하나씩 돌며 파라미터를 맵에 저장
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
