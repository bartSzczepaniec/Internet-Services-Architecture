package aui.laboratorium.lab1;

import java.io.*;
import java.util.logging.Level;

public class CloningUtility {
    public static <T extends Serializable> T clone(T object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//Closing this stream has no effect
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw new IllegalStateException(ex);
        }

    }
}
