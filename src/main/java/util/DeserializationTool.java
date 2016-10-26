package util;

import pojo.Category;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by zhaokai on 16-10-26.
 */
public class DeserializationTool {

    private static ObjectInputStream getObjectInputStream(String fileName) throws IOException {
        return new ObjectInputStream(new FileInputStream(Constants.SERIALIZATION_DIR+fileName));
    }

    public static List<Category> getCategoryList(){
        try {
            return (List<Category>)getObjectInputStream("category").readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
