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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        System.out.println(loginMember.getName());
        vote.setId(loginMember.getName());

        voteService.createVoteAgenda(vote);

        return "redirect:/home.do";
    }

    @GetMapping("/vote/detail/{voteId}")
    public String showVoteDetails(@PathVariable int voteId, Model model, HttpSession session) {
        Student loginMember = (Student) session.getAttribute("loginMember");
        boolean isVoted = false;
        // 여기서 투표 상세 정보를 데이터베이스 등에서 가져오는 로직을 추가
        Vote vote = voteService.getVoteById(voteId);
        if( voteService.checkVoteById(loginMember.getId()) == 1) {
            // 투표 한 경우
            isVoted = true;
            System.out.println("투표한놈임");
        }
        model.addAttribute("isVoted", isVoted);
        // 투표했는지 확인해서 해당 데이터 내용 가지고 감
        model.addAttribute("vote", vote);
        return "vote/vote_detail";  // 투표 상세 페이지의 Thymeleaf 템플릿 이름
    }

    @PostMapping("/vote/cast/{voteId}")
    public String castVote(@PathVariable int voteId, @RequestParam String voteOption, @RequestParam String anonymityType, HttpSession session){
        Student loginMember = (Student) session.getAttribute("loginMember");
        System.out.println("됐나?" + anonymityType);
        if(anonymityType.equals("기명")){
            voteService.castVote(voteId, loginMember.getId(), loginMember.getName(),Integer.parseInt(voteOption));
        }else {
            voteService.castVote(voteId, loginMember.getId(), null,Integer.parseInt(voteOption));
        }
        return "redirect:/vote/detail/" + Integer.toString(voteId);
    }
}
