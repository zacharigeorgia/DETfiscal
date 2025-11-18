package gr.aueb.dmst.detFiscal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DataLoader{
    private ObjectMapper mapper;

    public DataLoader() {
        this.mapper = new ObjectMapper();
    }
    private JsonNode getRoot(String filePath) throws IOException {
        return mapper.readTree(new File(filePath)); //ψαχνει στο json
    }
    public List<Revenue> loadRevenues(String filePath) {  //μεθοδοσ επιστεφει την λιστα με τα εσοδα
        List<Revenue> list = new ArrayList<>(); //η λιστα μ εχει ονομα list
        try {
            JsonNode root = getRoot(filePath);
            JsonNode categories = root.path("revenues").path("categories"); // πλοηγηση μεσα στο json του λεω τι να παρει απο εκει
        if (categories.isArray()) {
                for (JsonNode node : categories) {  //για καθε ενα αντικειμενο node μεσα στην λιστα categories του json
                    Revenue r = new Revenue(); //δημιουργια αντικειμενου απο κλαση revenue
                    r.setName(node.path("name").asText());  //μετατροπη πεδιου name σε javastring που εκχωρειται στο αντικειμενο r
                    r.setAmount(node.path("amount").asDouble()); // το ιδιο αλλα για το ποσο
                    list.add(r); //προσθετω το εσοδο στην λιστα
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //εντοπισμος σφαλματος αμα πιασει το exception
        }
        return list;
    }
}