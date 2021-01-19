package jd.jeicam.trainingapp.product;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.calories_calculator.Meal;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@CrossOrigin(origins = "*")
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
    ResponseEntity<List<Product>> findByProductNameCustomQuery(@RequestParam(name = "productName") String productName){
        return ResponseEntity.ok(productService.findByProductNameCustom(productName));
    }

    @PostMapping("{mealId}/add/{productId}")
    @JsonView(Meal.JsonViews.Get.class)
    ResponseEntity<Meal> addProductToMeal(@PathVariable String productId, @PathVariable long mealId, String username){
        return ResponseEntity.ok(productService.addProductToMeal(productId, mealId, username));
    }

    @GetMapping("/searchp/")
    ResponseEntity<Page<Product>> searchProductsWithPaging(@RequestParam(name = "productName") String productName, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size){
        return ResponseEntity.ok(productService.findByProductNameCustomWithPaging(productName, page, size));
    }
}
