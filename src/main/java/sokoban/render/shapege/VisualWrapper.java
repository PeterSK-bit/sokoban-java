package sokoban.render.shapege;

import java.lang.reflect.Method;

/**
 * A wrapper around any visual object to control its visibility via reflection.
 */
public class VisualWrapper {
    private final Object inner;

    /**
     * Wraps the given object for visibility control.
     *
     * @param inner the object to wrap
     */
    public VisualWrapper(Object inner) {
        this.inner = inner;
    }

    /**
     * Makes the wrapped object visible.
     */
    public void makeVisible() {
        this.invokeNoArgs("makeVisible");
    }

    /**
     * Makes the wrapped object invisible.
     */
    public void makeInvisible() {
        this.invokeNoArgs("makeInvisible");
    }

    private void invokeNoArgs(String methodName) {
        try {
            Method m = this.findMethod(this.inner.getClass(), methodName);
            m.setAccessible(true);
            m.invoke(this.inner);
        } catch (Exception e) {
            throw new RuntimeException("Wrapper fail on method: " + methodName, e);
        }
    }

    private Method findMethod(Class<?> c, String name) throws NoSuchMethodException {
        while (c != null) {
            try {
                return c.getDeclaredMethod(name);
            } catch (NoSuchMethodException ignored) { }
            c = c.getSuperclass();
        }
        throw new NoSuchMethodException(name);
    }

    /**
     * Returns the raw wrapped object.
     *
     * @return the wrapped object
     */
    public Object raw() {
        return this.inner;
    }
}


