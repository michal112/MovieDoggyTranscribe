package app.moviedoggytranscribe;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCore {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        ApplicationCore.context = context;
    }

}
