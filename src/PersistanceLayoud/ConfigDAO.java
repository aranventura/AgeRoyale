package PersistanceLayoud;

import BusinessLayer.Entities.ConfigJson;

/**
 * Interface that abstracts the persistence of groups from the rest of the code.
 *
 * In this case it takes data from the differents files we can have in the program, for this reason
 * we implements an abstract set of generic operations
 */
public interface ConfigDAO {

    /**
     * Method that reads a configuration file (entity: ConfigJson)
     * @param file The file we have to read
     * @return A class with all the information the file had
     */
    ConfigJson readFile (String file);
}
