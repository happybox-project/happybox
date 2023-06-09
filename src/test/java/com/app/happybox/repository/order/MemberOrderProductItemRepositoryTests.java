package com.app.happybox.repository.order;

import com.app.happybox.domain.MemberOrderProductItemDTO;
import com.app.happybox.domain.SearchDateDTO;
import com.app.happybox.entity.order.MemberOrderProductItem;
import com.app.happybox.service.order.MemberOrderProductItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
public class MemberOrderProductItemRepositoryTests {
    @Autowired private MemberOrderProductItemRepository memberOrderProductItemRepository;
    @Autowired private MemberOrderProductItemService memberOrderProductItemService;


    @Test
    public void findOrderListTest() {
        SearchDateDTO searchDateDTO = new SearchDateDTO();
        searchDateDTO.setSetDate(LocalDateTime.of(2023, 05, 05, 0, 0));
        memberOrderProductItemRepository.findOrderListByMemberIdAndSearchDateDescWithPaging_QueryDSL(
                PageRequest.of(0, 5), 1L, searchDateDTO
        ).stream().forEach(v -> {
            log.info(v.getOrderAmount() + "");
            log.info(v.getCreatedDate() + "");
            log.info(v.getProduct().getProductName() + "");
        });
    }

    @Test
    public void findSaleListByWelfareIdAndSearchDateDescWithPagingTest() {
        SearchDateDTO searchDateDTO = new SearchDateDTO();

        searchDateDTO.setSetDate(LocalDateTime.of(2022, 02, 8, 00, 00));
        memberOrderProductItemRepository
                .findSaleListByDistributorIdAndSearchDateDescWithPaging_QueryDSL(PageRequest.of(1, 5), 120L, searchDateDTO)
                .stream().forEach(v -> log.info(v.getCreatedDate() + ""));
    }

    @Test
    public void findSaleCountByDistributorAndPurchaseStatus_QueryDSL_Test() {
        log.info("saleCount : " + memberOrderProductItemRepository.findSaleCountByDistributorAndPurchaseStatus_QueryDSL(120L));
    }
}