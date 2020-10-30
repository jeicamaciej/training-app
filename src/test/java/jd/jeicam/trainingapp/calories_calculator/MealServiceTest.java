package jd.jeicam.trainingapp.calories_calculator;

import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.day.DayService;
import jd.jeicam.trainingapp.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MealServiceTest {

    @Autowired
    private MealService mealService;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private DayService dayService;
    @Autowired
    private UserRepository userRepository;

//    @Test
//    void addProductToMeal() {
//        String productId = "3220440176203";
//
//        Meal meal = new Meal();
//        meal.setId(88L);
//        meal.setProducts(new ArrayList<>());
//        mealRepository.saveAndFlush(meal);
//
//        mealService.addProductToMeal(88L, productId, "a");
//        System.out.println(mealService.getAllProductsFromMeal(88L));
//    }

    @Test
    void getAllProductsFromMeal() {
    }

    @Test
    void addEmptyMeal() {
        Day day = dayService.getDay(10867L);
        Meal meal = mealService.addEmptyMeal(day.getDate(), "a");
        System.out.println(meal.getId());
        System.out.println(meal.getUser().getId());
    }
}