package com.app.happybox.repository.inquiry;

import com.app.happybox.domain.InquiryAnswerDTO;
import com.app.happybox.domain.InquiryDTO;
import com.app.happybox.entity.customer.Inquiry;
import com.app.happybox.entity.customer.InquiryAnswer;
import com.app.happybox.entity.file.InquiryFile;
import com.app.happybox.type.FileRepresent;
import com.app.happybox.type.InquiryType;
import com.app.happybox.entity.user.User;
import com.app.happybox.repository.user.MemberRepository;
import com.app.happybox.service.cs.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
public class InquiryRepositoryTests {
    @Autowired private InquiryRepository inquiryRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private InquiryFileRepository inquiryFileRepository;
    @Autowired private InquiryService inquiryService;
    @Autowired private InquiryAnswerRepository inquiryAnswerRepository;

    @Test
    public void saveTest() {
        InquiryType[] inquiryTypes = {InquiryType.ORDER, InquiryType.CANCEL, InquiryType.SITE, InquiryType.USE, InquiryType.ETC};
        for (int i = 0; i < 3; i++) {
            Inquiry inquiry = new Inquiry("문의 제목" + (i + 1), "문의 내용" + (i + 1), inquiryTypes[new Random().nextInt(inquiryTypes.length)]);
            memberRepository.findById(3L).ifPresent(member -> inquiry.setUser((User)member));

            inquiryRepository.save(inquiry);
        }
    }

    @Test
    public void saveAnswerTest() {
        InquiryAnswer inquiryAnswer = new InquiryAnswer("문의 답변_내용", inquiryRepository.findById(2L).get());
        inquiryAnswerRepository.save(inquiryAnswer);
    }

    @Test
    public void fileSaveTest() {
        Inquiry inquiry = inquiryRepository.findById(204L).get();

        InquiryFile inquiryFile1 = new InquiryFile("2023/05/16", UUID.randomUUID().toString(), "문의사항1.png", FileRepresent.REPRESENT);
        InquiryFile inquiryFile2 = new InquiryFile("2023/05/16", UUID.randomUUID().toString(), "문의사항2.png", FileRepresent.ORDINARY);
        InquiryFile inquiryFile3 = new InquiryFile("2023/05/16", UUID.randomUUID().toString(), "문의사항3.png", FileRepresent.ORDINARY);

        List<InquiryFile> inquiryFiles = new ArrayList<>(Arrays.asList(inquiryFile1, inquiryFile2, inquiryFile3));

        inquiryFile1.setInquiry(inquiry);
        inquiryFile2.setInquiry(inquiry);
        inquiryFile3.setInquiry(inquiry);

        inquiryFileRepository.save(inquiryFile1);
        inquiryFileRepository.save(inquiryFile2);
        inquiryFileRepository.save(inquiryFile3);
    }

    @Test
    public void findInquiryListByMemberIdWithPaging_QueryDSLTest() {
        inquiryRepository.findInquiryListByMemberIdWithPaging_QueryDSL(
                PageRequest.of(1, 5), 1L).stream().forEach(v -> {
            log.info(v.getInquiryTitle());
            log.info(v.getInquiryContent());
            log.info(v.getInquiryFiles().toString());
        });
    }

    @Test
    public void findInquiryByInquiryId_QueryDSLTest() {
        log.info(inquiryRepository.findInquiryByInquiryId_QueryDSL(2L).get().toString());
    }

    @Test
    public void findInquiryListWithPaging_QueryDSL_Test() {
        inquiryRepository.findInquiryListWithPaging_QueryDSL(PageRequest.of(0, 10))
                .stream().forEach(inquiry -> {
                    log.info(inquiry.getInquiryContent());
                    log.info(inquiry.getInquiryTitle());
                    log.info(inquiry.getInquiryFiles().toString());
        });
    }

    @Test
    public void getInquiryListByIdTest() {
        PageRequest page = PageRequest.of(0, 5);
        inquiryService.getInquiryListById(page, 84L).stream().map(InquiryDTO::toString).forEach(log::info);
    }

    @Test
    public void getInquiryAnswerListByMemberId_Test() {
        inquiryService.getInquiryAnswerListByUserId(1L)
                .stream().map(InquiryAnswerDTO::toString).forEach(log::info);
    }


    @Test
    public void 후() {
//        inquiryRepository.findAll().stream().forEach(v -> log.info(v.toString()));
        inquiryRepository.findInquiryListByMemberIdWithPaging_QueryDSL(PageRequest.of(0, 10), 1L)
                .stream().forEach(v -> log.info(v.toString()));

//        inquiryService.getListByMemberId(PageRequest.of(2, 5), 1L)
//                .stream().map(InquiryDTO::toString).forEach(log::info);
    }
}
