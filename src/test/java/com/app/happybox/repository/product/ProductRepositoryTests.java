package com.app.happybox.repository.product;

import com.app.happybox.entity.file.ProductFile;
import com.app.happybox.entity.product.Product;
import com.app.happybox.repository.user.DistributorRepository;
import com.app.happybox.type.FileRepresent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductFileRepository productFileRepository;

    @Autowired
    private DistributorRepository distributorRepository;

    @Test
    public void saveTest() {
        // given
        distributorRepository.findById(120L).ifPresent(distributor -> {
            Product product = new Product("ㄳ", 2_800, distributor);

            product.setProductStock(2_000L);
//        distributorRepository.findById(120L).ifPresent(distributor -> {
//            Product product = new Product("카스", 4_000, distributor);
//            product.setProductStock(5_000L);
            productRepository.save(product);
        });

        // when

        // then
    }

    @Test
    public void fileSaveTest() {
        Product product = productRepository.findById(155L).get();
        ProductFile productFile = new ProductFile("2023/05/22", UUID.randomUUID().toString(), "상품 이미지", product, FileRepresent.REPRESENT);

        productFileRepository.save(productFile);
    }

    @Test
    public void find2RandomProducts_QueryDSL_Test(){
        List<Product> randomProducts = productRepository.findRandomProducts_QueryDSL();

        log.info(randomProducts.toString());
    }

//    @Test
//    public void findTop8WithDistributorAndReviewCountOrderByDate_QueryDSL_Test() {
//        // given
//        productRepository
//                .findTop8WithDistributorAndReviewCountOrderByDate_QueryDSL()
//                .stream()
//                .map(Product::toString)
//                .forEach(log::info);
//        // when
//
//        // then
//    }
//
//    @Test
//    public void findByIdWithDetail_QueryDSL_Test() {
//        // given
//        Optional<Product> product = productRepository.findByIdWithDetail_QueryDSL(9L);
//
//        // when
//
//        // then
//        product.map(Product::toString).ifPresent(log::info);
//    }
//
//    @Test
//    public void findAllByProductSearch_QueryDSL(){
//        // given
//        ProductSearchDTO productSearchDTO = new ProductSearchDTO();
//
////        productSearch.setAddress("경남");
////        productSearch.setName("사과");
////        productSearch.setProductSearchOrder(ProductSearchOrder.PRICE_DESC);
//
//        // when
//        Page<Product> products = productRepository.findAllByProductSearch_QueryDSL(PageRequest.of(0, 10), productSearchDTO);
//
//        // then
//        products.get().map(Product::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findAllByDistributorIdWithPaging_QueryDSL_Test() {
//        productRepository.findAllByDistributorIdWithPaging_QueryDSL(PageRequest.of(0, 5), 42L)
//                .stream().map(Product::toString).forEach(log::info);
//    }
}