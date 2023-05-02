package com.app.happybox.entity.order;

import com.app.happybox.entity.subscript.Subscription;
import com.app.happybox.entity.user.Member;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@DiscriminatorValue("SUBSCRIPTION")
@Getter @ToString(exclude = {"member", "subscript"}, callSuper = true) @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSubscription extends Order {

    /* 구독상품 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Subscription subscription;

    /* 구독 상태 */
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'SUBSCRIBED'")
    @NotNull
    private SubscriptStatus subscriptStatus;

}