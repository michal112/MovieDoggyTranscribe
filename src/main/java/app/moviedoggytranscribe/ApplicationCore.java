package app.moviedoggytranscribe;

import org.springframework.context.ApplicationContext;

public class ApplicationCore {

    private static ApplicationContext context;

    private static SpringFxmlLoader springFxmlLoader = SpringFxmlLoader.getInstance();

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        ApplicationCore.context = context;
        springFxmlLoader.setApplicationContext(context);
    }

    public static SpringFxmlLoader getLoader() {
        return springFxmlLoader;
    }

}
