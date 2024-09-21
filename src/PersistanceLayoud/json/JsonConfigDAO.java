package PersistanceLayoud.json;

import BusinessLayer.Entities.ConfigJson;
import PersistanceLayoud.ConfigDAO;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Clase que implementa los metodos de {@link ConfigDAO} interficie.
 *
 * Implementa la lectura del fichero json
 */
public class JsonConfigDAO implements ConfigDAO {

    @Override
    public ConfigJson readFile(String file) {
        ConfigJson config = null;
        try {
            JsonReader br = new JsonReader(new FileReader(file));
            Gson gson = new Gson();
            config = gson.fromJson(br, ConfigJson.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return config;
    }
}
