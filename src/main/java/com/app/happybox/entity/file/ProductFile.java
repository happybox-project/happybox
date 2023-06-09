package com.app.happybox.entity.file;

import com.app.happybox.entity.product.Product;
import com.app.happybox.type.FileRepresent;
import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "TBL_PRODUCT_FILE")
@Getter @ToString(exclude = "product",callSuper = true) @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductFile extends Files {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    //    파일이 대표 파일인지 여부
    @Enumerated(EnumType.STRING)
    private FileRepresent fileRepresent;

    @Builder
    public ProductFile(String filePath, String fileUuid, String fileOrgName, Product product, FileRepresent fileRepresent) {
        super(filePath, fileUuid, fileOrgName);
        this.product = product;
        this.fileRepresent = fileRepresent;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setFileRepresent(FileRepresent fileRepresent) {
        this.fileRepresent = fileRepresent;
    }
}
