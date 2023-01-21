package screens;

import models.State;

public interface Backend<T extends State> {
    void updateState(T state);
}