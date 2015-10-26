package com.demo.base;

import java.util.List;

import org.testng.ITestResult;
import org.uncommons.reportng.ReportNGUtils;

/**
 * 
 * @author Nellore Krishna Kumar
 *
 */
public class ReportUtils extends ReportNGUtils {
    public List<String> getTestOutput(final ITestResult result) {
        final List<String> output = super.getTestOutput(result);
        final Exception error = (Exception) result.getAttribute("reportGeneratingException");

        if (error != null) {
            output.add("Generating report error: " + error);
            return output;
        }

        final String screenshot = (String) result.getAttribute("screenshot");

        if (screenshot != null) {
            output.add("<a href=\"" + screenshot + "\" target=\"_blank\">Screenshot</a> for a page " +
                    result.getAttribute("screenshotURL"));
        }

        return output;
    }
}
