package com.univ.VoteProject.Announce;

import com.univ.VoteProject.Model.Announce;
import com.univ.VoteProject.Model.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnnounceController {
    @Autowired
    AnnounceService announceService;

    @GetMapping("/announce/create.do")
    public String createAnnounceForm(Model model){
        model.addAttribute("announce", new Announce());
        return "announce/create_announce";
    }

    @PostMapping("/announce/create.do")
    public String createAnnounceAgenda(@ModelAttribute Announce announce, HttpSession session){

        Student loginMember = (Student) session.getAttribute("loginMember");

        announce.setId(loginMember.getId());
        System.out.println(announce.getAnnTitle());
        System.out.println(announce.getAnnTitle());
        System.out.println(announce.getAnnTitle());
        System.out.println(announce.getAnnTitle());

        announceService.createAnnounceAgenda(announce);

        return "redirect:/home.do";
    }
}
