package com.app.happybox.domain;

import com.app.happybox.type.SubOption;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @ToString
public class SubscriptionCartDTO {
    private Long id;

    private String subscriptionTitle;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Integer subscriptionPrice;

    private SubOption subOption;

    private Long subscriptionId;

    @Builder
    public SubscriptionCartDTO(Long id, String subscriptionTitle, LocalDateTime createdDate, LocalDateTime updatedDate, SubOption subOption,Integer subscriptionPrice ,Long subscriptionId) {
        this.id = id;
        this.subscriptionTitle = subscriptionTitle;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.subOption = subOption;
        this.subscriptionPrice = subscriptionPrice;
        this.subscriptionId = subscriptionId;
    }
}
