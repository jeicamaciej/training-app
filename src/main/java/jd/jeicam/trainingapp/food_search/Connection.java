package jd.jeicam.trainingapp.food_search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Connection implements CommandLineRunner {

    private final static String URL_BEGGINING = "https://world.openfoodfacts.org/cgi/search.pl?action=process&search_terms=";
    private final static String URL_END = "&search_simple=1&action=process&json=true";
    private final static String NUTRIMENTS_FIELD_NAME = "nutriments";
    private final static String KCAL_FIELD_NAME = "energy-kcal_100g";
    private final static String CARBS_FIELD_NAME = "carbohydrates_100g";
    private final static String FATS_FIELD_NAME = "fat_100g";
    private final static String PROTEINS_FIELD_NAME = "proteins_100g";
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static String TEST_VALUE = "cola";

    @SneakyThrows
    private com.fasterxml.jackson.databind.JsonNode getResponse() {
        HttpResponse<com.mashape.unirest.http.JsonNode> response = Unirest.get(URL_BEGGINING + TEST_VALUE + URL_END).asJson();
        return OBJECT_MAPPER.readTree(response.getBody().toString());

    }

    private String readNutritionInfo(String fieldName, com.fasterxml.jackson.databind.JsonNode node) {
        if (node.has(fieldName)) {
            return node.get(fieldName).toPrettyString();
        }
        return null;
    }

    public void getInfo() {

        com.fasterxml.jackson.databind.JsonNode jsonNode = getResponse();

        jsonNode.get("products").forEach(p -> {
            String name = this.readNutritionInfo("product_name", p);
            String kcal = this.readNutritionInfo("energy-kcal_100g", p.get(NUTRIMENTS_FIELD_NAME));
            String carbs = this.readNutritionInfo("carbohydrates_100g", p.get(NUTRIMENTS_FIELD_NAME));
            String fats = this.readNutritionInfo("fat_100g", p.get(NUTRIMENTS_FIELD_NAME));
            String proteins = this.readNutritionInfo("proteins_100g", p.get(NUTRIMENTS_FIELD_NAME));

            System.out.println(name + " " + kcal + " " + carbs + " " + fats + " " + proteins);
        });
    }

    @Override
    public void run(String... args) {
        //:todo in case mongo dies
        //getInfo();
    }
}
