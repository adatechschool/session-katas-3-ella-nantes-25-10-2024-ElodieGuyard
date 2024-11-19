import org.json.simple.JSONArray; //import depuis la librairie simple la classe JSONArray
import org.json.simple.JSONObject; //import depuis la librairie simple la classe JSONObject
import org.json.simple.parser.JSONParser; //import depuis la librairie simple la classe JSONParser
import org.json.simple.parser.ParseException; //import depuis la librairie simple la classe ParseException

import java.io.FileReader; //import de la classe FileReader
import java.io.IOException; //import de la classe IOException

public class Main {
    public static void main(String[] args) {

        JSONRangeur();                                  //Appel de la méthode JSONRangeur qui permet de creer un objet pokemon par itération
        System.out.println(CompterLespokemons());       //print la valeur de retour de la méthode CompterLespokemons
    }

    public static JSONArray PokedexParseur(){
        String filePathPokedex = "assets/Pokedex.json";
        JSONArray AllPokemons;
        try { //permet la gestion des erreurs
            FileReader reader = new FileReader(filePathPokedex); //créer un objet nommé reader, cherche s'il existe ou non, trow une exception s'il y a un problème (FileNotFoundException par exemple), si tout va bien, lit les caractères du fichier (convertisseur bytes to chars )
            Object o = new JSONParser().parse(reader); // l'objet o contient à présent le contenu de ce que FileReader à trouvé dans le fichier, mais maintenant c'est un ensemble de clé valeurs que java peux traiter
            JSONObject jsonObject = (JSONObject) o; // (nom de variable vraiment pas explicite, risque de confondre le type et le nom) jsonObject contient la conversion de l'object o (la liste des pokemons de type objet) en liste de pokemon de type JSONObject
            AllPokemons = (JSONArray) jsonObject.get("pokemon"); // Enfin, on convertis la liste des pokemons de type jsonobject en son array pour qu'on puisse naviguer facilement dedans

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e); //informe dans la console en cas de problème dans la lecture du fichier ou dans le parsing
        } return AllPokemons; //valeur de retour de PokedexParseur qui contient la liste de tout les pokemons de type JSONArray
    }

    public static void JSONRangeur() {

        JSONArray AllPokemons = PokedexParseur();
        String nomPkm;
        String numPkm;
        String typePkm;

        for (int i = 0; i < AllPokemons.size(); i++) {
            JSONObject currentPokemon = (JSONObject) AllPokemons.get(i); // l'objet JSON est égale aux paires clés / valeurs présents à l'endroit i
            nomPkm = (String) currentPokemon.get("name"); //à l'endroit i du JSONArray, prendre la valeur de la clé "name" qui est de type string
            numPkm = currentPokemon.get("num").toString(); //à l'endroit i du JSONArray; prendre la valeur de la clé "num" convertie en string
            typePkm = currentPokemon.get("type").toString(); // à l'endroit i du JSONArray, prendre la valeur de la clé type (de type Array) convertie en string

            Pokemon monPokemon = new Pokemon(numPkm,nomPkm,typePkm); //création d'un pokemon grâce à l'objet pokemon
            System.out.println("Pokémon numéro = " + monPokemon.num + " | Nom = " + monPokemon.nom + " | Type = " + monPokemon.type); //utilisation de l'object créé pour print dans la console les informations qu'on a pu récupérer
            //note : monPokemon est écrasé à chaque itération.
        }
    }

    public static String CompterLespokemons(){
        int nbPokemon;
        JSONArray AllPokemons = PokedexParseur();                   //parse le pokedex.json, il semblerai que c'est parsé plusieurs fois dans le programme c'est pas malin
        nbPokemon = AllPokemons.size();                             //nbPokemon stocke la taille du tableau où il y a tout les pokemons
        return "Il y a : " + nbPokemon + " pokémons dans la liste";
    }
}