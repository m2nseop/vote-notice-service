package com.univ.VoteProject.Announce;

import com.univ.VoteProject.Model.Announce;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AnnounceRepository {
    void createAnnounceAgenda(Announce announce);

    Announce getAnnounceList();
}
