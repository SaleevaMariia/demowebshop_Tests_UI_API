package helpers;

import config.Environment;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class ActionOnFailure implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            AllureAttachments.addScreenshotAs("Last screenshot");
            if (!Environment.config.videoStorage().equals(""))
                AllureAttachments.addVideo(DriverUtils.getSessionId());
        }
        closeWebDriver();
    }
}