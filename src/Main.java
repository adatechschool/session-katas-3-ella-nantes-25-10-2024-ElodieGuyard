import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JSONRanger();
        System.out.println(CompterLespokemons());
        //je commenterai plus tard flem dsl
    }

    public static JSONArray JSONParser(){
        String filePath = "assets/Pokedex.json";
        JSONArray All;
        try {
            FileReader reader = new FileReader(filePath);
            Object o = new JSONParser().parse(reader);

            JSONObject jsonObject = (JSONObject) o;
            All = (JSONArray) jsonObject.get("pokemon");

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        } return All;
    }

    public static void JSONRanger() {

        JSONArray AllPokemons = JSONParser();
        String unNom;
        String Id;
        for (int i = 0; i < AllPokemons.size(); i++) {
            JSONObject currentPokemon = (JSONObject) AllPokemons.get(i);
            unNom = (String) currentPokemon.get("name");
            Id = currentPokemon.get("num").toString();

            Pokedex monPokemon = new Pokedex(Id,unNom);
            System.out.println(monPokemon.ID);
            System.out.println(monPokemon.nom);
        }
    }

    public static String CompterLespokemons(){
        int nbPokemon;
        JSONArray AllPokemons = JSONParser();
        nbPokemon = AllPokemons.size();
        return "Il y a : " + nbPokemon + " pokémons dans la liste";
    }
}