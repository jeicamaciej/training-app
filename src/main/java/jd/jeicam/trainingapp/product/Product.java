package jd.jeicam.trainingapp.product;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "products")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @MongoId
    @Field(name = "_id")
    String id;

    @Field(name = "product_name")
    String productName;

    @Field(name = "brands")
    String brands;

    @Field(name = "nutriments.energy-kcal_100g")
    String kcal;

    @Field(name = "nutriments.carbohydrates_100g")
    String carbs;

    @Field(name = "nutriments.proteins_100g")
    String proteins;

    @Field(name = "nutriments.fat_100g")
    String fats;
}
