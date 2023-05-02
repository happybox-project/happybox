package com.app.happybox.entity.payment;

import com.app.happybox.audity.Period;
import com.app.happybox.entity.order.Order;
import com.app.happybox.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity @Table(name = "TBL_PAYMENT")
@DynamicInsert @DynamicUpdate
@Getter @ToString(exclude = "user") @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends Period {
    @EqualsAndHashCode.Include
    @Id @GeneratedValue
    private Long id;

    @NotNull
    @ColumnDefault(value = "0")
    private Integer paymentAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Order order;
}