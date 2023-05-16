package com.app.happybox.service.product;

import com.app.happybox.domain.AddressDTO;
import com.app.happybox.entity.file.ProductFile;
import com.app.happybox.entity.product.Product;
import com.app.happybox.domain.product.ProductDTO;
import com.app.happybox.entity.file.ProductFileDTO;
import com.app.happybox.domain.product.ProductSearchDTO;
import com.app.happybox.entity.user.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProductService {

//    최신 8개 조회
    public List<ProductDTO> findTop8Recent();

//    추천으로 조회
    public List<ProductDTO> findTop8ReplyCount();

//    상품 동적검색
    public Page<ProductDTO> findAllBySearch(Pageable pageable, ProductSearchDTO search);

//    상품 상세조회
    public ProductDTO findById(Long id);

//    관리자 해당 유통회원의 상품 목록
    public Page<ProductDTO> getListByDistributorId(Pageable pageable, Long distributorId);

//    관리자 상품 상세보기
    public Optional<Product> getDetailById(Long productId);

    default ProductDTO productToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .distributorName(product.getDistributor().getDistributorName())
                .productCategory(product.getProductCategory())
                .productLikeCount(product.getProductLikeCount())
                .productName(product.getProductName())
                .productOrderCount(product.getProductOrderCount())
                .productPrice(product.getProductPrice())
                .productReplyCount(product.getProductReplyCount())
                .productStock(product.getProductStock())
                .productFileDTOS(
                        product.getProductFiles().stream()
                                .map(this::productFileToDTO)
                                .collect(Collectors.toList())
                )
                .address(this.addressToDTO(product.getDistributor().getAddress()))
                .build();
    }

    default ProductFileDTO productFileToDTO(ProductFile file) {
        return ProductFileDTO.builder()
                .fileOrgName(file.getFileOrgName())
                .filePath(file.getFilePath())
                .fileRepresent(file.getFileRepresent())
                .fileUuid(file.getFileUuid())
                .id(file.getId())
                .build();
    }

    default ProductDTO adminProductToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .productCategory(product.getProductCategory())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productStock(product.getProductStock())
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
