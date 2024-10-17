package com.example.Shopzon.Service;

import com.example.Shopzon.DTO.Request.ProductRequest;
import com.example.Shopzon.DTO.Response.ProductResponse;
import com.example.Shopzon.Exception.SellerNotFoundException;
import com.example.Shopzon.Model.Product;
import com.example.Shopzon.Model.Seller;
import com.example.Shopzon.Repository.ProductRepository;
import com.example.Shopzon.Repository.SellerRepository;
import com.example.Shopzon.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    SellerRepository sellerRepository;



    public ProductResponse addProduct(ProductRequest productRequest, String sellerEmail) {
        Optional<Seller> sellerOptional=sellerRepository.findByEmail(sellerEmail);
        if(sellerOptional.isEmpty()){
            throw new SellerNotFoundException("Invalid seller email");
        }
        Seller seller=sellerOptional.get();
        Product product= ProductTransformer.productRequestToProduct(productRequest);
        product.setSeller(seller);
        seller.getProductList().add(product);

        Seller savedSeller=sellerRepository.save(seller);
        int size=savedSeller.getProductList().size();
        Product savedProduct=savedSeller.getProductList().get(size-1);
        return ProductTransformer.productToProductResponse(savedProduct);



    }


}
