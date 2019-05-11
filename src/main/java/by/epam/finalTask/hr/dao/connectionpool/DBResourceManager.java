package by.epam.finalTask.hr.dao.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;


public class DBResourceManager {
    private final static Logger LOGGER = LogManager.getLogger(DBResourceManager.class);
    private final static String PROPERTIES_FILE_PATH = "db";

    private static DBResourceManager instance;
    private ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_FILE_PATH);

    public static DBResourceManager getInstance() {
        if (instance == null) {
            instance = new DBResourceManager();
        }
        return instance;
    }

    public String getValue(String key) {
        LOGGER.info(key + " - " + bundle.getString(key));
        return bundle.getString(key);
    }
}
