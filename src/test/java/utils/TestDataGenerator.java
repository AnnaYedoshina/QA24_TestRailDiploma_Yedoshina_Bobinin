package utils;

import models.TestCase;

public class TestDataGenerator {
    private static final String TITLE = "Позитивное тестирование формы Login";

    public static TestCase positiveTestCaseGeneration(String title){
        return TestCase.builder()
                .setTitle(TITLE)
                .setSection("Test Cases")
                .setType("Other")
                .setPriority("Medium")
                .setEstimate("30 minutes")
                .setReferences("qwe")
                .setAutomationType("None")
                .setPreconditions("Открыта форма Login на сайте TestRail")
                .setSteps("Заполнить поле email. Заполнить поле password. Нажать кнопку login")
                .setExpectedResult("Пользователь авторизован")
                .build();

    }
    public static TestCase negativeTestCaseGeneration(String title){
        return TestCase.builder()
                .setTitle("")
                .setSection("Test Cases")
                .setType("Other")
                .setPriority("Medium")
                .setEstimate("30 minutes")
                .setReferences("qwe")
                .setAutomationType("None")
                .setPreconditions("Открыта форма Login на сайте TestRail")
                .setSteps("Заполнить поле email. Заполнить поле password. Нажать кнопку login")
                .setExpectedResult("Пользователь авторизован")
                .build();
    }
}
