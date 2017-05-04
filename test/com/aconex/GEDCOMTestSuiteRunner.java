/**
 * Created by meghanaharidev
 */

package com.aconex;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GEDCOMTestSuiteRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GEDCOMTestSuite.class);

        System.out.println("\nTotal Tests Run = " + result.getRunCount() +
                           "\nTotal Run Time = " + result.getRunTime() + "ms" +
                           "\nTotal Failures = " + result.getFailureCount());

        if(!result.wasSuccessful()) {
            System.out.println("\nFailure details:\n");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
}
