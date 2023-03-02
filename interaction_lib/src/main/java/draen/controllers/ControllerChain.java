package draen.controllers;

import draen.context.CommonContext;
import draen.context.ControllerContext;

import java.util.*;

public class ControllerChain<T extends CommonContext<T>> {
    private final ControllerContext<T> context;
    private final Queue<Controller<T>> controllers;

    public ControllerChain(ControllerContext<T> context, List<Controller<T>> controllers) {
        this.context = context;
        this.controllers = new LinkedList<>(controllers);
    }

    public void begin() {
        while (!controllers.isEmpty()) {
            controllers.remove().handle(context);
        }
    }
}
