package com.app.happybox.entity.file;

import com.app.happybox.entity.customer.InquiryAnswer;
import com.app.happybox.type.FileRepresent;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = "inquiryAnswer", callSuper = true)
@Table(name = "TBL_INQUIRY_ANSWER_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryAnswerFile extends Files {
    @Enumerated(EnumType.STRING)
    private FileRepresent fileRepresent;

    @ManyToOne(fetch = FetchType.LAZY)
    private InquiryAnswer inquiryAnswer;

    @Builder
    public InquiryAnswerFile(String filePath, String fileUuid, String fileOrgName, FileRepresent fileRepresent) {
        super(filePath, fileUuid, fileOrgName);
        this.fileRepresent = fileRepresent;
    }

    public void setInquiryAnswer(InquiryAnswer inquiryAnswer) {
        this.inquiryAnswer = inquiryAnswer;
    }
}
