package com.univ.VoteProject.Announce;

import com.univ.VoteProject.Model.Announce;
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
        announce.setName(loginMember.getName());

        announceService.createAnnounceAgenda(announce);

        return "redirect:/home.do";
    }

    @GetMapping("/announce/detail/{annId}")
    public String showVoteDetails(@PathVariable int annId, Model model, HttpSession session) {
        Student loginMember = (Student) session.getAttribute("loginMember");
        // 여기서 투표 상세 정보를 데이터베이스 등에서 가져오는 로직을 추가
        Announce announce = announceService.getAnnounceById(annId);

        // 투표했는지 확인해서 해당 데이터 내용 가지고 감
        model.addAttribute("announce", announce);
        return "announce/announce_detail";  // 투표 상세 페이지의 Thymeleaf 템플릿 이름
    }

    @GetMapping("/announce/update/{annId}")
    public String goUpdateAnnouncePage(@PathVariable int annId, Model model) {
        // 여기서 투표 상세 정보를 데이터베이스 등에서 가져오는 로직을 추가
        Announce announce = announceService.getAnnounceById(annId);

        // 투표했는지 확인해서 해당 데이터 내용 가지고 감
        model.addAttribute("announce", announce);
        return "announce/update_announce";  // 투표 상세 페이지의 Thymeleaf 템플릿 이름
    }

    @PostMapping("/announce/update/{annId}")
    public String updateAnnounce(@PathVariable int annId, @ModelAttribute("announce") Announce announce){
        announce.setAnnId(annId); // 경로 변수로부터 투표 ID를 받아와 설정합니다.
        announceService.updateAnnounce(announce); // 서비스를 통해 업데이트 메서드를 호출합니다.

        return "redirect:/home.do";
    }

    @GetMapping("/announce/delete/{annId}")
    public String deleteAnnounce(@PathVariable int annId){
        announceService.deleteAnnounce(annId);

        return "redirect:/home.do";
    }
}
