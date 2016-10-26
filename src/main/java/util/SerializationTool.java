package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by zhaokai on 16-10-26.
 */
public class SerializationTool {

    private static ObjectOutputStream getObjectOutputStream(String fileName) throws IOException {
        return new ObjectOutputStream(new FileOutputStream(Constants.SERIALIZATION_DIR+fileName));
    }

    public static void saveCategoryList(){
        try{

            getObjectOutputStream("category").writeObject(DataMiningTool.getCategory());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
