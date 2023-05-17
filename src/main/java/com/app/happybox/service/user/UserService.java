package com.app.happybox.service.user;

import com.app.happybox.domain.AddressDTO;
import com.app.happybox.entity.user.Address;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
//    관리자 회원 삭제
    public void deleteByMemberId(Long userId);

    //    아이디중복체크
    public Boolean existsUserByUserId(String userId);

    //    이메일 중복체크
    public Boolean existsUserByUserEmail(String userEmail);

    //    휴대폰 중복체크
    public Boolean existsUserByUserPhoneNumber(String userPhoneNumber);

    //    id로 주소 조회
    public AddressDTO findAddressById(Long id);


    default Address toAddress(AddressDTO addressDTO){
        return Address.builder()
                .firstAddress(addressDTO.getFirstAddress())
                .addressDetail(addressDTO.getAddressDetail())
                .zipcode(addressDTO.getZipcode())
                .build();
    }

    default AddressDTO toAddressDTO(Address address){
        return AddressDTO.builder()
                .addressDetail(address.getAddressDetail())
                .firstAddress(address.getFirstAddress())
                .zipcode(address.getZipcode())
                .build();
    }



//    회원 탈퇴
    public void updateUserStatusByUserId(Long userId);
}
