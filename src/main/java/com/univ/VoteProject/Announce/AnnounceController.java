package com.univ.VoteProject.Announce;

import com.univ.VoteProject.Media.MediaService;
import com.univ.VoteProject.Model.Announce;
import com.univ.VoteProject.Model.Media;
import com.univ.VoteProject.Model.Student;
import com.univ.VoteProject.Model.Vote;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AnnounceController {
    @Autowired
    AnnounceService announceService;

    @Autowired
    MediaService mediaService;

    @GetMapping("/announce/create.do")
    public String createAnnounceForm(Model model){
        model.addAttribute("announce", new Announce());
        return "announce/create_announce";
    }

    @PostMapping("/announce/create.do")
    public String createAnnounceAgenda(@ModelAttribute Announce announce, @RequestParam("image") MultipartFile image, @RequestParam("file") MultipartFile file, HttpSession session)
            throws IOException {

        Student loginMember = (Student) session.getAttribute("loginMember");

        int mediaId = mediaService.saveMedia(file, image);

        announce.setMediaId(mediaId);
        announce.setId(loginMember.getId());
        announce.setName(loginMember.getName());

        announceService.createAnnounceAgenda(announce);

        return "redirect:/home.do";
    }

    @GetMapping("/announce/detail/{annId}")
    public String showVoteDetails(@PathVariable int annId, Model model, HttpSession session) {
        Student loginMember = (Student) session.getAttribute("loginMember");

        Announce announce = announceService.getAnnounceById(annId);

        Media media = mediaService.getMediaById(announce.getMediaId());

        model.addAttribute("media", media);
        model.addAttribute("announce", announce);
        return "announce/announce_detail";  // 투표 상세 페이지의 Thymeleaf 템플릿 이름
    }

    @GetMapping("/announce/update/{annId}")
    public String goUpdateAnnouncePage(@PathVariable int annId, Model model) {
        Announce announce = announceService.getAnnounceById(annId);

        Media media = mediaService.getMediaById(announce.getMediaId());
        model.addAttribute("media", media);
        model.addAttribute("announce", announce);
        return "announce/update_announce";  // 투표 상세 페이지의 Thymeleaf 템플릿 이름
    }

    @PostMapping("/announce/update/{annId}")
    public String updateAnnounce(
            @PathVariable int annId,
            @ModelAttribute("announce") Announce announce,
            @RequestParam("image") MultipartFile image,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        announce.setAnnId(annId);

        mediaService.updateAnnounceMedia(announce.getMediaId(), file, image, annId);
        announceService.updateAnnounce(announce);

        return "redirect:/home.do";
    }

    @GetMapping("/announce/delete/{annId}")
    public String deleteAnnounce(@PathVariable int annId){
        announceService.deleteAnnounce(annId);

        return "redirect:/home.do";
    }
}
