//package com.app.happybox.repository.subscript;
//
//import com.app.happybox.entity.subscript.Rider;
//import com.app.happybox.entity.user.Welfare;
//import com.app.happybox.repository.user.WelfareRepository;
//import com.app.happybox.service.subscript.RiderService;
//import com.app.happybox.type.DeliveryType;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.UUID;
//
//@SpringBootTest
//@Transactional @Rollback(false)
//@Slf4j
//public class RiderRepositoryTests {
//    @Autowired private RiderRepository riderRepository;
//    @Autowired private WelfareRepository welfareRepository;
//    @Autowired private RiderService riderService;
//
//    @Test
//    public void saveTest() {
//        Welfare welfare = welfareRepository.findById(26L).get();
//
//        for (int i = 0; i < 5; i++) {
//            Rider rider = new Rider("배달원_" + (i + 1), "01012341234" + i, "2023/05/01", UUID.randomUUID().toString(), "라이더 프로필 사진_" + (i + 1), welfare);
//            riderRepository.save(rider);
//        }
//    }
//
//    @Test
//    public void saveServiceTest() {
//        Welfare welfare = welfareRepository.findById(26L).get();
//
//        for (int i = 0; i < 5; i++) {
//            Rider rider = new Rider("라이더_" + (i + 1), "0102222123" + (i), "2023/05/01", UUID.randomUUID().toString(), "라이더 프로필 사진_" + (i + 1), welfare);
//            riderService.registerRiderByWelfareId(rider);
//        }
//    }
//
//    @Test
//    public void findAllByWelfareIdWithPaging_QueryDSL_Test() {
//        riderRepository.findAllByWelfareIdWithPaging_QueryDSL(PageRequest.of(0, 5), 26L)
//                .stream().forEach(rider -> {
//                    log.info(rider.getFileOrgName());
//                    log.info(rider.getFilePath());
//                    log.info(rider.getFileUuid());
//                    log.info(rider.getRiderName());
//                    log.info(rider.getRiderPhoneNumber());
//        });
//    }
//
//    @Test
//    public void updateDeliveryStatusById_Test() {
//        riderService.updateDeliveryStatusById(97L, DeliveryType.COMPLETED);
//    }
//}
