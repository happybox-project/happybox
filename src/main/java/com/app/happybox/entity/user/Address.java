package com.app.happybox.entity.user;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString @Setter
public class Address implements Serializable {
    /* kakao 주소 api 에 따라 변경해야함 */
    /* 상세주소를 밖으로 빼야할수도? */
    private String zipcode;
    private String firstAddress;
    private String addressDetail;

    @Builder
    public Address(String zipcode, String firstAddress, String addressDetail) {
        this.zipcode = zipcode;
        this.firstAddress = firstAddress;
        this.addressDetail = addressDetail;
    }
}
