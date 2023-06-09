package com.app.happybox.repository.board;

import com.app.happybox.entity.board.ReviewBoard;
import com.app.happybox.entity.user.Member;
import com.app.happybox.entity.user.User;

public interface ReviewBoardLikeQueryDsl {
    public boolean checkMemberLikesReviewBoard_QueryDSL(Long reviewBoardId, Long memberId);
    public void deleteUserLikeByUserAndReviewBoard(Long reviewBoardId, Long memberId);
}
