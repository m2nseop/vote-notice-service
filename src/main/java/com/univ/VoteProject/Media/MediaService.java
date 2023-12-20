package com.univ.VoteProject.Media;

import com.univ.VoteProject.Announce.AnnounceRepository;
import com.univ.VoteProject.Model.Media;
import com.univ.VoteProject.Vote.VoteRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaService {

    @Autowired
    MediaRepository mediaRepo;

    @Autowired
    VoteRepository voteRepo;

    @Autowired
    AnnounceRepository announceRepo;

    // file upload path
    @Value("${file.upload-dir}")
    private String uploadDir;

    public int saveMedia(MultipartFile file, MultipartFile image) throws IOException {
        Media media = new Media();
        String fileName = null;
        String imageName = null;
        if(file != null && !file.isEmpty()){
            fileName = getMediaName(file);
        }
        if(image != null && !image.isEmpty()){
            imageName = getMediaName(image);
        }

        media.setFile_name(fileName);
        media.setImage_name(imageName);

        // 실제 파일 저장
        mediaRepo.saveMedia(media);

        return media.getMediaId();
    }

    public void updateVoteMedia(int mediaId, MultipartFile file, MultipartFile image, int voteId) throws IOException {
        String fileName = null;
        String imageName = null;

        if(getMediaById(mediaId) == null){
            int m_id = saveMedia(file, image);
            voteRepo.updateVoteMediaId(voteId, m_id);
        } else{
            if(file != null && !file.isEmpty()){
                fileName = getMediaName(file);
            }
            if(image != null && !image.isEmpty()){
                imageName = getMediaName(image);
            }

            mediaRepo.updateMedia(mediaId, fileName, imageName);
        }
    }

    public void updateAnnounceMedia(int mediaId, MultipartFile file, MultipartFile image, int annId) throws IOException {
        String fileName = null;
        String imageName = null;

        if(getMediaById(mediaId) == null){ // 생성할때 파일을 업로드 안했을 때 media_record를 하나 만들어준다.
            int m_id = saveMedia(file, image);
            announceRepo.updateAnnounceMediaId(annId, m_id);
        } else{ // 생성할때 업로드했을 경우
            if(file != null && !file.isEmpty()){
                fileName = getMediaName(file);
            }
            if(image != null && !image.isEmpty()){
                imageName = getMediaName(image);
            }

            mediaRepo.updateMedia(mediaId, fileName, imageName);
        }
    }

    public String getMediaName(MultipartFile media) throws IOException {
        String mediaName = media.getOriginalFilename();
        String mediaPath = Paths.get(uploadDir, mediaName).toString();

        media.transferTo(new File(mediaPath));
        return mediaName;
    }

    public Media getMediaById(int mediaId){
        return mediaRepo.getMediaById(mediaId);
    }

}
