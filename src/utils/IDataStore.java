package utils;

import java.util.List;

public interface IDataStore {
    <T> void addToCollection(String collectionName, T element, Class<T> clazz);

    <T> List<T> getCollection(String collectionName, Class<T> clazz);
}
