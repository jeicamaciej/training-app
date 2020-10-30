package jd.jeicam.trainingapp.product;

import jd.jeicam.trainingapp.calories_calculator.Meal;
import jd.jeicam.trainingapp.calories_calculator.MealRepository;
import jd.jeicam.trainingapp.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;

    @Test
    void findById() {
        long timeMillis = System.currentTimeMillis();
        productService.findById("3220440176203").get();
        System.out.println("czas id: " + (System.currentTimeMillis() - timeMillis));
    }

    @Test
    void findByProductName() {
        long timeMillis = System.currentTimeMillis();
        System.out.println(productService.findByProductName("La Laitière Lait d'Amande pépites façon calisson"));
        System.out.println("czas product_name: " + (System.currentTimeMillis() - timeMillis));
    }

    @Test
    void findByProductNameCustom() {
        long timeMillis = System.currentTimeMillis();
        System.out.println(productService.findByProductNameCustom("mleko"));
        System.out.println("czas lista: " + (System.currentTimeMillis() - timeMillis));
    }

    @Test
    void addProductToUser() {
        productService.addProductToUser("a", "3220440176203");
    }

    @Test
    void getUserProducts() {
        System.out.println(productService.getUserProducts("a"));
    }

    @Test
    @Transactional
    void addProductToMeal() {
        String productId = "3220440176203";
        productService.addProductToMeal(productId, 10869L, "a");
        Meal meal = mealRepository.findById(10869L).orElseThrow(IllegalArgumentException::new);
        assertTrue(meal.getProducts().contains(productId));
        System.out.println("\n\n" + meal.getProducts().get(0) + "\n\n");
    }

    @Test
    void getProductFromMeal() {
        String productId = "01223004";
        productService.addProductToMeal(productId, 10869L, "a");
        List<Product> products = productService.getProductFromMeal(10869L);
        Product product = products.get(0);
        assertEquals(product.getId(), "01223004");
        assertEquals(product.getProductName(), "Cola");
        assertEquals(product.getBrands(), "Pepsi");
        System.out.println(product);
    }
}