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

    // 나 이런 서비스를 쓸거야

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
        vote.setId(loginMember.getId());
        vote.setName(loginMember.getName());
        voteService.createVoteAgenda(vote);

        return "redirect:/home.do";
    }

    @GetMapping("/vote/detail/{voteId}")
    public String showVoteDetails(@PathVariable int voteId, Model model, HttpSession session) {
        Student loginMember = (Student) session.getAttribute("loginMember");
        boolean isVoted = false;
        // 여기서 투표 상세 정보를 데이터베이스 등에서 가져오는 로직을 추가
        Vote vote = voteService.getVoteById(voteId);



        if( voteService.checkVoteById(loginMember.getId(), voteId) == 1) {
            // 투표 한 경우
            isVoted = true;
            System.out.println("투표한놈임");
        }
        model.addAttribute("isVoted", isVoted);
        String domainUrl = "http://localhost:8080/tactVote/detail/";
        model.addAttribute("voteUrl", domainUrl + vote.getQrToken());
        // 투표했는지 확인해서 해당 데이터 내용 가지고 감
        model.addAttribute("vote", vote);
        return "vote/vote_detail";  // 투표 상세 페이지의 Thymeleaf 템플릿 이름
    }

    @GetMapping("/tactVote/detail/{token}")
    public String votePage(@PathVariable String token, Model model, HttpSession session) {
        Student loginMember = (Student) session.getAttribute("loginMember");
        boolean isVoted = false;

        Vote vote = voteService.getVoteByToken(token);

        if( voteService.checkVoteById(loginMember.getId(), vote.getVoteId()) == 1) {
            // 투표 한 경우
            isVoted = true;
            System.out.println("투표한놈임");
        }
        model.addAttribute("isVoted", isVoted);
        model.addAttribute("vote", vote);
        // 토큰을 가지고 투표 정보를 가져오는 메서드 (이 메서드는 데이터베이스에서 토큰을 검증하고 투표 정보를 반환해야 함)
//        Vote vote = voteService.getVoteByToken(token);
        // 토큰에 해당하는 페이지 추가
        // 토큰이 유효한 경우 모델에 투표 정보 추가
//        model.addAttribute("vote", vote);
        return "vote/vote_detail_tact";  // 투표 페이지로 이동하는 Thymeleaf 템플릿 이름
        // 토큰이 유효하지 않은 경우 예외 처리 또는 다른 작업 수행
    }

    @GetMapping("/vote/createUrl/{voteId}")
    public String createVoteUrl(@PathVariable int voteId, Model model){
        String voteUrl = voteService.generateToken(voteId);
//        String domainUrl = "http://localhost:8080"; // 배포시 도메인 주소를 넣으면 된다.
//        model.addAttribute("voteUrl", domainUrl + voteUrl);
        System.out.println("잘 넘어왔나?" + voteId);
        return "redirect:/vote/detail/" + voteId;
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

    @PostMapping("/tactVote/cast/{voteId}")
    public String castTactVote(@PathVariable int voteId, @RequestParam String voteOption, @RequestParam String anonymityType, HttpSession session){
        Student loginMember = (Student) session.getAttribute("loginMember");
        System.out.println("됐나?" + anonymityType);
        if(anonymityType.equals("기명")){
            voteService.castVote(voteId, loginMember.getId(), loginMember.getName(),Integer.parseInt(voteOption));
        }else {
            voteService.castVote(voteId, loginMember.getId(), null,Integer.parseInt(voteOption));
        }
        Vote vote = voteService.getVoteById(voteId);
        return "redirect:/tactVote/detail/" + vote.getQrToken();
    }

    @GetMapping("/vote/delete/{voteId}")
    public String deleteVote(@PathVariable int voteId){
        System.out.println("들어오긴 하니?");
        voteService.deleteVote(voteId);
        return "redirect:/home.do";
    }

    @GetMapping("/vote/update/{voteId}")
    public String goUpdateVotePage(@PathVariable int voteId, Model model){
        Vote vote = voteService.getVoteById(voteId);
        model.addAttribute("vote", vote);

        // update_vote 템플릿(html)으로 이동
        return "vote/update_vote";
    }

    @PostMapping("/vote/update/{voteId}")
    public String updateVote(@PathVariable int voteId, @ModelAttribute("vote") Vote vote){
        vote.setVoteId(voteId); // 경로 변수로부터 투표 ID를 받아와 설정합니다.
        voteService.updateVote(vote); // 서비스를 통해 업데이트 메서드를 호출합니다.

        return "redirect:/home.do";
    }
}
