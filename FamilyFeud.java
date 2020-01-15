/**
 * Runs the Family Feud game
 *
 * Game consists of two teams
 * with 5 players each
 *
 * Each team also has a point count
 * First to 300 wins
 *
 * @author Stefan Gligorevic
 */
import java.util.*;

public class FamilyFeud {
    //includes host, both teams, set of questions to use
    private Host host;
    private FFteam team1;
    private FFteam team2;
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<FastMoneyQuestion> fastMoneyQuestions = new ArrayList<>();
    //Scanner for input
    private static Scanner sc = new Scanner(System.in);
    //To implement later :P
    private HUD hud;

    //constructor for family feud game
    public FamilyFeud(){
        host = new Host();
        team1 = new FFteam();
        team2 = new FFteam();
        //Populates questions list
        for(int i=0; i<25; i++)
        {
           Question q = new Question();
           //makes sure there are no duplicates
            if(!questions.contains(q))
                questions.add(q);
        }

        //Populates fast money questions
        for(int i=0; i<25; i++)
        {
            FastMoneyQuestion q = new FastMoneyQuestion();
            //makes sure there are no duplicates
            if(!fastMoneyQuestions.contains(q))
                fastMoneyQuestions.add(q);
        }

        //Prints questions to make sure no duplicates *For testing*
        //for (Question question : questions)
            //System.out.println(question.getQuestion());

    } //end constructor

    //runs game
    public static void main(String args[]) {
        //creates an instance of the family feud game
        FamilyFeud ff = new FamilyFeud();
        ff.intro();
        ff.round1();
        ff.round2();
        ff.round3();
        ff.suddenDeath();
        ff.fastMoney();
    }

    //intro to family feud
    public void intro(){
        System.out.println("Welcome to Family Feud! Input the name of the Host to begin: ");
        host.changeName(sc.nextLine());
        System.out.println("Please insert the name of the first family: ");
        team1.changeTeamName(sc.nextLine());
        System.out.println("Please insert the name of the second family: ");
        team2.changeTeamName(sc.nextLine());

        //switch statement to get the player names
        System.out.println("Now for the " + team1.getTeamName() + " family... ");
        for(int i=1; i<=5; i++)
        {
            switch (i)
            {
                case 1:
                    System.out.println("Please insert the name of the first player: ");
                    team1.setPlayerOne(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Please insert the name of the second player: ");
                    team1.setPlayerTwo(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Please insert the name of the third player: ");
                    team1.setPlayerThree(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Please insert the name of the fourth player: ");
                    team1.setPlayerFour(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Finally, insert the name of the fifth player: ");
                    team1.setPlayerFive(sc.nextLine());
                    break;
                default:
                    //Won't ever reach this hehe
            }

        } //end for loop

        //switch statement to get the player names
        System.out.println("Now on to the " + team2.getTeamName() + " family... ");
        for(int i=1; i<=5; i++)
        {
            switch (i)
            {
                case 1:
                    System.out.println("Please insert the name of the first player: ");
                    team2.setPlayerOne(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Please insert the name of the second player: ");
                    team2.setPlayerTwo(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Please insert the name of the third player: ");
                    team2.setPlayerThree(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Please insert the name of the fourth player: ");
                    team2.setPlayerFour(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Finally, insert the name of the fifth player: ");
                    team2.setPlayerFive(sc.nextLine());
                    break;
                default:
                    //Won't ever reach this hehe
            }

        } //end 2nd for loop

        System.out.println();
        System.out.println("Hi! I'm your host " + host.getName() + ", and welcome to Family Feud!");
        System.out.println("To my right we have the " + team1.getTeamName() + " family.");
        System.out.println("And to my left we have the " + team2.getTeamName() + " family.");
    } //end intro

    public void round1(){
        String player1 = team1.getCurrent();
        String player2 = team2.getCurrent();

        Question question = questions.get(0);

        System.out.println();
        System.out.println("Let's get started. Give me " + player1 + " and give me " + player2);
        System.out.println("There are 5 answers up on the board, here is your question:");
        System.out.println();
        System.out.println(question.getQuestion());

        boolean valid = false;
        /* keep asking for the player buzzed until valid is given
         * this is in case they mess up, which would cause them to have to restart otherwise
        */
        while(!valid)
        {
            System.out.println("Who hit the buzzer first? " + player1 + " or " + player2 + "?");
            String first = sc.nextLine();

            if(first.equalsIgnoreCase(player1))
            {
                valid = true;
                atBuzzer(team1, team2, question);
            }
            else if(first.equalsIgnoreCase(player2))
            {
                valid = true;
                atBuzzer(team2, team1, question);
            }
            else
                System.out.println("That is not one of the two players at the buzzer, try again");
        } //end while !valid

        //Print points summary after the round
        printTeamScores();
        //removes the question that was just used from our list of questions
        questions.remove(question);
    } //end round1

    public void round2(){
        //Sets current players to go to buzzer
        team1.setCurrent(1);
        team2.setCurrent(1);
        //reset strikes
        team1.resetStrikes();
        team2.resetStrikes();

        String player1 = team1.getCurrent();
        String player2 = team2.getCurrent();

        Question question = questions.get(0);
        //multiplier of 2 to each answer value for round 2
        question.addMultiplier(2);

        System.out.println();
        System.out.println("Time for round 2. The points for this round are doubled. ");
        System.out.println("Give me " + player1 + " and give me " + player2);
        System.out.println("There are 5 answers up on the board, here is your question:");
        System.out.println();
        System.out.println(question.getQuestion());

        boolean valid = false;
        /* keep asking for the player buzzed until valid is given
         * this is in case they mess up, which would cause them to have to restart otherwise
         */
        while(!valid)
        {
            System.out.println("Who hit the buzzer first? " + player1 + " or " + player2 + "?");
            String first = sc.nextLine();

            if(first.equalsIgnoreCase(player1))
            {
                valid = true;
                atBuzzer(team1, team2, question);
            }
            else if(first.equalsIgnoreCase(player2))
            {
                valid = true;
                atBuzzer(team2, team1, question);
            }
            else
                System.out.println("That is not one of the two players at the buzzer, try again");
        } //end while !valid

        //announce if there is a winner
        if(team1.isWinner())
            System.out.println("The " + team1.getTeamName() + " family has won the game! ");
        else if (team2.isWinner())
            System.out.println("The " + team2.getTeamName() + " family has won the game! ");
        else

        //Print points summary after the round
        printTeamScores();
        //removes the question that was just used from our list of questions
        questions.remove(question);

    } //end round 2

    public void round3() {
        //if a team has won don't play the round
        if(team1.isWinner() || team2.isWinner())
            return;

        //Sets current players to go to buzzer
        team1.setCurrent(2);
        team2.setCurrent(2);
        //reset strikes
        team1.resetStrikes();
        team2.resetStrikes();

        String player1 = team1.getCurrent();
        String player2 = team2.getCurrent();

        Question question = questions.get(0);
        //multiplier of 3 to each answer value for round 3
        question.addMultiplier(3);

        System.out.println();
        System.out.println("Time for round 3. The points for this round are tripled. ");
        System.out.println("Give me " + player1 + " and give me " + player2);
        System.out.println("There are 5 answers up on the board, here is your question:");
        System.out.println();
        System.out.println(question.getQuestion());

        boolean valid = false;
        /* keep asking for the player buzzed until valid is given
         * this is in case they mess up, which would cause them to have to restart otherwise
         */
        while(!valid)
        {
            System.out.println("Who hit the buzzer first? " + player1 + " or " + player2 + "?");
            String first = sc.nextLine();

            if(first.equalsIgnoreCase(player1))
            {
                valid = true;
                atBuzzer(team1, team2, question);
            }
            else if(first.equalsIgnoreCase(player2))
            {
                valid = true;
                atBuzzer(team2, team1, question);
            }
            else
                System.out.println("That is not one of the two players at the buzzer, try again");
        } //end while !valid

        //announce if there is a winner
        if(team1.isWinner())
            System.out.println("The " + team1.getTeamName() + " family has won the game! ");
        else if (team2.isWinner())
            System.out.println("The " + team2.getTeamName() + " family has won the game! ");
        else
            System.out.println();

        //Print points summary after the round
        printTeamScores();
        //removes the question that was just used from our list of questions
        questions.remove(question);
    } //end round 3

    public void suddenDeath() {
        //if a team has won don't play the round
        if(team1.isWinner() || team2.isWinner())
            return;

        //Sets current players to go to buzzer
        team1.setCurrent(3);
        team2.setCurrent(3);
        //reset strikes
        team1.resetStrikes();
        team2.resetStrikes();

        String player1 = team1.getCurrent();
        String player2 = team2.getCurrent();

        Question question = questions.get(0);
        question.addMultiplier(4);

        System.out.println();
        System.out.println("We still do not have a winner, so we come to sudden death.");
        System.out.println("The points are quadrupled and only the top answer is accepted.");
        System.out.println("Give me " + player1 + " and give me " + player2);
        System.out.println("Here is your question: ");
        System.out.println();
        System.out.println(question.getQuestion());

        boolean valid = false;
        /* keep asking for the player buzzed until valid is given
         * this is in case they mess up, which would cause them to have to restart otherwise
         */
        while(!valid)
        {
            System.out.println("Who hit the buzzer first? " + player1 + " or " + player2 + "?");
            String first = sc.nextLine();

            if(first.equalsIgnoreCase(player1))
            {
                valid = true;
                playSuddenDeath(team1, team2, question);
            }
            else if(first.equalsIgnoreCase(player2))
            {
                valid = true;
                playSuddenDeath(team2, team1, question);
            }
            else
                System.out.println("That is not one of the two players at the buzzer, try again");
        } //end while !valid

    } //end sudden death

    //plays fast money with the winning team
    public void fastMoney()
    {
        FFteam winners;
        if(team1.isWinner())
            winners = team1;
        else
            winners = team2;

        System.out.println();
        System.out.println();
        //intro to fast money
        System.out.println("The " + winners.getTeamName() + " family has won the game, which means they are going to play fast money!");
        System.out.println("Two of you will join me and give your best answers in an attempt to try to score 200 points. If you succeed, you will get $20,000!");
        System.out.println("You may not repeat each others answers, if you do, you will be alerted and must guess something else. Ready?");
        System.out.println("Alright " + winners.getPlayerOne() + ", give us the name of the first player from your team to play fast money:");
        String player1 = sc.nextLine();
        System.out.println("And now the name of the second player to play fast money:");
        String player2 = sc.nextLine();

        playFastMoney(winners, player1, player2);

        System.out.println();
        System.out.println("Thank you for playing family feud with me! I hope to see you soon. And from your host " + host.getName() + ", good night.");

    } //end sudden death

    //simulates what happens when two players are at the buzzer
    public void atBuzzer(FFteam teamBuzzed, FFteam otherTeam, Question question)
    {
        //variables for convenience
        String buzzPlayer = teamBuzzed.getCurrent();
        String otherPlayer = otherTeam.getCurrent();

        System.out.println("Alright " + buzzPlayer + ", " + question.getQuestion());
        String answer = sc.nextLine();

        int index, index2;
        String guess;

        //if answer is found, print it and go from there
        if(search(question, answer))
        {
            index = getIndex(question, answer);
            printBoard(question);
            //if top answer, print it and ask player to pass or play
            if((index+1) == 1)
            {
                System.out.println("That's the top answer!");
                passOrPlay(teamBuzzed, otherTeam, question);
            }
            //else print answer and let other player guess
            else
            {
                System.out.println("It's on the board!");
                System.out.println(otherPlayer + ", what is your guess?");
                guess = sc.nextLine();

                //if other player's guess is found, compare to buzzPlayer's guess
                if(search(question, guess))
                {
                    index2 = getIndex(question, guess);
                    printBoard(question);
                    //if other player's answer has higher position, they choose whether to pass or play
                    if(index2 < index)
                    {
                        //if top answer print
                        if((index2+1) == 1)
                            System.out.println("That's the top answer!");
                        //else print on the board
                        else
                            System.out.println("It's on the board!");

                        passOrPlay(otherTeam, teamBuzzed, question);
                    } //end other player guess > buzzPlayer guess
                    //else buzzPlayer gets to choose pass or play
                    else
                    {
                        System.out.println("It's on the board! However, " + buzzPlayer + "'s answer is better. ");
                        passOrPlay(teamBuzzed, otherTeam, question);
                    }
                } //end if other player guess found
                //else if other player's guess isn't found, first player gets priority
                else
                {
                    System.out.println("Oh it's not on the board!");
                    passOrPlay(teamBuzzed, otherTeam, question);
                }

            } //end else not top answer
        } //end if found
        //else let 2nd player guess
        else
        {
            printBoard(question);
            System.out.println("Oof, it's not on the board.");
            System.out.println(otherPlayer + ", " + question.getQuestion());
            guess = sc.nextLine();

            //if answer is found, they get to pass or play since first player guessed wrong
            if(search(question, guess))
            {
                printBoard(question);
                index = getIndex(question, guess);
                if((index+1) == 1)
                    System.out.println("That's the top answer!");
                else
                    System.out.println("It's on the board!");

                passOrPlay(otherTeam, teamBuzzed, question);
            }
            //else not on board and repeat question with the next family member on each team until we get a valid answer
            else
            {
                printBoard(question);
                System.out.println("Not on the board either! We move on to the next family members.");
                teamBuzzed.incCurrent();
                otherTeam.incCurrent();
                atBuzzer(teamBuzzed, otherTeam, question);
            }
        } //end else let 2nd player guess
    } //end atBuzzer

    //plays a round, given the team that is guessing and the question
    public void play(FFteam team, FFteam otherTeam, Question question)
    {
        int points = question.pointsEarned();
        //the team has its turn until they hit 3 strikes
        while(team.getStrikes() < 3)
        {
            boolean answersLeft = answersLeft(question);
            //kick out if there are no answers left and reward points to team playing
            if(!answersLeft)
            {
                System.out.println("You've guessed all of the answers correctly! That's the end of the round, all the points go to the " + team.getTeamName() + " family!");
                team.addPoints(points);
                break;
            }
            //end game if team has won
            if(team.isWinner())
            {
                System.out.println("The " + team.getTeamName() + " family has won the game! ");
                return;
            }

            System.out.println(team.getCurrent() + ", " + question.getQuestion());
            String answer = sc.nextLine();

            boolean found = search(question, answer);
            if(found)
            {
                int index = getIndex(question, answer);
                points += question.getAnswerPoints(index);

                //alert if it is the top answer and print
                if((index+1) == 1)
                {
                    printBoard(question);
                    System.out.println("That's the top answer!");
                }
                //else say it is on the board and print
                else
                {
                    printBoard(question);
                    System.out.println("It's on the board! ");
                }

                //if a team reached 300, announce winner and break loop
                if(team.isWinner())
                {
                    System.out.println("The " + team.getTeamName() + " family has won the game! ");
                    return;
                }
                team.incCurrent();

            }//end if found
            else
            {
                team.addStrike();
                team.incCurrent();
                printBoard(question);
                System.out.println("That's not on the board, you have " + team.getStrikes() + " strike(s).");
            }
        } //end while strikes < 3
        team.resetStrikes();
        //check to see if all answers have been found
        if(answersLeft(question))
        {
            //if there are answers left, other team gets to steal
            System.out.println("Now the " + otherTeam.getTeamName() + " family gets a chance to steal!");
            System.out.println("Alright " + otherTeam.getPlayerOne() + ", " + question.getQuestion());
            String guess = sc.nextLine();

            //if the guess is there, reward otherTeam with all the points
            if(search(question, guess))
            {
                int index = getIndex(question, guess);
                points += question.getAnswerPoints(index);
                otherTeam.addPoints(points);
                printBoard(question);
                System.out.println("It's there! The " + otherTeam.getTeamName() + " family has earned all the points for this round!");
            }
            //if it's not there, first team keeps the points
            else
            {
                team.addPoints(points);
                printBoard(question);
                System.out.println("Oh, and it's not on the board! The " + team.getTeamName() + " family keeps the points for this round!");
            }
        } //end if answersLeft

    } //end play method

    //Runs the sudden death round
    public void playSuddenDeath(FFteam buzzed, FFteam other, Question question)
    {
        //maybe return if winner to avoid double printing

        System.out.println();
        System.out.println(buzzed.getCurrent() + ", " + question.getQuestion());
        String answer = sc.nextLine();

        //search first to not mess up printing the board
        search(question, answer);
        printSuddenDeathBoard(question);
        //if answer is the top answer, reward points to team that hit the buzzer
        if(getIndex(question, answer) == 0)
        {
            buzzed.addPoints(question.getAnswerPoints(0));
            System.out.println("That's the top answer! The " + buzzed.getTeamName() + " family have earned the points! ");
        }
        //else not top answer let other player guess
        else
        {
            System.out.println("It's not there!");
            System.out.println(other.getCurrent() + ", " + question.getQuestion());
            String guess = sc.nextLine();

            //search first to not mess up printing the board
            search(question, guess);
            printSuddenDeathBoard(question);
            //if other player's guess is top answer, rewards points to their team
            if(getIndex(question, guess) == 0)
            {
                other.addPoints(question.getAnswerPoints(0));
                System.out.println("That's the top answer! The " + other.getTeamName() + " family have earned the points! ");
            }
            //else give new question and guess with next players on each team
            else
            {
                System.out.println("It's not on the board!");
                System.out.println("Looks like it's time for a new question!");
                //increment player guessing to next players
                buzzed.incCurrent();
                other.incCurrent();
                //remove old question and get new question
                questions.remove(question);
                Question newQuestion = questions.get(0);
                //repeat with new question
                playSuddenDeath(buzzed, other, newQuestion);
            }

        } //end else not top answer

        //Announce a winner if either team has reached 300 (likely at this point)
        if(buzzed.isWinner())
        {
            System.out.println("The game is over! The " + buzzed.getTeamName() + " family are the winners!");
            printTeamScores();
            return;
        }
        else if(other.isWinner())
        {
            System.out.println("The game is over! The " + other.getTeamName() + " family are the winners!");
            printTeamScores();
            return;
        }
        //repeat sudden death with new question if neither team is at 300
        else
        {
            questions.remove(question);
            suddenDeath();
        }

    } //end playSuddenDeath

    //plays fast money given the winning team and 2 players playing
    public void playFastMoney(FFteam winner, String player1, String player2)
    {
        int points = 0;
        //stores player one's answers to make sure player2 doesn't duplicate
        ArrayList<String> guesses = new ArrayList<>();
        ArrayList<String> guesses2 = new ArrayList<>();
        //Stores questions
        ArrayList<FastMoneyQuestion> Qs = new ArrayList<>();
        //if a player duplicates they will see this:
        String duplicateAlert = "That answer is taken, please try again:";

        System.out.println();
        System.out.println(player1 + ", you are going to be first. Make sure " + player2 + " can't see the board or hear you.");

        //get fast money questions question
        Qs.add(fastMoneyQuestions.get(0));
        Qs.add(fastMoneyQuestions.get(1));
        Qs.add(fastMoneyQuestions.get(2));
        Qs.add(fastMoneyQuestions.get(3));
        Qs.add(fastMoneyQuestions.get(4));

        System.out.println();
        System.out.println(player1 + ", we are gonna start with you. There will be five questions, give me your best answer for each. Here we go.");

        //ask questions and get answers
        System.out.println(Qs.get(0).getQuestion());
        guesses.add(sc.nextLine());
        System.out.println(Qs.get(1).getQuestion());
        guesses.add(sc.nextLine());
        System.out.println(Qs.get(2).getQuestion());
        guesses.add(sc.nextLine());
        System.out.println(Qs.get(3).getQuestion());
        guesses.add(sc.nextLine());
        System.out.println(Qs.get(4).getQuestion());
        guesses.add(sc.nextLine());

        //get points for players guesses and print board
        System.out.println("Thank you for your answers " + player1 + ". Time to see how many points you earned.");
        points += getFastMoneyPoints(Qs, guesses);
        printFastMoneyBoard(Qs, guesses, guesses2);

        //if 200 has been reached, return
        if(points >= 200) {
            System.out.println("Wow! You have managed to get 200 points by yourself! Congratulations, your family has won $20,000!");
            return;
        }

        //extra space so next player guessing can't see previous answers
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        //end extra spacing

        //Second player guesses
        System.out.println("Now we move on to " + player2 + ". Bring them over to have a guess at the questions.");
        System.out.println("Alright " + player2 + ", you will be asked the same five questions but cannot repeat " + player1 + "'s answers.");
        System.out.println("If you do, you will see this: ");
        System.out.println();
        System.out.println(duplicateAlert);
        System.out.println();
        System.out.println("Ready? Here we go: ");

        String guess;
        /*ask questions and get answers
            makes sure there are no duplicates answers
         */
        for(int i=0; i<Qs.size(); i++)
        {
            System.out.println(Qs.get(i).getQuestion());
            guess = sc.nextLine();

            //if duplicate answer, keep asking till new answer is given
            while(guess.equalsIgnoreCase(guesses.get(i)))
            {
                System.out.println(duplicateAlert);
                guess = sc.nextLine();
            }

            guesses2.add(guess);
        } //end for ask questions and get answers

        //get points for players guesses and print board
        System.out.println("Thank you for your answers " + player2 + ". Time to see how many points you earned.");
        printFastMoneyBoard(Qs, guesses, guesses2);
        points += getFastMoneyPoints(Qs, guesses2);

        //if 200 has been reached, return
        if(points >= 200) {
            System.out.println("Congratulations! You and " + player1 + " have accumulated at least 200 points and have won your family $20,000!");
            return;
        }

        //if they haven't reached 200, award $5 per points
        System.out.println("Unfortunately you guys couldn't hit 200. However, you do get $5 per point, meaning your family has earned $" + (points*5) + "!");

    } //end playFastMoney

    //returns the amount of points earned for a players guesses to a set of fast money questions
    public int getFastMoneyPoints(ArrayList<FastMoneyQuestion> Qs, ArrayList<String> guesses)
    {
        int points = 0;
        if(guesses.size() >= 5) {
            points += Qs.get(0).getPoints(guesses.get(0));
            points += Qs.get(1).getPoints(guesses.get(1));
            points += Qs.get(2).getPoints(guesses.get(2));
            points += Qs.get(3).getPoints(guesses.get(3));
            points += Qs.get(4).getPoints(guesses.get(4));
        }
        return points;
    }

    //prints the board for fast money
    public void printFastMoneyBoard(ArrayList<FastMoneyQuestion> Qs, ArrayList<String> guesses, ArrayList<String> guesses2)
    {
        System.out.println();
        for(int i = 0; i < Qs.size(); i++)
        {
            String guess = guesses.get(i);
            String guess2;
            if(guesses2.size() == 0)
                guess2 = null;
            else
                guess2 = guesses2.get(i);

            if(guess2 == null)
                System.out.printf("%-20s %10d%n", guess, Qs.get(i).getPoints(guess));
            else
                System.out.printf("%-20s %10d %20s %10d%n", guess, Qs.get(i).getPoints(guess), guess2, Qs.get(i).getPoints(guess2));

            System.out.println();
        } //end for
        System.out.println("Total points: " + (getFastMoneyPoints(Qs, guesses) + getFastMoneyPoints(Qs, guesses2)));
        System.out.println();
    } //end printFastMoneyBoard

    public void passOrPlay(FFteam teamAsked, FFteam otherTeam, Question question)
    {
        System.out.println(teamAsked.getCurrent() + ", would you like to pass or play?");
        String prp = sc.nextLine();
        //increments current player for both teams
        teamAsked.incCurrent();
        otherTeam.incCurrent();
        //sets team to play based on if user chooses to pass or play
        if(prp.equalsIgnoreCase("play"))
            play(teamAsked, otherTeam, question);
        else
            play(otherTeam, teamAsked, question);
    }

    //helper method that searches to see if answer is on the board
    public boolean search(Question question, String answer)
    {
        //goes through all answers
        for(int i=0; i<question.getAnswers().size(); i++)
        {
            //searches to see if answer is on the board
            //also makes sure there the answer is not a duplicate
            if(question.getAnswerAt(i).equalsIgnoreCase(answer) && !question.answerAt(i).isFound())
            {
                //if found, set answer to found and return true
                question.answerAt(i).setFound();
                return true;
            }
        }
        return false;
    }

    //Returns true if there are still answers on the board
    public boolean answersLeft(Question question)
    {
        for(int i=0; i<question.getAnswers().size(); i++)
        {
            //if an answer that hasn't been given is found, return true
            if(!question.answerAt(i).isFound())
                return true;
        }
        return false;
    }

    //Gets the index of an answer to a particular question
    public int getIndex(Question question, String answer)
    {
        int index = -1;
        for(int i=0; i<question.getAnswers().size(); i++)
        {
            if(question.getAnswerAt(i).equalsIgnoreCase(answer))
            {
                index = i;
                break;
            }
        }
        return index;
    }

    //Prints board of answers
    public void printBoard(Question question)
    {
        int points = question.pointsEarned();
        System.out.println();
        //goes through answers for the question
        for(int i=0; i<question.getAnswers().size(); i++)
        {
            //if answer has been given, print the answer and points its worth
            //record the points earned to be printed later
            if(question.answerAt(i).isFound())
            {
                int pointVal = question.getAnswerPoints(i);
                System.out.printf("%1d %-1s %-25s %3d%n",(i + 1),". ",question.getAnswerAt(i), pointVal);
            }
            //else print only the number and hide the answer
            else
                System.out.println((i+1) + " . ");

            System.out.println();
        } //end for
        System.out.println();
        System.out.println("Points earned: " + points);
        System.out.println();
    }

    //prints board differently when it is sudden death
    public void printSuddenDeathBoard(Question question)
    {
        System.out.println();
        //if answer has been given, print the answer and points its worth
        if(question.answerAt(0).isFound())
        {
            int pointVal = question.getAnswerPoints(0);
            System.out.println("1. " + question.getAnswerAt(0) + "\t"  + pointVal);
        }
        else
            System.out.println("1. ");

        System.out.println();
    } //end printSuddenDeathBoard

    //prints a display of each family's points
    public void printTeamScores()
    {
        System.out.println();
        System.out.println("Here are the scores for each of the families");
        System.out.println(team1.getTeamName() + ": " + team1.getPoints());
        System.out.println(team2.getTeamName() + ": " + team2.getPoints());
        System.out.println();
    }

} //end Family Feud class

//Scrap code
/*
//prints the board for fast money
    public void printFastMoneyBoard(ArrayList<FastMoneyQuestion> Qs, ArrayList<String> guesses, int points)
    {
        System.out.println();
        for(int i = 0; i < Qs.size(); i++)
        {
            String guess = guesses.get(i);
            if(Qs.get(i).search(guess))
                System.out.println(guess + "\t" + Qs.get(i).getPoints(guess));
            else
                System.out.println(guess + "\t" + " 0");

            System.out.println();
        } //end for
        System.out.println("Total points: " + (points + getFastMoneyPoints(Qs, guesses)));
        System.out.println();
    } //end printFastMoneyBoard

    if(Qs.get(i).search(guess) && guess2 == null)
                System.out.println(guess + "\t" + Qs.get(i).getPoints(guess));
            else if(Qs.get(i).search(guess) && guess2 != null)
                System.out.println(guess + "\t" + Qs.get(i).getPoints(guess) + "\t" + guess2 + "\t" + Qs.get(i).getPoints(guess2));
            else if(!Qs.get(i).search(guess) && guess2 != null)
                System.out.println(guess + "\t" + "0 " + "\t" + guess2 + "\t" + Qs.get(i).getPoints(guess2));
            else
                System.out.println(guess + "\t" + " 0");

        //if t1p1 buzzes first
        if(first.equalsIgnoreCase(t1p1))
        {
            System.out.println("Alright " + t1p1 + ", " + question.getQuestion());
            String answer = sc.nextLine();

            //Searches to see if answer is on the board
            boolean found = search(question, answer);

            //if answer given is found then it is on the board
            if(found)
            {
                index = getIndex(question, answer);
                //guessed top answer
                if((index+1) == 1)
                {
                    printBoard(question);
                    System.out.println("That's the top answer!");
                    System.out.println(t1p1 + ", would you like to pass or play?");
                    passOrPlay = sc.nextLine();
                    //increments current player
                    team1.incCurrent();
                    //sets team to play based on if user chooses to pass or play
                    if(passOrPlay.equalsIgnoreCase("play"))
                        play(team1, team2, question);
                    else
                        play(team2, team1, question);

                } //end if guessed top answer
                //else if not top answer other player gets to guess
                else
                {
                    printBoard(question);
                    System.out.println("It's on the board! ");
                    System.out.println(t2p1 + ", what is your guess?");
                    String guess = sc.nextLine();

                    //if found, compare to see which answer has higher position
                    if(search(question, guess))
                    {
                        //whoever's answer has higher position gets to choose to pass or play
                        //if 2nd player guess has higher position than first player answer
                        if(getIndex(question, guess) < index)
                        {
                            //if top answer print it
                            if((index+1) == 1)
                            {
                                printBoard(question);
                                System.out.println("That's the top answer!");
                            }
                            //else print its on the board
                            else
                            {
                                printBoard(question);
                                System.out.println("It's on the board!");
                            }
                            //2nd player gets to choose pass or play
                            System.out.println(t2p1 + ", would you like to pass or play?");
                            passOrPlay = sc.nextLine();
                            //increments current player
                            team2.incCurrent();
                            //sets team to play based on if user chooses to pass or play
                            if(passOrPlay.equalsIgnoreCase("play"))
                                play(team2, team1, question);
                            else
                                play(team1, team2, question);

                        } //end if 2nd player guess > 1st player
                        //if 1st player answer still better, they choose
                        else
                        {
                            printBoard(question);
                            System.out.println("It's on the board! However, " + t1p1 + "'s answer is better.");
                            System.out.println(t1p1 + ", would you like to pass or play?");
                            passOrPlay = sc.nextLine();
                            //increments current player
                            team1.incCurrent();
                            team2.incCurrent();
                            //sets team to play based on if user chooses to pass or play
                            if(passOrPlay.equalsIgnoreCase("play"))
                                play(team1, team2, question);
                            else
                                play(team2, team1, question);
                        }

                    } //end if found
                    //else not found, first player chooses pass or play
                    else
                    {
                        printBoard(question);
                        System.out.println("Oh, it's not on the board.");
                        System.out.println(t1p1 + ", would you like to pass or play?");
                        passOrPlay = sc.nextLine();
                        //increments current player
                        team1.incCurrent();
                        //sets team to play based on if user chooses to pass or play
                        if(passOrPlay.equalsIgnoreCase("play"))
                            play(team1, team2, question);
                        else
                            play(team2, team1, question);
                    } //end else: not found

                } //end else: answer is not top answer
            } //end found true
            //else then not on board, player 2 guesses
            else
            {
                printBoard(question);
                System.out.println("Oh, it's not on the board. What is your guess, " + t2p1 + "?");
                String guess = sc.nextLine();
            } //end else not on board player 2 guesses

        } //end if t1p1 buzzes first
        //if t2p1 buzzes first
        else if(first.equalsIgnoreCase(t2p1))
        {

        } //end else if t2p1 buzzes first
        else{
            throw new IllegalArgumentException("That is not one of the players at the buzzer");
        } */