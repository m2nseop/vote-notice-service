package com.univ.VoteProject.Main;

import com.univ.VoteProject.DTO.MainDTO;
import com.univ.VoteProject.Model.Student;
import com.univ.VoteProject.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/home.do", method = RequestMethod.GET)
    public ModelAndView goHome(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        HttpSession session = request.getSession();
        Student loginMember = (Student) session.getAttribute("loginMember");

        if (loginMember != null) {
            String username = loginMember.getId();
            System.out.println("로그인됨");
        } else {
            System.out.println("정보 날라감");
            session.invalidate();
            mv.setViewName("redirect:/");
            return mv;
        }

        mv.addObject("voteList", mainService.getVoteList());
        mv.addObject("announceList", mainService.getAnnounceList());
        mv.setViewName("content/home");
        return mv;
    }
}
