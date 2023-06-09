package com.app.happybox.service.product;

import com.app.happybox.domain.SubscriptionCartDTO;
import com.app.happybox.entity.product.*;
import com.app.happybox.entity.subscript.Subscription;
import com.app.happybox.entity.user.Member;

import java.util.List;

public interface SubscriptionCartService {

    public List<SubscriptionCartDTO> findAllByUserId(Long id);

    public Long saveCart(SubscriptionCartDTO cartDTO, Long memberId, Long subscriptionId);

//    유저 id로 카트 삭제 하기
    public void deleteCart(Long id);

    default SubscriptionCartDTO cartToDTO(SubscriptionCart subscriptionCart) {
        return SubscriptionCartDTO.builder()
                .id(subscriptionCart.getId())
                .subscriptionTitle(subscriptionCart.getSubscription().getSubscriptionTitle())
                .createdDate(subscriptionCart.getCreatedDate())
                .updatedDate(subscriptionCart.getUpdatedDate())
                .subOption(subscriptionCart.getSubOption())
                .subscriptionPrice(subscriptionCart.getSubscription().getSubscriptionPrice())
                .subscriptionId(subscriptionCart.getSubscription().getId())
                .build();
    }

    default SubscriptionCart subscriptionCartDTOToEntity(SubscriptionCartDTO cartDTO, Member member, Subscription subscription) {
        return SubscriptionCart.builder()
                .subscription(subscription)
                .member(member)
                .subOption(cartDTO.getSubOption())
                .build();
    }
}
