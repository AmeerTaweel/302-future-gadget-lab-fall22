package control;

import models.State;

public interface Backend<T extends State> {
    void updateState(T state, int width, int height);
}
