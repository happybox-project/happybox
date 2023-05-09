package com.app.happybox.service.subscript;

import com.app.happybox.domain.AddressDTO;
import com.app.happybox.entity.subscript.*;
import com.app.happybox.entity.user.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface SubscriptionService {

//    최신 8개 조회
    public List<SubscriptionDTO> findRecentTop8();

//    주문 많은순 N개 조회
    public List<SubscriptionDTO> findByOrderCount(Long limit);

//    리뷰 많은순 N개 조회
    public List<SubscriptionDTO> findByReviews(Long limit);

    default SubscriptionDTO subscriptionToDTO(Subscription subscription, List<FoodDTO> foodList) {
        return SubscriptionDTO.builder()
                .id(subscription.getId())
                .orderCount(subscription.getOrderCount())
                .reviewAvgRating(subscription.getReviewAvgRating())
                .reviewCount(subscription.getReviewCount())
                .subscriptionPrice(subscription.getSubscriptionPrice())
                .subscriptionTitle(subscription.getSubscriptionTitle())
                .subscriptLikeCount(subscription.getSubscriptLikeCount())
                .welfareAddress(addressToDTO(subscription.getWelfare().getAddress()))
                .welfareName(subscription.getWelfare().getWelfareName())
                .foodList(foodList)
                .build();
    }

    default List<SubscriptionDTO> collectFoodList(List<Subscription> subscriptions, List<FoodCalendar> foodCalendarList) {
        return subscriptions.stream().map(subscription -> {
            List<FoodDTO> foods = new ArrayList<>();
            foodCalendarList.stream()
                    .filter(foodCalendar -> subscription.getFoodCalendars().contains(foodCalendar))
                    .forEach(foodCalendar -> foodCalendar.getFoodList().forEach(food -> foods.add(foodToDTO(food))));

            return subscriptionToDTO(subscription, foods);
        }).collect(Collectors.toList());
    }

    default FoodDTO foodToDTO(Food food) {
        return FoodDTO.builder()
                .id(food.getId())
                .fileOrgName(food.getFileOrgName())
                .filePath(food.getFilePath())
                .fileUuid(food.getFileUuid())
                .foodName(food.getFoodName())
                .build();
    }

    default AddressDTO addressToDTO(Address address) {
        return AddressDTO.builder()
                .firstAddress(address.getFirstAddress())
                .addressDetail(address.getAddressDetail())
                .zipcode(address.getZipcode())
                .build();
    }
}