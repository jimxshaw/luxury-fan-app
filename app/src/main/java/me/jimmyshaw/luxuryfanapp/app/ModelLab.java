package me.jimmyshaw.luxuryfanapp.app;


/*
    ModelLab is a Bill Pugh singleton pattern implementation. Before Java 5, certain singleton
    implementations failed in some situations where too many threads try to get the singleton class
    instance simultaneously. Bill's approach involves a private inner static class. When the singleton
    class is loaded, the helper class is not loaded into memory. Only when getInstance is called does
    the helper class get loaded and creates the singleton instance. This implementation is widely
    used as it doesn't require synchronization.
*/
public class ModelLab {

    private ModelLab() {

    }

    public static ModelLab getInstance() {
        return ModelLabHelper.INSTANCE;
    }

    public static class ModelLabHelper {
        private static final ModelLab INSTANCE = new ModelLab();
    }
}
