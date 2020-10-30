package jd.jeicam.trainingapp.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductName(String productName);
    List<Product> findAllByIdIn(List<String> productIds);
}
