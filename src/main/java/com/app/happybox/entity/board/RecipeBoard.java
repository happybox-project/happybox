package com.app.happybox.entity.board;

import com.app.happybox.service.reply.RecipeBoardReply;
import com.app.happybox.entity.user.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_RECIPE_BOARD")
@DynamicInsert
@Getter @ToString(exclude = {"recipeBoardReplies", "recipeBoardLikes"}, callSuper = true) @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeBoard extends Board {

    /* 레시피 게시판 정보 */
    @ColumnDefault(value = "0")
    private Integer recipeLikeCount;

    /* 댓글 갯수 */
    @ColumnDefault(value="0")
    private Integer recipeBoardReplyCount;
    /* ============= */

    /* 작성한 유저 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    /* 게시글 댓글 List */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipeBoard", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<RecipeBoardReply> recipeBoardReplies = new ArrayList<>();

    /* 게시글 좋아요 List */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipeBoard", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<RecipeBoardLike> recipeBoardLikes = new ArrayList<>();

    public RecipeBoard(String boardTitle, String boardContent) {
        super(boardTitle, boardContent);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setRecipeLikeCount(Integer recipeLikeCount) {
        this.recipeLikeCount = recipeLikeCount;
    }

    public void setRecipeBoardReplyCount(Integer recipeBoardReplyCount) {
        this.recipeBoardReplyCount = recipeBoardReplyCount;
    }
}
