package com.ayeshj.gapstar.service;

import com.ayeshj.gapstar.dto.ProductDTO;
import com.ayeshj.gapstar.model.ProductEntity;
import com.ayeshj.gapstar.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> fetchAllProducts(){
        List<ProductDTO> productDTOList = new ArrayList<>();
        productRepository.findAll().forEach(productEntity -> productDTOList.add(convert(productEntity)));
        return productDTOList;
    }



    public ProductDTO convert(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productEntity, productDTO);
        return productDTO;
    }


}
