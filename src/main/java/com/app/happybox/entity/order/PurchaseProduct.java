package com.app.happybox.entity.order;

import com.app.happybox.audity.Period;
import lombok.*;

import javax.persistence.*;

/**
 * 주문 시 주문한 상품 내역 (주문 안에 여러 상품이 있기 때문)
 * */
@Entity @Table(name = "TBL_PURCHASE_PRODUCT")
@Getter @ToString @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseProduct extends Period {
    @EqualsAndHashCode.Include
    @Id @GeneratedValue
    private Long id;

    /* 주문한 상품 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    /* 주문 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Purchase purchase;
}