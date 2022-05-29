package com.example.selfservletinflearn.web.frontcontroller.servletmvc.springmvc.old;

// 과거버전 스프링 컨트롤러
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/old-controller") // 스프링 빈의 이름 지정. url 호출로 호출됨.
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");

        // application.properties에 아래 추가해주면 뷰 리졸버가 잘 동작한다. url을 만들어주는걸 자동으로 해줌
        //spring.mvc.view.prefix=/WEB-INF/views/
        //spring.mvc.view.suffix=.jsp
    }
}
