package com.univ.VoteProject.Main;

import com.univ.VoteProject.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

//    @RequestMapping(value = "/")
//    public String index(HttpServletResponse response, HttpServletRequest request) {
//        return "redirect:/home.do";
//    }
//    원래는 이렇게 해서 interceptor를 이용해서 로그인이 안되었을 때 로그인 화면으로 이동하게 해야함

    // 일단은 그냥 로그인 화면으로 가는 것으로 ㄱㄱ
    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/home.do", method= RequestMethod.GET)
    public ModelAndView goHome(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("content/home");

        return mv;
    }
}
