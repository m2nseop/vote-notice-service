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

//    @RequestMapping(value = "/")
//    public String index(HttpServletResponse response, HttpServletRequest request) {
//        return "redirect:/home.do";
//    }
//    원래는 이렇게 해서 interceptor를 이용해서 로그인이 안되었을 때 로그인 화면으로 이동하게 해야함

    // 일단은 그냥 로그인 화면으로 가는 것으로 ㄱㄱ
    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
//        if(session.getId() != null){
//            mv.setViewName("content/home");
//            return mv;
//        }
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
            // 로그인 정보 활용
            String username = loginMember.getId();
//            System.out.println(username);
            System.out.println("로그인됨");
            // 추가적인 작업 수행...
        } else {
            System.out.println("정보 날라감");
            session.invalidate();
            // 로그인되지 않은 경우 처리
            // 로그인 페이지로 리다이렉트 또는 예외 처리 등을 수행할 수 있습니다.
            mv.setViewName("redirect:/"); // 예시: 로그인 페이지로 리다이렉트
            return mv;
        }

        mv.addObject("voteList", mainService.getVoteList()); // mainDTO를 "mainDTO"라는 이름으로 추가
        mv.addObject("announceList", mainService.getAnnounceList());
        // home 뷰로 이동
        mv.setViewName("content/home");
        return mv;
    }
}
