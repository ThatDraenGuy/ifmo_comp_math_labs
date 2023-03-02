package draen.controllers;

import draen.context.CommonContext;
import draen.context.ControllerContext;

public interface Controller<T extends CommonContext<T>> {
    void handle(ControllerContext<T> ctx);
}
