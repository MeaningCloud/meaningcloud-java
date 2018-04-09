package com.meaningcloud.test.junit;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * This runner sleeps for a few seconds before executing a test annotated with @Throttle.
 * This is useful to avoid request rate errors.
 */
public class Throttling extends BlockJUnit4ClassRunner {

    /**
     * Constructor
     * @param klass Class to apply delay
     * @throws InitializationError Raised when a parameter value can't be accepted
     */
    public Throttling(Class<?> klass) throws InitializationError {
        super(klass);
    }

    private static long SECONDS = 1000;
    private static long lastTimestamp = -1;

    /**
     * Delay
     */
    public static void sleep() {
        if (lastTimestamp == -1) {
            lastTimestamp = System.currentTimeMillis();
            return;
        }
        while (System.currentTimeMillis() - lastTimestamp < 1 * SECONDS) {
            try {
                Thread.sleep(1 * SECONDS);
            } catch (InterruptedException e) {
                // ignore
            }
        }
        lastTimestamp = System.currentTimeMillis();
    }


    /**
     * Delay
     * @param method method on a test class to be invoked at the appropriate point in test execution
     * @param notifier To notify JUnit of your progress running tests
     */
    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        if (method.getAnnotation(Throttle.class) != null) {
            sleep();
        }
        super.runChild(method, notifier);
    }
}
