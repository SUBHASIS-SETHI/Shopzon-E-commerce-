package com.example.Shopzon.Transformer;

import com.example.Shopzon.DTO.Request.SellerRequest;
import com.example.Shopzon.DTO.Response.SellerResponse;
import com.example.Shopzon.Model.Seller;

public class SellerTransformer {
    public static Seller sellerRequestToSeller(SellerRequest sellerRequest){
       return Seller.builder()
                .name(sellerRequest.getName())
                .email(sellerRequest.getEmail())
                .panNo(sellerRequest.getPanNo())
                .build();
    }

    public static SellerResponse sellerToSellerResponse(Seller seller) {
       return SellerResponse.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .panNo(seller.getPanNo())
                .build();
    }
}
