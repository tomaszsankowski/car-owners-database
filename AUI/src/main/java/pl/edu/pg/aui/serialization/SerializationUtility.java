package pl.edu.pg.aui.serialization;

import pl.edu.pg.aui.dto.CarDTO;

import java.io.*;
import java.util.List;

public class SerializationUtility {
    public static void serialize(List<CarDTO> cars, String filename) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            stream.writeObject(cars);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<CarDTO> deserialize(String filename) throws IOException, ClassNotFoundException {
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<CarDTO>) stream.readObject();
        }
    }
}
