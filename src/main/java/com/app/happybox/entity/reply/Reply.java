package com.app.happybox.entity.reply;

import com.app.happybox.audity.Period;
import com.app.happybox.entity.board.Board;
import com.app.happybox.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_REPLY")
@DynamicInsert
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @ToString(exclude = "replyLikes") @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends Period {
    @EqualsAndHashCode.Include
    @Id @GeneratedValue
    private Long id;
    /* 댓글 정보 */
    @NotNull
    private String replyContent;

    @ColumnDefault(value = "0")
    private Integer replyLikeCount;
    /* ======= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reply", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReplyLike> replyLikes = new ArrayList<>();

    public Reply(String replyContent, User user) {
        this.replyContent = replyContent;
        this.user = user;
    }

    public void setReplyLikeCount(Integer replyLikeCount) {
        this.replyLikeCount = replyLikeCount;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
