package jd.jeicam.trainingapp.product;

import jd.jeicam.trainingapp.calories_calculator.Meal;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/get/{mealId}")
    ResponseEntity<List<Product>> getMealProducts(@PathVariable Long mealId){
        return ResponseEntity.ok(productService.getProductFromMeal(mealId));
    }

    @GetMapping("/all")
    ResponseEntity<List<Product>> getUserProducts(String username){
        return ResponseEntity.ok(productService.getUserProducts(username));
    }

    @GetMapping("/search")
    ResponseEntity<List<Product>> findByProductNameCustomQuery(@RequestParam String productName){
        return ResponseEntity.ok(productService.findByProductNameCustom(productName));
    }

    @PostMapping("{mealId}/add/{productId}")
    ResponseEntity<Meal> addProductToMeal(@PathVariable String productId, @PathVariable long mealId, String username){
        return ResponseEntity.ok(productService.addProductToMeal(productId, mealId, username));
    }
}
