 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 *
 * @author nikolaev
 */
public class hash {
    
    private static final hash SINGLETON;
    private static final Logger LOG = Logger.getLogger(hash.class.getName());
    
    static 
    {
        SINGLETON = new hash();
    }
    
    //Извлечение объекта из коллекции
    public static Object get(String key)
    {
        return SINGLETON.fHashMap.get(key);
    }
    
    // Извлечение объекта из коллекции
    // при отсутствии данных возвращается значение по умолчанию
    public static Object get(String key, Object deflt)
    {
        Object obj = SINGLETON.fHashMap.get(key);
        if (obj == null)
        {
            return deflt;
        }
        else {
            return obj;
        }
    }

// Для упрощения извлечения данных типа int
    public static int getInt(String key, int deflt) {
        Object obj = SINGLETON.fHashMap.get(key);
        if (obj == null) {
            
            return deflt;
        } else {
            return Integer.parseInt(obj.toString());
    
  }
}
    
    // Для упрощения извлечения данных типа int, if Key = 0, then default return 0
    public static int getIntDef(String key) {
        Object obj = SINGLETON.fHashMap.get(key);
        if (obj == null) {
            
            return 0;
        } else {
            return Integer.parseInt(obj.toString());
    
  }
}

// Для упрощения извлечения данных типа char
    public static char getChar(String key, char deflt) {
        Object obj = SINGLETON.fHashMap.get(key);
        if (obj == null) {   
            return deflt;
    } 
    else {
    return  obj.toString().charAt(0);
  }
}

// Добавление объекта в коллекцию
    public static void put(String key, Object data)
    {
        //prevent null values. Hasmap allow them
        if (data == null)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            SINGLETON.fHashMap.put(key, data);
         }
    }

// Добавление объекта в коллекцию
    @SuppressWarnings("unchecked")
    public static void putStr(String key, String data) {
        //prevent null values. Hasmap allow them
        if (data == null)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            SINGLETON.fHashMap.put(key, data);
         }
    }

    
    
    private final HashMap fHashMap;
    
    private hash() {
        this.fHashMap =  new HashMap();
    }
}
