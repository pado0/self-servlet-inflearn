package com.example.selfservletinflearn.web.frontcontroller.servletmvc.springmvc.v3;

import com.example.selfservletinflearn.domain.member.Member;
import com.example.selfservletinflearn.domain.member.MemberRepository;
import org.eclipse.jdt.internal.compiler.env.IGenericType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();




    // 이제 모델 뷰를 직접 생성하지 않아도 된다. 문자를 반환해도 뷰 이름으로 알고 프로세스를 진행해줌
    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    // 자동 형변환 시켜주고, httpServlet request.getParam을 하지 않아도 파라미터를 받아올 수 있다.
    //@RequestMapping(value = "/save",method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        //String username = request.getParameter("username");
        //int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // ModelAndView mv = new ModelAndView("save-result");
        // mv.getModel().put("member", member);
        // mv.addObject("member", member);

        model.addAttribute("member", member);
        return "save-result";
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        //ModelAndView mv = new ModelAndView("members");
        //mv.addObject("members", members);

        model.addAttribute("members", members);
        return "members";
    }
}
