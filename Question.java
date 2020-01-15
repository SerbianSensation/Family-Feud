/**
 * Question proposed to players to guess one of the answers on the board
 *
 * Each question has 5 possible answers
 *
 * The top answer to each question is stored at Answers(0) and is worth the most
 * The last answer is stored at Answers(4) and is worth the least
 *
 *
 * @author Stefan Gligorevic
 */
import java.util.ArrayList;
import java.util.Random;
import java.lang.String;
import java.lang.Object;

public class Question {

    private ArrayList<Answer> answers;
    private String question;

    /**** MIGHT HAVE TO INITIALIZE ANSWERS TO AN ARRAY LIST WITH NO SIZE SO ADD CAN WORK ****/
    //constructors
    public Question(){
        answers = new ArrayList<>();
        question = getRandomQuestion();
    }

    //makes a random question with a set of answers
    public String getRandomQuestion() {
        String ask = "";

        Random rand = new Random();
        int n = 1 + rand.nextInt(10);

        switch (n)
        {
            case 1:
                ask="Name a place where a child might get seperated from its parents:";
                answers.add(new Answer("Mall", 38));
                answers.add(new Answer("Park", 23));
                answers.add(new Answer("Zoo", 16));
                answers.add(new Answer("Theme Park", 16));
                answers.add(new Answer("Airport", 5));
                break;
            case 2:
                ask="Name something for which you need a warranty:";
                answers.add(new Answer("Car", 54));
                answers.add(new Answer("TV", 23));
                answers.add(new Answer("Watch", 8));
                answers.add(new Answer("Computers", 4));
                answers.add(new Answer("Appliance", 3));
                break;
            case 3:
                ask="Name a fruit you can buy dried:";
                answers.add(new Answer("Grape", 22));
                answers.add(new Answer("Banana", 21));
                answers.add(new Answer("Apricot", 21));
                answers.add(new Answer("Prune", 17));
                answers.add(new Answer("Apple", 15));
                break;
            case 4:
                ask="Name an activity that could be rained out:";
                answers.add(new Answer("Sports Event", 45));
                answers.add(new Answer("Picnic", 34));
                answers.add(new Answer("Wedding", 10));
                answers.add(new Answer("Concert", 7));
                answers.add(new Answer("Barbecue", 3));
                break;
            case 5:
                ask="What accent might an American pretend to have in order to sound more attractive:";
                answers.add(new Answer("French", 61));
                answers.add(new Answer("British", 18));
                answers.add(new Answer("Italian", 8));
                answers.add(new Answer("Spanish", 8));
                answers.add(new Answer("Australian", 3));
                break;
            case 6:
                ask="Name a sport that might be played at a family reunion:";
                answers.add(new Answer("Football", 54));
                answers.add(new Answer("Baseball", 21));
                answers.add(new Answer("Horseshoe", 8));
                answers.add(new Answer("Frisbee", 7));
                answers.add(new Answer("Basketball", 6));
                break;
            case 7:
                ask="Name a game that would be inappropriate at a company party:";
                answers.add(new Answer("Spin the Bottle", 41));
                answers.add(new Answer("Strip Poker", 32));
                answers.add(new Answer("Twister", 11));
                answers.add(new Answer("Truth or Dare", 11));
                answers.add(new Answer("Beer Pong", 3));
                break;
            case 8:
                ask="Name something that would get you thrown out of most bars:";
                answers.add(new Answer("Getting in a fight", 45));
                answers.add(new Answer("Drinking too much", 29));
                answers.add(new Answer("Not Paying", 6));
                answers.add(new Answer("Stripping", 5));
                answers.add(new Answer("Being underage", 3));
                break;
            case 9:
                ask="Name something you would see on the Jerry Springer show:";
                answers.add(new Answer("Fighting", 56));
                answers.add(new Answer("Nudity", 22));
                answers.add(new Answer("Security", 6));
                answers.add(new Answer("Jerry Springer", 4));
                answers.add(new Answer("Chairs Thrown", 3));
                break;
            case 10:
                ask="Name a breed of dog that might be used as a guard dog:";
                answers.add(new Answer("German Shepard", 36));
                answers.add(new Answer("Pit Bull", 23));
                answers.add(new Answer("Doberman Pinscher", 20));
                answers.add(new Answer("Rottweiler", 8));
                answers.add(new Answer("Bulldog", 5));
                break;
            default:
                //won't reach this hehe
        } //end switch
        return ask;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;

        if(!Question.class.isAssignableFrom(obj.getClass()))
            return false;

        final Question q = (Question) obj;

        if(q.getQuestion() == null || this.getQuestion() == null)
            return false;

        if(!this.getQuestion().equalsIgnoreCase(q.getQuestion()))
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return question.hashCode();
    }

    //getters and setters
    public String getQuestion() { return question; }
    public ArrayList<Answer> getAnswers() { return answers; }

    //returns answer at given index
    public Answer answerAt(int index) { return answers.get(index); }
    //returns String of answer at specified index
    public String getAnswerAt(int index) { return answers.get(index).getAnswer(); }
    //returns point value of answer at specified index
    public int getAnswerPoints(int index) { return answers.get(index).getValue(); }

    //returns the points earned for this question
    //points are the point values of all the answers that have been found
    public int pointsEarned() {
        int points=0;
        for (int i=0; i<answers.size(); i++)
        {
            if(answers.get(i).isFound())
                points += answers.get(i).getValue();
        }
        return points;
    }

    //Sets a particular answer's value
    public void setAnswerVal(int index, int val) { answers.get(index).setValue(val); }

    //Add a multiplier to each answer's value for the question
    public void addMultiplier(int multiplier)
    {
        for(int i=0; i<answers.size(); i++)
        {
            int val = answers.get(i).getValue();
            answers.get(i).setValue(multiplier * val);
        }
    }

    //returns string of each answer
    public String topAnswer() { return answers.get(0).getAnswer(); }
    public String Answer2() { return answers.get(1).getAnswer(); }
    public String Answer3() { return answers.get(2).getAnswer(); }
    public String Answer4() { return answers.get(3).getAnswer(); }
    public String lastAnswer() { return answers.get(4).getAnswer(); }

    //returns values of each answer
    public int topAnswerVal() { return answers.get(0).getValue(); }
    public int Answer2Val() { return answers.get(1).getValue(); }
    public int Answer3Val() { return answers.get(2).getValue(); }
    public int Answer4Val() { return answers.get(3).getValue(); }
    public int lastAnswerVal() { return answers.get(4).getValue(); }

}
