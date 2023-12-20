package com.univ.VoteProject.Vote;

import com.univ.VoteProject.Media.MediaService;
import com.univ.VoteProject.Model.Media;
import com.univ.VoteProject.Model.Student;
import com.univ.VoteProject.Model.Vote;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    MediaService mediaService;

    @GetMapping("/vote/create.do")
    public String createVoteForm(Model model) {
        model.addAttribute("vote", new Vote());
        return "vote/create_vote";
    }

    @PostMapping("/vote/create.do")
    public String createVoteAgenda(@ModelAttribute Vote vote, @RequestParam("image") MultipartFile image, @RequestParam("file") MultipartFile file, HttpSession session)
            throws IOException {
        Student loginMember = (Student) session.getAttribute("loginMember");

        int mediaId = mediaService.saveMedia(file, image);

        vote.setMediaId(mediaId);
        vote.setId(loginMember.getId());
        vote.setName(loginMember.getName());

        voteService.createVoteAgenda(vote);

        return "redirect:/home.do";
    }

    @GetMapping("/vote/detail/{voteId}")
    public String showVoteDetails(@PathVariable int voteId, Model model, HttpSession session) {
        Student loginMember = (Student) session.getAttribute("loginMember");
        boolean isVoted = false;
        Vote vote = voteService.getVoteById(voteId);

        if( voteService.checkVoteById(loginMember.getId(), voteId) == 1) {
            // 투표 한 경우
            isVoted = true;
            System.out.println("투표한놈임");
        }

        Media media = new Media();
        if(mediaService.getMediaById(vote.getMediaId()) != null){
            media = mediaService.getMediaById(vote.getMediaId());
        }
        model.addAttribute("media", media);
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
        Media media = mediaService.getMediaById(vote.getMediaId());

        model.addAttribute("media", media);
        model.addAttribute("isVoted", isVoted);
        model.addAttribute("vote", vote);
        return "vote/vote_detail_tact";  // 투표 페이지로 이동하는 Thymeleaf 템플릿 이름
    }

    @GetMapping("/vote/createUrl/{voteId}")
    public String createVoteUrl(@PathVariable int voteId, Model model){
        String voteUrl = voteService.generateToken(voteId);
        return "redirect:/vote/detail/" + voteId;
    }

    @PostMapping("/vote/cast/{voteId}")
    public String castVote(@PathVariable int voteId, @RequestParam String voteOption, @RequestParam String anonymityType, HttpSession session){
        Student loginMember = (Student) session.getAttribute("loginMember");
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
        if(anonymityType.equals("기명")){
            voteService.castVote(voteId, loginMember.getId(), loginMember.getName(),Integer.parseInt(voteOption));
            voteService.insertAttendHistory(voteId, loginMember.getId());
        }else {
            voteService.castVote(voteId, loginMember.getId(), null,Integer.parseInt(voteOption));
            voteService.insertAttendHistory(voteId, loginMember.getId());
        }
        Vote vote = voteService.getVoteById(voteId);
        return "redirect:/tactVote/detail/" + vote.getQrToken();
    }

    @GetMapping("/vote/delete/{voteId}")
    public String deleteVote(@PathVariable int voteId){
        voteService.deleteVote(voteId);
        return "redirect:/home.do";
    }

    @GetMapping("/vote/update/{voteId}")
    public String goUpdateVotePage(@PathVariable int voteId, Model model){
        Vote vote = voteService.getVoteById(voteId);
        Media media = mediaService.getMediaById(vote.getMediaId());
        model.addAttribute("vote", vote);
        model.addAttribute("media", media);
        return "vote/update_vote";
    }

    @PostMapping("/vote/update/{voteId}")
    public String updateVote(
            @PathVariable int voteId,
            @ModelAttribute("vote") Vote vote,
            @RequestParam("image") MultipartFile image,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        vote.setVoteId(voteId); // 경로 변수로부터 투표 ID를 받아와 설정합니다.


        mediaService.updateVoteMedia(vote.getMediaId(), file, image, voteId);
        voteService.updateVote(vote); // 서비스를 통해 업데이트 메서드를 호출합니다.



        return "redirect:/home.do";
    }
}
