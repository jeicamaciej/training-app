package jd.jeicam.trainingapp.product;

import jd.jeicam.trainingapp.calories_calculator.Meal;
import jd.jeicam.trainingapp.calories_calculator.MealRepository;
import jd.jeicam.trainingapp.user.User;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;
    private final UserRepository userRepository;
    private final MealRepository mealRepository;

    private final static String PRODUCT_NAME = "product_name";
    private final static String KCAL = "nutriments.energy-kcal_100g";
    private final static String CARBS = "nutriments.carbohydrates_100g";
    private final static String PROTEINS = "nutriments.proteins_100g";
    private final static String FATS = "nutriments.fat_100g";
    private final static String BRANDS = "brands";

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllByIdIn(List<String> ids) {
        return productRepository.findAllByIdIn(ids);
    }

    public Product findByProductName(String productName) {
        return productRepository.findByProductName(productName).get(0);
    }

    private Query createMongoQuery(String productName) {
        return new Query().addCriteria(new Criteria()
                .andOperator(Criteria.where(PRODUCT_NAME)
                                .regex(Pattern.compile(productName, Pattern.CASE_INSENSITIVE)),
                        Criteria.where(KCAL).ne(null),
                        Criteria.where(CARBS).ne(null),
                        Criteria.where(PROTEINS).ne(null),
                        Criteria.where(FATS).ne(null)));
    }

    private void setQueryFields(Query query) {
        query.fields()
                .include(PRODUCT_NAME)
                .include(BRANDS)
                .include(KCAL)
                .include(CARBS)
                .include(PROTEINS)
                .include(FATS);
    }

    List<Product> findByProductNameCustom(String productName) {
        Query query = createMongoQuery(productName);
        setQueryFields(query);
        return mongoTemplate.find(query, Product.class);
    }

    @Transactional
    public User addProductToUser(String username, String productId) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        user.getProducts().add(productId);
        return userRepository.save(user);
    }

    @Transactional
    public List<Product> getUserProducts(String username) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        return productRepository.findAllByIdIn(user.getProducts());
    }

    @Transactional
    public Meal addProductToMeal(String productId, long mealId, String username) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(IllegalArgumentException::new);
        meal.getProducts().add(productId);
        addProductToUser(username, productId);
        return mealRepository.save(meal);
    }

    @Transactional
    public List<Product> getProductFromMeal(long mealId){
        Meal meal = mealRepository.findById(mealId).orElseThrow(IllegalArgumentException::new);
        return findAllByIdIn(meal.getProducts());
    }
}
