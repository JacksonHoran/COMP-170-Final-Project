public class EnemyEncounter {


    public static int points = 0; // Tracks player's points earned through correct answers
    public static int attemptCounter = 0; // Counts attempts per encounter
    public static boolean gameRunning = true; // Controls if the game is active
    public static int livesCounter; // tracks number of liveds player has left

    /* 
    helper method that initializes a count for the amount of lives the player gets.
    1 life for easy mode, 2 for medium and 3 lives for hard mode
    */
    public static void livesCounterInit() {
        if (PreGameInfo.difficulty == 1) { //if user selects easy mode
            livesCounter = 1; //add one to life counter
        } else if (PreGameInfo.difficulty == 2) { //if user selects medium mode
            livesCounter = 2; //add two to life counter
        } else { //if user selects hard mode
            livesCounter = 3; //add three to life counter
        }

    }

    /*
    method that simulates an encounter with a monster.
    
    initializes an attempt counter
    initaizes a correct answer boolean to false
    prints ASCII picture

    while correctAnswer is false, attemps are less than 4, and game is running
        increment attempt counter by one
        get mathproblem, 
            if user is correct
                call calculate points
                correctAnswer = true
                increment pathsTraversed by one
            if user is wrong and they reach max attempts
                inform user they are wrong and the monster won
                increment pathsTraversed by one
            else
                tell user theyre wrong, and get another math equation.
                
        if the user attempted 4 or more times, call loseLife()
    
    */
    public static void encounter(int difficulty) {
        attemptCounter = 0; // Resets attempts for the current encounter
        boolean correctAnswer = false;
        MonsterPrinter.printRandomMonster();

        while (!correctAnswer && attemptCounter < 4 && PreGameInfo.runGame) {
            attemptCounter++;
            // if user gets math problem calculate points based off attempts
            if (MathProblem.getMathProblem(difficulty)) {
                calculatePoints(); // Adds points based on number of attempts
                correctAnswer = true;
                Main.pathsTraversed += 1;
            } else if (attemptCounter == 4) {
                System.out.println("Incorrect! The Monster defeated you!");
                Main.pathsTraversed += 1;
            } else {
                System.out.println("Incorrect!");
                System.out.println();
            }
        }

        // Checks if the maximum attempts have been reached to lose a life
        if (attemptCounter >= 4) {
            loseLife();
        }
    }

    private static void loseLife() {
        livesCounter--; // Deduct a life

        if (livesCounter <= 0) {
            // Game over if no lives remain
            System.out.println();
            System.out.println("Game over, you've lost all your lives.");
            Main.pathsTraversed = 100;
        } else {
            // Print remaining lives if any are left
            System.out.println("The monster beat you, you have " + livesCounter + " lives left.");
            // Allow the game to continue by not changing the runGame flag
        }
    }


    private static void calculatePoints() {
        if (attemptCounter == 1) {
            points += 4;
        } else if (attemptCounter == 2) {
            points += 3;
        } else if (attemptCounter == 3) {
            points += 2;
        } else {
            points += 1;
        }
    }
}
