package com.univ.VoteProject.Vote;

import com.univ.VoteProject.Model.Vote;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VoteRepository {
    void createVoteAgenda(Vote vote);

    List<Vote> getVoteList();
}
