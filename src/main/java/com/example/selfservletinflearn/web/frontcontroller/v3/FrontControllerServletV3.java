package com.example.selfservletinflearn.web.frontcontroller.v3;

import com.example.selfservletinflearn.web.frontcontroller.ModelView;
import com.example.selfservletinflearn.web.frontcontroller.MyView;
import com.example.selfservletinflearn.web.frontcontroller.v2.ControllerV2;
import com.example.selfservletinflearn.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.example.selfservletinflearn.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.selfservletinflearn.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import com.example.selfservletinflearn.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.selfservletinflearn.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.selfservletinflearn.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// *로 경로지정: v1 하위의 어떤 Url이 들어와도 일단 이 컨트롤러 서블릿 호출
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 매핑정보
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();



        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 2. 컨트롤러 호출
        // 3. 모델뷰 반환받기
        // 얘가 너무 디테일한 로직임. 별도 메서드로 뽑는게 좋다.
        // paramMap을 넘겨줘야함
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap); // 요청으로 들어온 데이터를 뜯어

        // 뷰 리졸버로 4.논리 이름을 물리 이름으로 변환 (별도 메서드)
        String viewName = mv.getViewName(); // 여기서 컨트롤러 호출시 논리이름 new-form이 반환
        MyView view = viewResolver(viewName);

        // 모델을 렌더에 같이 넘겨주기
        // mv.getModel을 파라미터로 받는 render를 MyView에 추가.
        // MyView에서 렌더 / 디스패치 해줌
        view.render(mv.getModel(), request,response);
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
