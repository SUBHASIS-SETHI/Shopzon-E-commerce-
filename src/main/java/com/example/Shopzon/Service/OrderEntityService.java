package com.example.Shopzon.Service;

import com.example.Shopzon.DTO.Request.ItemRequest;
import com.example.Shopzon.DTO.Request.OrderEntityRequest;
import com.example.Shopzon.DTO.Response.OrderEntityResponse;
import com.example.Shopzon.Enum.ProductStatus;
import com.example.Shopzon.Exception.CustomerNotFoundException;
import com.example.Shopzon.Exception.ProductNotAvailableException;
import com.example.Shopzon.Model.Customer;
import com.example.Shopzon.Model.OrderEntity;
import com.example.Shopzon.Model.Product;
import com.example.Shopzon.Repository.CustomerRepository;
import com.example.Shopzon.Repository.OrderEntityRepository;
import com.example.Shopzon.Repository.ProductRepository;
import com.example.Shopzon.Transformer.OrderEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderEntityService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Autowired
    JavaMailSender javaMailSender;


    public OrderEntityResponse placeOrder(OrderEntityRequest orderEntityRequest) {
            Optional<Customer> optionalCustomer=customerRepository.findByEmail(orderEntityRequest.getCustomerEmail());
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Email is invalid");
        }

        Customer customer=optionalCustomer.get();
        List<Product> productsRequested=new ArrayList<>();
        double totalValue=0;

        for(ItemRequest itemRequest:orderEntityRequest.getItemRequestList()){
            Optional<Product> optionalProduct=productRepository.findById(itemRequest.getId());
            if(optionalProduct.isEmpty()){
                throw new ProductNotAvailableException("Sorry, the product id=" + itemRequest.getId()+" is invalid");
            }
            //if the product exists check is it under required quantity
            Product product=optionalProduct.get();
            //if not
            if(product.getQuantity()<itemRequest.getRequiredQuantity()){
                throw new ProductNotAvailableException("Sorry, the product with id = "+product.getId()+" is out of stock");
            }
            //if yes book order
            product.setQuantity(product.getQuantity()-itemRequest.getRequiredQuantity());
            if(product.getQuantity()==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCKS);
            }
            totalValue+=itemRequest.getRequiredQuantity()*product.getPrice();
            productsRequested.add(product);
        }
        // complete the order entity and save it to be continued
        OrderEntity order= OrderEntityTransformer.orderEntityRequestToOrderEntity(totalValue);
        order.setCustomer(customer);
        order.setProductList(productsRequested);
        OrderEntity savedOrder=orderEntityRepository.save(order);
        // complete product and customer entity and save them as well
        for(Product product:productsRequested){
            product.getOrderEntityList().add(savedOrder);
        }
        customer.getOrderEntityList().add(savedOrder);
        //save customer and product
        customerRepository.save(customer);
        productRepository.saveAll(productsRequested);

        //send email before returning
        sendEmail(savedOrder);

        return OrderEntityTransformer.orderEntityToOrderResponse(savedOrder);

    }

    private void sendEmail(OrderEntity savedOrder) {
        String text = "Hooray !!!, " + savedOrder.getCustomer().getName() + " your order is placed with total value = "+ savedOrder.getTotalValue();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("acciojobspring@gmail.com");
        message.setTo(savedOrder.getCustomer().getEmail());
        message.setSubject("Order Placed");
        message.setText(text);
        javaMailSender.send(message);
    }
}
