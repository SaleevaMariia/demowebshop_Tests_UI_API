package helpers;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.DriverUtils.getSessionId;

public class ActionOnFailure implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            AllureAttachments.addVideo(getSessionId());
            AllureAttachments.addScreenshotAs("Last screenshot");
        }
        closeWebDriver();
    }
}