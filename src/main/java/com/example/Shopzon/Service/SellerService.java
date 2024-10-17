package com.example.Shopzon.Service;

import com.example.Shopzon.DTO.Request.SellerRequest;
import com.example.Shopzon.DTO.Response.SellerResponse;
import com.example.Shopzon.Exception.SellerDuplicateEntryFoundException;
import com.example.Shopzon.Model.Seller;
import com.example.Shopzon.Repository.SellerRepository;
import com.example.Shopzon.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public SellerResponse addSeller(SellerRequest sellerRequest) {

        Optional<Seller> sellerOptional=sellerRepository.findByEmail(sellerRequest.getEmail());
        if(!sellerOptional.isEmpty()){
            throw new SellerDuplicateEntryFoundException("Email already exists,Seller can't be added again");
        }

        Seller seller= SellerTransformer.sellerRequestToSeller(sellerRequest);
        Seller savedSeller=sellerRepository.save(seller);
        SellerResponse sellerResponse= SellerTransformer.sellerToSellerResponse(savedSeller);
        return sellerResponse;

    }
}
