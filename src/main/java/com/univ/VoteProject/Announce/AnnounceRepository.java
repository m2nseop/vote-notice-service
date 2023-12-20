package com.univ.VoteProject.Announce;

import com.univ.VoteProject.Model.Announce;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AnnounceRepository {
    void createAnnounceAgenda(Announce announce);

    List<Announce> getAnnounceList();

    Announce getAnnounceById(int annId);

    void updateAnnounce(Announce announce);

    void deleteAnnounce(int annId);

    void updateAnnounceMediaId(int annId, int mediaId);
}
