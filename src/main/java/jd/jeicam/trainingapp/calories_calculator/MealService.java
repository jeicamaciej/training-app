package jd.jeicam.trainingapp.calories_calculator;

import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.day.DayRepository;
import jd.jeicam.trainingapp.product.Product;
import jd.jeicam.trainingapp.product.ProductService;
import jd.jeicam.trainingapp.user.User;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final ProductService productService;
    private final UserRepository userRepository;
    private final DayRepository dayRepository;

    public Meal addMeal(@NotNull Meal meal) {
        return mealRepository.save(meal);
    }
    
    @Transactional
    public List<Product> getAllProductsFromMeal(long mealId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new IllegalArgumentException("meal does not exist"));
        return productService.findAllByIdIn(meal.getProducts());
    }

    @Transactional
    public Meal addEmptyMeal(Date date, String username) {
        long userId = getIdFromUsername(username);
        Day day = dayRepository.findByDateAndUserId(date, userId).orElseThrow(IllegalArgumentException::new);
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        Meal meal = new Meal();
        meal.setUser(user);
        meal.setDay(day);
        meal.setProducts(new ArrayList<>());
        user.getMeals().add(meal);
        return mealRepository.save(meal);
    }

    //:todo implement this method in user service and inject service
    private Long getIdFromUsername(String username) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        return user.getId();
    }

    public Optional<Meal> findById(Long id) {
        return mealRepository.findById(id);
    }

    public List<Meal> getAllMealsByDate(Date date, String username){
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        Day day = dayRepository.findByDateAndUserId(date, user.getId()).orElseThrow(IllegalArgumentException::new);
        return mealRepository.findAllByDayIdAndUserId(user.getId(), day.getId());
    }
}
