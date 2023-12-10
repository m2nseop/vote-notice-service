package com.univ.VoteProject.Vote;

import com.univ.VoteProject.Model.Student;
import com.univ.VoteProject.Model.Vote;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    @GetMapping("/vote/create.do")
    public String createVoteForm(Model model) {
        model.addAttribute("vote", new Vote());
        System.out.println("wlsWk?");
        return "vote/create_vote";
    }

    @PostMapping("/vote/create.do")
    public String createVoteAgenda(@ModelAttribute Vote vote, HttpSession session) {
        Student loginMember = (Student) session.getAttribute("loginMember");

        vote.setId(loginMember.getId());

        voteService.createVoteAgenda(vote);

        System.out.println("title: " + vote.getTitle());
        System.out.println("votePeriod: " + vote.getVotePeriod());
        System.out.println("targetScope: " + vote.getTargetScope());
        System.out.println("attendanceType: " + vote.getAttendanceType());
        System.out.println("anonymityType: " + vote.getAnonymityType());
        System.out.println("qrId: " + vote.getQrId());
        return "redirect:/home.do";
    }

}
