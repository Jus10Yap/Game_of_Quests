
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SeleniumTest {
    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    public void assertShieldCount(int playerNumber, int expectedShieldCount) throws InterruptedException {
        WebElement shieldCountElement = driver.findElement(By.id("p" + playerNumber + "-shields"));

        int actualShieldCount = Integer.parseInt(shieldCountElement.getText());

        System.out.println("P" + playerNumber + " Number of shields: " + actualShieldCount);

        // Assert the shield count
        Assertions.assertEquals(expectedShieldCount, actualShieldCount, "P"+playerNumber+" should have "+expectedShieldCount+" shields.");

        Thread.sleep(2000);
    }

    public void assertCardCount(int playerNumber, int expectedCardCount) throws InterruptedException {
        WebElement cardCountElement = driver.findElement(By.id("p" + playerNumber + "-cards"));

        int actualCardCount = Integer.parseInt(cardCountElement.getText());

        System.out.println("P" + playerNumber + " Number of cards: " + actualCardCount);

        // Assert the shield count
        Assertions.assertEquals(expectedCardCount, actualCardCount, "P"+playerNumber+" should have "+expectedCardCount+" shields.");

        Thread.sleep(2000);
    }


    @Test
    void a1Scenario() throws InterruptedException {
        // Your test logic here
        driver.get("http://127.0.0.1:8081");

        driver.findElement(By.xpath("//button[contains(text(), 'A1_scenario')]")).click();
        Thread.sleep(2000);

        // P1 draws a quest of 4 stages
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(2000);

        // P1 is asked but declines to sponsor
        driver.findElement(By.xpath("//button[contains(text(), 'No')]")).click();
        Thread.sleep(2000);

        // P2 is asked and sponsors
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(3000);

        WebElement buildStageInput = driver.findElement(By.id("build-stage-input"));
        buildStageInput.clear();
        WebElement confirmButton = driver.findElement(By.id("confirm-build-stage"));

        // P2 builds stage 1 with "F5, H10"
        buildStageInput.clear();
        buildStageInput.sendKeys("F5,H10");
        confirmButton.click();
        Thread.sleep(2000);

        // P2 builds stage 2 with "F15, S10"
        buildStageInput.clear();
        buildStageInput.sendKeys("F15,S10");
        confirmButton.click();
        Thread.sleep(2000);
        // P2 builds stage 3 with "F15, D5, B15"
        buildStageInput.clear();
        buildStageInput.sendKeys("F15,D5,B15");
        confirmButton.click();
        Thread.sleep(2000);
        // P2 builds stage 4 with "F40, B15"
        buildStageInput.clear();
        buildStageInput.sendKeys("F40,B15");
        confirmButton.click();
        Thread.sleep(2000);

        // P2 Assertions
        // Assert P2 has 3 cards left
        assertCardCount(2,3);
        // Assert P2 has 0 shields
        assertShieldCount(2,0);

        // Stage 1
        // P1 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P3 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P4 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        WebElement discardCardsInput = driver.findElement(By.id("discard-cards-input"));
        discardCardsInput.clear();
        WebElement confirmSelectionButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm Selection')]"));
        // P1 draws a "F30" and discards a "F5"
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // P1 Assertions
        // Assert P1 has 12 cards
        assertCardCount(1,12);
        // Assert P1 has no shields
        assertShieldCount(1,0);

        // P3 draws a "S10" and discards a "F5"
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // P3 Assertions
        // Assert P3 has 12 cards
        assertCardCount(3,12);
        // Assert P3 has no shields
        assertShieldCount(3,0);

        // P4 draws a "B15" and discards a "F5"
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // P4 Assertions
        // Assert P4 has 12 cards
        assertCardCount(4,12);
        // Assert P4 has 0 shields
        assertShieldCount(4,0);

        WebElement buildAttackInput = driver.findElement(By.id("build-attack-input"));
        buildAttackInput.clear();
        WebElement confirmAttackButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm Attack')]"));

        // P1 sees their hand and builds an attack with "D5, S10"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("D5, S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P1 Assertions
        // Assert P1 has 10 cards
        assertCardCount(1,10);

        // P3 sees their hand and builds an attack with "S10, D5"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("S10, D5");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P3 Assertions
        // Assert P3 has 10 cards
        assertCardCount(3,10);

        // P4 sees their hand and builds an attack with "D5, H10"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("D5, H10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        Thread.sleep(4000);
        // P4 Assertions
        // Assert P4 has 10 cards
        assertCardCount(4,10);

        // Stage 2
        // P1 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P3 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P4 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P1 draws a "F10"
        // P1 Assertions
        // Assert P1 has 11 cards
        assertCardCount(1,11);
        // P3 draws a "L20"
        // P3 Assertions
        // Assert P3 has 11 cards
        assertCardCount(3,11);
        // P4 draws a "L20"
        // P4 Assertions
        // Assert P4 has 11 cards
        assertCardCount(4,11);

        // P1 sees their hand and builds an attack with "H10, S10"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10, S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P1 Assertions
        // Assert P1 has 11 cards
        assertCardCount(1,9);

        // P3 sees their hand and builds an attack with "B15, S10"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15, S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P3 Assertions
        // Assert P3 has 9 cards
        assertCardCount(3,9);

        // P4 sees their hand and builds an attack with "H10, B15"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10, B15");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P4 Assertions
        // Assert P4 has 9 cards
        assertCardCount(4,9);

        // Assert P1 has 9 cards
        assertCardCount(1,9);

        // Assert P1 has no shields
        assertShieldCount(1,0);

        // Assert P1 hand is "F5, F10, F15, F15, F30, H10, B15, B15, L20"
        WebElement p1HandElement = driver.findElement(By.id("p1-hand"));
        String p1Hand = p1HandElement.getText();
        String expectedHand = "F5, F10, F15, F15, F30, H10, B15, B15, L20";
        System.out.println("P1 Hand: " + p1Hand);
        Assertions.assertEquals(expectedHand, p1Hand, "Player 1's hand should match the expected cards.");
        Thread.sleep(2000);
        Thread.sleep(4000);
        // Stage 3
        // P3 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P4 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P3 draws a "B15"
        // P3 Assertions
        // Assert P3 has 10 cards
        assertCardCount(3,10);
        // P4 draws a "S10"
        // P4 Assertions
        // Assert P4 has 10 cards
        assertCardCount(4,10);

        // P3 sees their hand and builds an attack with "L20, H10, S10"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("L20, H10, S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P3 Assertions
        // Assert P3 has 7 cards
        assertCardCount(3,7);

        // P4 sees their hand and builds an attack with "B15, S10, L20"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15, S10, L20");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P4 Assertions
        // Assert P4 has 7 cards
        assertCardCount(4,7);
        Thread.sleep(4000);
        // Stage 4
        // P3 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P4 is asked and decides to participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P3 draws a "F30"
        // P3 Assertions
        // Assert P3 has 8 cards
        assertCardCount(3,8);
        // P4 draws a "L20"
        // P4 Assertions
        // Assert P4 has 8 cards
        assertCardCount(4,8);

        // P3 sees their hand and builds an attack with "B15, H10, L20"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15, H10, L20");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // P3 Assertions
        // Assert P3 has 5 cards
        assertCardCount(3,5);

        // P4 sees their hand and builds an attack with "D5, S10, L20, E30"
        buildAttackInput.clear();
        buildAttackInput.sendKeys("D5, S10, L20, E30");
        confirmAttackButton.click();
        Thread.sleep(2000);
        Thread.sleep(4000);
        // P4 Assertions
        // Assert P4 has 4 cards
        assertCardCount(4,4);



        // Assert P1 has no shields
        assertShieldCount(1,0);

        // Assert P1 hand is "F5, F10, F15, F15, F30, H10, B15, B15, L20"
        p1HandElement = driver.findElement(By.id("p1-hand"));
        p1Hand = p1HandElement.getText();
        expectedHand = "F5, F10, F15, F15, F30, H10, B15, B15, L20";
        System.out.println("P1 Hand: " + p1Hand);
        Assertions.assertEquals(expectedHand, p1Hand, "Player 1's hand should match the expected cards.");
        Thread.sleep(2000);

        // Assert P3 has 5 cards
        assertCardCount(3,5);

        // Assert P3 has no shields
        assertShieldCount(3,0);

        // Assert P3 hand is  "F5, F5, F15, F30, S10"
        WebElement p3HandElement = driver.findElement(By.id("p3-hand"));
        String p3Hand = p3HandElement.getText();
        expectedHand =  "F5, F5, F15, F30, S10";
        System.out.println("P3 Hand: " + p3Hand);
        Assertions.assertEquals(expectedHand, p3Hand, "Player 3's hand should match the expected cards.");
        Thread.sleep(2000);

        // P4 Asserts
        // Assert P4 has 4 cards
        assertCardCount(4,4);

        // Assert P4 has 4 shields
        assertShieldCount(4,4);

        // Assert P4 hand is "F15, F15, F40, L20"
        WebElement p4HandElement = driver.findElement(By.id("p4-hand"));
        String p4Hand = p4HandElement.getText();
        expectedHand =  "F15, F15, F40, L20";
        System.out.println("P4 Hand: " + p4Hand);
        Assertions.assertEquals(expectedHand, p4Hand, "Player 4's hand should match the expected cards.");
        Thread.sleep(3000);

        // Sponsor discards all cards used in quest and draws 13 random cards and then trims down to 12 cards
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F10, F10, F10, F10");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // P2 Asserts
        // Assert P2 has 12 cards
        assertCardCount(2,12);

        // Assert P2 has 0 shields
        assertShieldCount(2,0);

        // Assert P2 hand is "F15, F15, F40, L20"
        WebElement p2HandElement = driver.findElement(By.id("p2-hand"));
        String p2Hand = p2HandElement.getText();
        expectedHand =  "F5, F10, F10, F15, F20, F20, F20, F20, F20, F20, H10, E30";
        System.out.println("P2 Hand: " + p2Hand);
        Assertions.assertEquals(expectedHand, p2Hand, "Player 2's hand should match the expected cards.");
        Thread.sleep(3000);

        // Assert absence of winners
        WebElement gameLogElement = driver.findElement(By.id("game-log"));
        String gameLogContent = gameLogElement.getText();
        System.out.println("Game Log Content: " + gameLogContent);
        String expectedMessage = "[Game] No winners yet. The game continues!";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "The game log should contain the expected message: " + expectedMessage);
        Thread.sleep(2000);
        expectedMessage = "[Game] Non-Winner(s): P1, P2, P3, P4";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "P1, P2, P3, P4 should not be winners: " + expectedMessage);
    }

    @Test
    void twoWinnersScenario() throws InterruptedException {
        // Your test logic here
        driver.get("http://127.0.0.1:8081");

        driver.findElement(By.xpath("//button[contains(text(), '2winner_game_2winner_quest')]")).click();
        Thread.sleep(2000);

        // P1 draws a quest of 4 stages
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(2000);

        // P1 accepts to sponsor the quest
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        WebElement buildStageInput = driver.findElement(By.id("build-stage-input"));
        buildStageInput.clear();
        WebElement confirmButton = driver.findElement(By.id("confirm-build-stage"));

        // P1 builds stage 1 with "F5"
        buildStageInput.clear();
        buildStageInput.sendKeys("F5");
        confirmButton.click();
        Thread.sleep(2000);

        // P1 builds stage 2 with "F5 + dagger"
        buildStageInput.clear();
        buildStageInput.sendKeys("F5,D5");
        confirmButton.click();
        Thread.sleep(2000);

        // P1 builds stage 3 with "F10 + horse"
        buildStageInput.clear();
        buildStageInput.sendKeys("F10,H10");
        confirmButton.click();
        Thread.sleep(2000);

        // P1 builds stage 4 with "F10 + axe"
        buildStageInput.clear();
        buildStageInput.sendKeys("F10,B15");
        confirmButton.click();
        Thread.sleep(2000);

        // P1 Assertions
        // Assert P1 has 5 cards
        assertCardCount(1,5);

        // Stage 1
        // P2, P3 and P4 participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        WebElement discardCardsInput = driver.findElement(By.id("discard-cards-input"));
        discardCardsInput.clear();
        WebElement confirmSelectionButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm Selection')]"));
        // P2 draws F5, discards F5
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // Assert P2 has 12 cards
        assertCardCount(2,12);

        // P3 draws F40, discards F5
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // Assert P3 has 12 cards
        assertCardCount(3,12);

        // P4 draws F10, discards F10
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F10");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // Assert P4 has 12 cards
        assertCardCount(4,12);

        WebElement buildAttackInput = driver.findElement(By.id("build-attack-input"));
        buildAttackInput.clear();
        WebElement confirmAttackButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm Attack')]"));

        // P2 attack: 1 horse
        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P2 has 11 cards
        assertCardCount(2,11);

        // P3 attack: nothing
        buildAttackInput.clear();
        buildAttackInput.sendKeys(" ");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P3 has 12 cards
        assertCardCount(3,12);

        // P4 attack: 1 horse
        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P4 has 11 cards
        assertCardCount(4,11);

        // Assert P3 has 0 shields
        assertShieldCount(3,0);
        Thread.sleep(4000);
        // Stage 2
        // P2 and P4 participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P2 draws F10
        // Assert P2 has 12 cards
        assertCardCount(2,12);
        // P4 draws F30
        // Assert P4 has 12 cards
        assertCardCount(4,12);

        // P2 attack: sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("S10");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // Assert P2 has 11 cards
        assertCardCount(2,11);

        // P4 attack: sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("S10");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // Assert P4 has 11 cards
        assertCardCount(4,11);

        Thread.sleep(4000);
        // Stage 3
        // P2 and P4 participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P2 draws F30
        // P4 draws F15
        // Assert P2 has 12 cards
        assertCardCount(2,12);
        // Assert P4 has 12 cards
        assertCardCount(4,12);

        // P2 attack: horse + sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10, S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P4 attack: horse + sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10, S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P2 has 10 cards
        assertCardCount(2,10);
        // Assert P4 has 10 cards
        assertCardCount(4,10);
        Thread.sleep(4000);
        // Stage 4
        // P2 and P4 participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P2 draws F15
        // P4 draws F20
        // Assert P2 has 11 cards
        assertCardCount(2,11);
        // Assert P4 has 11 cards
        assertCardCount(4,11);

        // P2 attack: sword + axe
        buildAttackInput.clear();
        buildAttackInput.sendKeys("S10,B15");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P4: attack: sword + axe
        buildAttackInput.clear();
        buildAttackInput.sendKeys("S10,B15");
        confirmAttackButton.click();
        Thread.sleep(2000);
        Thread.sleep(4000);
        // Assert P2 has 9 cards
        assertCardCount(2,9);
        // Assert P4 has 9 cards
        assertCardCount(4,9);

        // P2 and P4 each earn 4 shields
        // Assert P2 has 4 shields
        assertShieldCount(2,4);
        // Assert P4 has 4 shields
        assertShieldCount(4,4);

        // P1 has 16 cards and discards: 1xF5, 1xF10, 2xF15
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5,F10,F15,F15");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // Assert P1 has 12 cards
        assertCardCount(1,12);

        // End Round
        driver.findElement(By.xpath("//button[contains(text(), 'End Round')]")).click();
        Thread.sleep(2000);

        // P2 draws a quest of 3 stages
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(2000);

        // P2 declines to sponsor it
        driver.findElement(By.xpath("//button[contains(text(), 'No')]")).click();
        Thread.sleep(2000);

        // P3 sponsors this quest
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P3 builds stage 1 with "F5"
        buildStageInput.clear();
        buildStageInput.sendKeys("F5");
        confirmButton.click();
        Thread.sleep(2000);

        // P3 builds stage 2 with "F5 + dagger"
        buildStageInput.clear();
        buildStageInput.sendKeys("F5,D5");
        confirmButton.click();
        Thread.sleep(2000);

        // P3 builds stage 3 with "F5 + horse"
        buildStageInput.clear();
        buildStageInput.sendKeys("F5,H10");
        confirmButton.click();
        Thread.sleep(2000);

        // Assert P3 has 7 cards
        assertCardCount(3,7);
        Thread.sleep(4000);
        // Stage 1
        // P1 declines to participate
        driver.findElement(By.xpath("//button[contains(text(), 'No')]")).click();
        Thread.sleep(2000);

        // P2 and P4 participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P2 draws dagger
        // P4 draws dagger
        // Assert P2 has 10 cards
        assertCardCount(2,10);
        // Assert P4 has 10 cards
        assertCardCount(4,10);

        // P2 attack: dagger
        buildAttackInput.clear();
        buildAttackInput.sendKeys("D5");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // P4 attack: dagger
        buildAttackInput.clear();
        buildAttackInput.sendKeys("D5");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P2 has 9 cards
        assertCardCount(2,9);
        // Assert P4 has 9 cards
        assertCardCount(4,9);
        Thread.sleep(4000);
        // Stage 2
        // P2 and P4 participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);
        // P2 draws F15
        // P4 draws F15
        // Assert P2 has 10 cards
        assertCardCount(2,10);
        // Assert P4 has 10 cards
        assertCardCount(4,10);

        // P2 attack: axe
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // P4 attack: axe
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P2 has 9 cards
        assertCardCount(2,9);
        // Assert P4 has 9 cards
        assertCardCount(4,9);
        Thread.sleep(4000);
        // Stage 3
        // P2 and P4 participate
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);
        // P2 draws F25
        // P4 draws F25
        // Assert P2 has 10 cards
        assertCardCount(2,10);
        // Assert P4 has 10 cards
        assertCardCount(4,10);

        // P2 attack: 1 excalibur
        buildAttackInput.clear();
        buildAttackInput.sendKeys("E30");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // P4 attack: 1 excalibur
        buildAttackInput.clear();
        buildAttackInput.sendKeys("E30");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P3 trims their hand and discards 1xF20, 1xF25, 1xF30
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F20,F25,F30");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        Thread.sleep(4000);
        // P1 Assertions
        // Assert P1 has 12 cards
        assertCardCount(1,12);
        // Assert P1 has no shields
        assertShieldCount(1,0);
        // Assert P1 hand is 2xF15, 4xF20, 2xF25, 1xF30, horse, axe, lance
        WebElement p1HandElement = driver.findElement(By.id("p1-hand"));
        String p1Hand = p1HandElement.getText();
        String expectedHand = "F15, F15, F20, F20, F20, F20, F25, F25, F30, H10, B15, L20";
        System.out.println("P1 Hand: " + p1Hand);
        Assertions.assertEquals(expectedHand, p1Hand, "Player 1's hand should match the expected cards.");
        Thread.sleep(2000);

        // P2 Assertions
        // Assert P2 has 9 cards
        assertCardCount(2,9);
        // Assert P2 has 7 shields
        assertShieldCount(2,7);
        // Assert P2 hand is 1xF10, 2xF15, 1xF25, 1xF30,1xF40, 1xF50, 2 lances
        WebElement p2HandElement = driver.findElement(By.id("p2-hand"));
        String p2Hand = p2HandElement.getText();
        expectedHand =  "F10, F15, F15, F25, F30, F40, F50, L20, L20";
        System.out.println("P2 Hand: " + p2Hand);
        Assertions.assertEquals(expectedHand, p2Hand, "Player 2's hand should match the expected cards.");
        Thread.sleep(2000);

        // P3 Assertions
        // Assert P3 has 12 cards
        assertCardCount(3,12);
        // Assert P3 has no shields
        assertShieldCount(3,0);
        // Assert P3 hand is  1xF20, 1xF40, 2 daggers, 1 sword, 4 horses, 2 axes, 1 lance
        WebElement p3HandElement = driver.findElement(By.id("p3-hand"));
        String p3Hand = p3HandElement.getText();
        expectedHand =  "F20, F40, D5, D5, S10, H10, H10, H10, H10, B15, B15, L20";
        System.out.println("P3 Hand: " + p3Hand);
        Assertions.assertEquals(expectedHand, p3Hand, "Player 3's hand should match the expected cards.");
        Thread.sleep(2000);

        // P4 Assertions
        // Assert P4 has 9 cards
        assertCardCount(4,9);
        // Assert P4 has 7 shields
        assertShieldCount(4,7);
        // Assert P4 hand is 2xF15, 1xF20, 1xF25, 1xF30, 1xF50, 1xF70, 2 lances
        WebElement p4HandElement = driver.findElement(By.id("p4-hand"));
        String p4Hand = p4HandElement.getText();
        expectedHand =  "F15, F15, F20, F25, F30, F50, F70, L20, L20";
        System.out.println("P4 Hand: " + p4Hand);
        Assertions.assertEquals(expectedHand, p4Hand, "Player 4's hand should match the expected cards.");
        Thread.sleep(2000);

        // Assert presence of winners
        WebElement gameLogElement = driver.findElement(By.id("game-log"));
        String gameLogContent = gameLogElement.getText();
        System.out.println("Game Log Content: " + gameLogContent);
        String expectedMessage = "[Game] Congratulations! The game is over.";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "The game log should contain the expected message: " + expectedMessage);
        Thread.sleep(2000);
        // Winners
        expectedMessage = "[Game] Winner(s): P2, P4";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "The winners should contain the expected message: " + expectedMessage);
        Thread.sleep(2000);
        // Losers
        expectedMessage = "[Game] Loser(s): P1, P3";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "The losers should contain the expected message: " + expectedMessage);

    }

    @Test
    void oneWinnerScenario() throws InterruptedException {
        // Your test logic here
        driver.get("http://127.0.0.1:8081");

        driver.findElement(By.xpath("//button[contains(text(), '1winner_game_with_events')]")).click();
        Thread.sleep(2000);

        // P1 draws a 4 stage quest and decides to sponsor it
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        WebElement buildStageInput = driver.findElement(By.id("build-stage-input"));
        buildStageInput.clear();
        WebElement confirmButton = driver.findElement(By.id("confirm-build-stage"));
        // Stage 1: F5
        buildStageInput.clear();
        buildStageInput.sendKeys("F5");
        confirmButton.click();
        Thread.sleep(2000);

        // Stage2: F10
        buildStageInput.clear();
        buildStageInput.sendKeys("F10");
        confirmButton.click();
        Thread.sleep(2000);

        // stage 3: F15
        buildStageInput.clear();
        buildStageInput.sendKeys("F15");
        confirmButton.click();
        Thread.sleep(2000);

        // stage 4: F20
        buildStageInput.clear();
        buildStageInput.sendKeys("F20");
        confirmButton.click();
        Thread.sleep(2000);

        // Assert P1 has 8 cards
        assertCardCount(1,8);

        // P2, P3 and P4 participate in stage 1
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        WebElement discardCardsInput = driver.findElement(By.id("discard-cards-input"));
        discardCardsInput.clear();
        WebElement confirmSelectionButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm Selection')]"));

        // P2 draws F5, discards F5
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P3 draws F10, discards F10
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F10");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P4 draws F20, discards F20
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F20");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // Assert P2 has 12 cards
        assertCardCount(2,12);
        // Assert P3 has 12 cards
        assertCardCount(3,12);
        // Assert P4 has 12 cards
        assertCardCount(4,12);

        WebElement buildAttackInput = driver.findElement(By.id("build-attack-input"));
        buildAttackInput.clear();
        WebElement confirmAttackButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm Attack')]"));

        // P2 attack: sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P3 attack: sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P4 attack: sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("S10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P2 has 11 cards
        assertCardCount(2,11);
        // Assert P3 has 11 cards
        assertCardCount(3,11);
        // Assert P4 has 11 cards
        assertCardCount(4,11);
        Thread.sleep(4000);
        // P2, P3 and P4 participate in stage 2
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);


        // P2, P3, P4 all use the same attack, a horse
        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P2 has 11 cards
        assertCardCount(2,11);
        // Assert P3 has 11 cards
        assertCardCount(3,11);
        // Assert P4 has 11 cards
        assertCardCount(4,11);
        Thread.sleep(4000);
        // P2, P3 and P4 participate in stage 3
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P2, P3, P4 all use the same attack, an axe
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15");
        confirmAttackButton.click();
        Thread.sleep(2000);

        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15");
        confirmAttackButton.click();
        Thread.sleep(2000);

        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15");
        confirmAttackButton.click();
        Thread.sleep(2000);
        Thread.sleep(4000);
        // P2, P3 and P4 participate in stage 4
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P2, P3, P4 all use the same attack, a lance
        buildAttackInput.clear();
        buildAttackInput.sendKeys("L20");
        confirmAttackButton.click();
        Thread.sleep(2000);

        buildAttackInput.clear();
        buildAttackInput.sendKeys("L20");
        confirmAttackButton.click();
        Thread.sleep(2000);

        buildAttackInput.clear();
        buildAttackInput.sendKeys("L20");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // Assert P2 has 11 cards
        assertCardCount(2,11);
        // Assert P3 has 11 cards
        assertCardCount(3,11);
        // Assert P4 has 11 cards
        assertCardCount(4,11);
        Thread.sleep(4000);
        // P1 discards 2xF5, 2xF10
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5,F5,F10,F10");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // Assert P1 has 12 cards
        assertCardCount(1,12);
        // P2, P3 and P4 each earn 4 shields
        // Assert P1 has 0 shields
        assertShieldCount(1,0);
        // Assert P2 has 4 shields
        assertShieldCount(2,4);
        // Assert P3 has 4 shields
        assertShieldCount(3,4);
        // Assert P4 has 4 shields
        assertShieldCount(4,4);

        // End Round
        driver.findElement(By.xpath("//button[contains(text(), 'End Round')]")).click();
        Thread.sleep(2000);

        // P2 draws Plague
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(3000);

        // Assert P1 has 0 shields
        assertShieldCount(1,0);
        // Assert P2 has 2 shields
        assertShieldCount(2,2);
        // Assert P3 has 4 shields
        assertShieldCount(3,4);
        // Assert P4 has 4 shields
        assertShieldCount(4,4);

        // End Round
        driver.findElement(By.xpath("//button[contains(text(), 'End Round')]")).click();
        Thread.sleep(2000);

        // P3 draws ‘Prosperity’: All 4 players receive 2 adventure cards
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(3000);

        // P1 discards 1xF5, 1xF10 (has 12 cards)
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5,F10");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P2 discards F5 (has 12 cards)
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P3 discards F5 (has 12 cards)
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P4 discards F20 (has 12 cards)
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F20");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // Assert P1 has 12 cards
        assertCardCount(1,12);
        // Assert P2 has 12 cards
        assertCardCount(2,12);
        // Assert P3 has 12 cards
        assertCardCount(3,12);
        // Assert P4 has 12 cards
        assertCardCount(4,12);

        // End Round
        driver.findElement(By.xpath("//button[contains(text(), 'End Round')]")).click();
        Thread.sleep(2000);

        // P4 draws ‘Queen’s favor’ : discards F25 and F30
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(3000);

        discardCardsInput.clear();
        discardCardsInput.sendKeys("F25,F30");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // Assert P4 has 12 cards
        assertCardCount(4,12);

        // End Round
        driver.findElement(By.xpath("//button[contains(text(), 'End Round')]")).click();
        Thread.sleep(2000);

        // P1 draws a quest of 3 stages and decides to sponsor it
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // Stage 1: F15
        buildStageInput.clear();
        buildStageInput.sendKeys("F15");
        confirmButton.click();
        Thread.sleep(2000);
        // Stage2: F15+ dagger
        buildStageInput.clear();
        buildStageInput.sendKeys("F15, D5");
        confirmButton.click();
        Thread.sleep(2000);
        // Stage3: F20+dagger
        buildStageInput.clear();
        buildStageInput.sendKeys("F20, D5");
        confirmButton.click();
        Thread.sleep(2000);

        // P2, P3 and P4 participate in stage 1
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);
        // P2 discards F5
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P3  discards F10
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F10");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P4 discards F20
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F20");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P2 attack: axe
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // P3 attack: axe
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // P4 attack: horse
        buildAttackInput.clear();
        buildAttackInput.sendKeys("H10");
        confirmAttackButton.click();
        Thread.sleep(6000);

        // P2 and P3 participate in stage 2
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);
        // P2 attack: axe+horse
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15, H10");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // P3 attack: axe+sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("B15, S10");
        confirmAttackButton.click();
        Thread.sleep(6000);

        // P2 and P3 participate in stage 3
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);
        // P2 attack: lance + sword
        buildAttackInput.clear();
        buildAttackInput.sendKeys("L20, S10");
        confirmAttackButton.click();
        Thread.sleep(2000);
        // P3 attack: excalibur
        buildAttackInput.clear();
        buildAttackInput.sendKeys("E30");
        confirmAttackButton.click();
        Thread.sleep(6000);
        // P1 discards 3xF15
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F15,F15,F15");
        confirmSelectionButton.click();
        Thread.sleep(4000);

        // P1 Assertions
        // P1 hand: 2 daggers, 3 horses, 4 swords, 2xF25, F35. (12 cards)
        // Assert P1 has 12 cards
        assertCardCount(1,12);
        // Assert P1 has no shields
        assertShieldCount(1,0);
        // Assert P1 hand is  2 daggers, 3 horses, 4 swords, 2xF25, F35
        WebElement p1HandElement = driver.findElement(By.id("p1-hand"));
        String p1Hand = p1HandElement.getText();
        String expectedHand = "F25, F25, F35, D5, D5, S10, S10, S10, S10, H10, H10, H10";
        System.out.println("P1 Hand: " + p1Hand);
        Assertions.assertEquals(expectedHand, p1Hand, "Player 1's hand should match the expected cards.");
        Thread.sleep(2000);


        // P2 Assertions
        // P2 hand: F15, F25, F30, F40, horse, 3 swords, 1 excalibur (9 cards)
        // Assert P2 has 9 cards
        assertCardCount(2,9);
        // Assert P2 has 5 shields
        assertShieldCount(2,5);
        // Assert P2 hand is  F15, F25, F30, F40, horse, 3 swords, 1 excalibur
        WebElement p2HandElement = driver.findElement(By.id("p2-hand"));
        String p2Hand = p2HandElement.getText();
        expectedHand =  "F15, F25, F30, F40, S10, S10, S10, H10, E30";
        System.out.println("P2 Hand: " + p2Hand);
        Assertions.assertEquals(expectedHand, p2Hand, "Player 2's hand should match the expected cards.");
        Thread.sleep(3000);

        // P3 hand: F10, F25, F30, F40, F50, 2 horses, 2 swords, 1 lance (10 cards)
        // P3 Assertions
        // Assert P3 has 10 cards
        assertCardCount(3,10);
        // Assert P3 has 7 shields
        assertShieldCount(3,7);
        // Assert P3 hand is  F10, F25, F30, F40, F50, 2 horses, 2 swords, 1 lance (10 cards)
        WebElement p3HandElement = driver.findElement(By.id("p3-hand"));
        String p3Hand = p3HandElement.getText();
        expectedHand =  "F10, F25, F30, F40, F50, S10, S10, H10, H10, L20";
        System.out.println("P3 Hand: " + p3Hand);
        Assertions.assertEquals(expectedHand, p3Hand, "Player 3's hand should match the expected cards.");
        Thread.sleep(2000);

        // P4 hand: 2xF25, F30, F50, F70, 2 daggers, 2 swords, 1 axe, 1 lance (11 cards)
        // P4 Assertions
        // Assert P4 has 11 cards
        assertCardCount(4,11);
        // Assert P4 has 4 shields
        assertShieldCount(4,4);
        // Assert P4 hand is 2xF25, F30, F50, F70, 2 daggers, 2 swords, 1 axe, 1 lance
        WebElement p4HandElement = driver.findElement(By.id("p4-hand"));
        String p4Hand = p4HandElement.getText();
        expectedHand =  "F25, F25, F30, F50, F70, D5, D5, S10, S10, B15, L20";
        System.out.println("P4 Hand: " + p4Hand);
        Assertions.assertEquals(expectedHand, p4Hand, "Player 4's hand should match the expected cards.");
        Thread.sleep(3000);
        // P3 is declared (and asserted as) the winner
        // Assert presence of winners
        // Assert presence of winners
        WebElement gameLogElement = driver.findElement(By.id("game-log"));
        String gameLogContent = gameLogElement.getText();
        System.out.println("Game Log Content: " + gameLogContent);
        String expectedMessage = "[Game] Congratulations! The game is over.";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "The game log should contain the expected message: " + expectedMessage);

        // Winners
        expectedMessage = "[Game] Winner(s): P3";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "The winners should contain the expected message: " + expectedMessage);

        // Losers
        expectedMessage = "[Game] Loser(s): P1, P2, P4";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "The losers should contain the expected message: " + expectedMessage);
    }

    @Test
    void noWinnerScenario() throws InterruptedException {
        // Your test logic here
        driver.get("http://127.0.0.1:8081");

        driver.findElement(By.xpath("//button[contains(text(), '0_winner_quest')]")).click();
        Thread.sleep(2000);

        // P1 draws a 2 stage quest and decides to sponsor it.
        driver.findElement(By.xpath("//button[contains(text(), 'Draw Event Card')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        // P1 builds 2 stages:
        WebElement buildStageInput = driver.findElement(By.id("build-stage-input"));
        buildStageInput.clear();
        WebElement confirmButton = driver.findElement(By.id("confirm-build-stage"));

        // Stage 1: F50 + dagger, sword, horse, axe, lance
        buildStageInput.clear();
        buildStageInput.sendKeys("F50, D5, S10, H10, B15, L20");
        confirmButton.click();
        Thread.sleep(2000);
        // Stage 2: F70 + dagger, sword, horse, axe, lance
        buildStageInput.clear();
        buildStageInput.sendKeys("F70, D5, S10, H10, B15, L20");
        confirmButton.click();
        Thread.sleep(2000);

        // Assert P1 has 0 cards
        assertCardCount(1,0);

        // P2, P3 and P4 participate in stage 1
        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
        Thread.sleep(2000);

        WebElement discardCardsInput = driver.findElement(By.id("discard-cards-input"));
        discardCardsInput.clear();
        WebElement confirmSelectionButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm Selection')]"));
        // P2 participates discards F5
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P3 participates discards F15
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F15");
        confirmSelectionButton.click();
        Thread.sleep(2000);
        // P2 participates discards F10
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F10");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        WebElement buildAttackInput = driver.findElement(By.id("build-attack-input"));
        buildAttackInput.clear();
        WebElement confirmAttackButton = driver.findElement(By.xpath("//button[contains(text(), 'Confirm Attack')]"));

        // P2 attack: Excalibur
        buildAttackInput.clear();
        buildAttackInput.sendKeys("E30");
        confirmAttackButton.click();
        Thread.sleep(2000);


        // P3 plays nothing as attack
        buildAttackInput.clear();
        buildAttackInput.sendKeys(" ");
        confirmAttackButton.click();
        Thread.sleep(2000);

        // P4 plays nothing as attack
        buildAttackInput.clear();
        buildAttackInput.sendKeys(" ");
        confirmAttackButton.click();
        Thread.sleep(2000);


        // P1 discards 1xF5, 1x10
        discardCardsInput.clear();
        discardCardsInput.sendKeys("F5,F10");
        confirmSelectionButton.click();
        Thread.sleep(2000);

        // End Round
        driver.findElement(By.xpath("//button[contains(text(), 'End Round')]")).click();
        Thread.sleep(2000);

        // P1 Assertions
        // Assert P1 has 12 cards
        assertCardCount(1,12);
        // Assert P1 has no shields
        assertShieldCount(1,0);
        // Assert P1 hand is 1xF15, 4 daggers, 4 horses, 3 swords
        WebElement p1HandElement = driver.findElement(By.id("p1-hand"));
        String p1Hand = p1HandElement.getText();
        String expectedHand = "F15, D5, D5, D5, D5, S10, S10, S10, H10, H10, H10, H10";
        System.out.println("P1 Hand: " + p1Hand);
        Assertions.assertEquals(expectedHand, p1Hand, "Player 1's hand should match the expected cards.");
        Thread.sleep(2000);

        // P2 Assertions
        // Assert P2 has 11 cards
        assertCardCount(2,11);
        // Assert P2 has 0 shields
        assertShieldCount(2,0);
        // Assert P2 hand is 2xF5, 1xF10, 2xF15, 2xF20, 1xF25, 2xF30, 1xF40
        WebElement p2HandElement = driver.findElement(By.id("p2-hand"));
        String p2Hand = p2HandElement.getText();
        expectedHand =  "F5, F5, F10, F15, F15, F20, F20, F25, F30, F30, F40";
        System.out.println("P2 Hand: " + p2Hand);
        Assertions.assertEquals(expectedHand, p2Hand, "Player 2's hand should match the expected cards.");
        Thread.sleep(3000);

        // P3 Assertions
        // Assert P3 has 12 cards
        assertCardCount(3,12);
        // Assert P3 has no shields
        assertShieldCount(3,0);
        // Assert P3 hand is 2xF5, 1xF10, 2xF15, 2xF20, 2xF25, 1xF30, 1xF40, 1 lance
        WebElement p3HandElement = driver.findElement(By.id("p3-hand"));
        String p3Hand = p3HandElement.getText();
        expectedHand =  "F5, F5, F10, F15, F15, F20, F20, F25, F25, F30, F40, L20";
        System.out.println("P3 Hand: " + p3Hand);
        Assertions.assertEquals(expectedHand, p3Hand, "Player 3's hand should match the expected cards.");
        Thread.sleep(2000);

        // P4 Assertions
        // Assert P4 has 12 cards
        assertCardCount(4,12);
        // Assert P4 has 0 shields
        assertShieldCount(4,0);
        // Assert P4 hand is 2xF5, 1xF10, 2xF15, 2xF20, 2xF25, 1xF30, 1xF50, 1 excalibur
        WebElement p4HandElement = driver.findElement(By.id("p4-hand"));
        String p4Hand = p4HandElement.getText();
        expectedHand =  "F5, F5, F10, F15, F15, F20, F20, F25, F25, F30, F50, E30";
        System.out.println("P4 Hand: " + p4Hand);
        Assertions.assertEquals(expectedHand, p4Hand, "Player 4's hand should match the expected cards.");
        Thread.sleep(3000);

        // Assert absence of winners
        WebElement gameLogElement = driver.findElement(By.id("game-log"));
        String gameLogContent = gameLogElement.getText();
        System.out.println("Game Log Content: " + gameLogContent);
        String expectedMessage = "[Game] No winners yet. The game continues!";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "The game log should contain the expected message: " + expectedMessage);

        expectedMessage = "[Game] Non-Winner(s): P1, P2, P3, P4.";
        Assertions.assertTrue(gameLogContent.contains(expectedMessage),
                "P1, P2, P3, P4 should not be winners: " + expectedMessage);
    }

    }
