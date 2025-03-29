package stepMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class SaveValueMethods extends BaseMethods{
    private static final Logger logger = LogManager.getLogger(SaveValueMethods.class);

    private static final HashMap<String,String> stringHashMap = new HashMap<>();

    public void saveStringValue(String stringValueName,String value){
        stringHashMap.put(stringValueName, value);
        logger.info("{} variable has been updated with the value: {}", stringValueName, value);
    }

    public String getStringValue(String stringValueName) {
        logger.info("The value of the registered variable {} is: {}", stringValueName, stringHashMap.get(stringValueName));
        return stringHashMap.get(stringValueName);
    }

}
