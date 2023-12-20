package com.univ.VoteProject.Media;

import com.univ.VoteProject.Model.Media;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MediaRepository {
    void saveMedia(Media media);

    Media getMediaById(int mediaId);

    void updateMedia(int mediaId, String fileName, String imageName);
}
