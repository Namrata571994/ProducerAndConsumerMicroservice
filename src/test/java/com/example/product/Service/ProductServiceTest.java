package com.example.product.Service;

import com.example.product.Entity.Product;
import com.example.product.Repository.ProductRepo;
import org.aspectj.weaver.patterns.IVerificationRequired;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.NotNull;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    ProductRepo productRepo;

    @InjectMocks
    ProductService productService;

    @Test
    public void getproductGreaterThanPriceTest(){

        Product p1=new Product();
        p1.setPrice(1);
        p1.setName("Watch");
        p1.setPrice(101);

        Product p2=new Product();
        p2.setPrice(2);
        p2.setName("Laptop");
        p2.setPrice(1000);

        List<Product> productList= Arrays.asList(p1,p2);

        //mock the repository
        Mockito.when(productRepo.findByPriceGreaterThan(100)).thenReturn(productList);

        List<Product> serviceresult=productService.getProductsGreaterthan(100);

        Assertions.assertNotNull(serviceresult);
        Assertions.assertEquals(2,serviceresult.size());

       // verify(productRepository, times(1)).findByPriceGreaterThan(100);
        Mockito.verify(productRepo,Mockito.times(1)).findByPriceGreaterThan(100);

    }
}
