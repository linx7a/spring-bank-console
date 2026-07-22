package linx7a;

import linx7a.command.OperationsConsoleListener;
import linx7a.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OperationsConsoleListener operationsConsoleListener = context.getBean(OperationsConsoleListener.class);

        Thread thread = new Thread(operationsConsoleListener);
        thread.start();
    }
}