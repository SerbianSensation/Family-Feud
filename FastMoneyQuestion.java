/**
 * Fast money question is a slight variation of a regular question
 *
 * There are only a few possible answers worth different types of points
 *
 * @author Stefan Gligorevic
 */
import java.util.ArrayList;
import java.util.Random;
import java.lang.String;
import java.lang.Object;

public class FastMoneyQuestion {

    String question;
    ArrayList<Answer> answers;

    public FastMoneyQuestion()
    {
        answers = new ArrayList<>();
        question = RandomQuestion();
    }

    /* returns a random fast money question */
    public String RandomQuestion()
    {
        String ask = "";

        Random rand = new Random();
        int n = 1 + rand.nextInt(10);

        switch (n)
        {
            case 1:
                ask="Besides Books, Name Something You’d Find In Most School Libraries:";
                answers.add(new Answer("Computers", 40));
                answers.add(new Answer("Magazines", 21));
                answers.add(new Answer("Librarian", 20));
                answers.add(new Answer("Chairs", 5));
                answers.add(new Answer("Tables", 5));
                break;
            case 2:
                ask="Name An Age That People Worry About Turning:";
                answers.add(new Answer("40", 42));
                answers.add(new Answer("50", 31));
                answers.add(new Answer("30", 23));
                answers.add(new Answer("60", 3));
                break;
            case 3:
                ask="Besides sand, name something you would need to make a sand castle:";
                answers.add(new Answer("Water", 56));
                answers.add(new Answer("Bucket", 25));
                break;
            case 4:
                ask="Name a celebrity who is often known by one name:";
                answers.add(new Answer("Madonna", 30));
                answers.add(new Answer("Drake", 45));
                answers.add(new Answer("Future", 40));
                answers.add(new Answer("Usher", 10));
                answers.add(new Answer("Beyonce", 30));
                break;
            case 5:
                ask="Name a flavor found in an ice cream shop:";
                answers.add(new Answer("Vanilla", 51));
                answers.add(new Answer("Chocolate", 29));
                answers.add(new Answer("Strawberry", 8));
                break;
            case 6:
                ask="Name a job where you might study DNA:";
                answers.add(new Answer("Scientist", 46));
                answers.add(new Answer("Detective", 13));
                break;
            case 7:
                ask="Name a sport where the players have very little clothing:";
                answers.add(new Answer("Swimming", 44));
                answers.add(new Answer("Wrestling", 23));
                answers.add(new Answer("Water Polo", 12));
                break;
            case 8:
                ask="Name a word that follows the word 'holy':";
                answers.add(new Answer("Spirit", 45));
                answers.add(new Answer("Cow", 33));
                answers.add(new Answer("Moly", 28));
                answers.add(new Answer("Crap", 5));
                break;
            case 9:
                ask="Name something people drink beer out of:";
                answers.add(new Answer("Can", 38));
                answers.add(new Answer("Bottle", 22));
                answers.add(new Answer("Keg", 20));
                answers.add(new Answer("Cup", 15));
                break;
            case 10:
                ask="On a scale of 1-10, how good are you at keeping a secret?";
                answers.add(new Answer("10", 31));
                answers.add(new Answer("5", 23));
                answers.add(new Answer("8", 8));
                answers.add(new Answer("7", 10));
                answers.add(new Answer("9", 9));
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

        if(!FastMoneyQuestion.class.isAssignableFrom(obj.getClass()))
            return false;

        final FastMoneyQuestion q = (FastMoneyQuestion) obj;

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

    public String getQuestion() { return question; }

    //Searches to see if a guess matches one of the answers to the question
    public boolean search(String guess)
    {
        //goes through all answers
        for(int i=0; i<answers.size(); i++)
        {
            //searches to see if answer is on the board
            //also makes sure there the answer is not a duplicate
            if(answers.get(i).getAnswer().equalsIgnoreCase(guess) && !answers.get(i).isFound())
            {
                //if found, set answer to found and return true
                answers.get(i).setFound();
                return true;
            }
        }
        return false;
    }

    public int getPoints(String guess)
    {
        int points = 0;
        for(int i=0; i<answers.size(); i++)
        {
            if(answers.get(i).getAnswer().equalsIgnoreCase(guess))
            {
                points += answers.get(i).getValue();
                break;
            }
        }
        return points;
    }

    public int getIndex(String guess)
    {
        int index = -1;
        for(int i=0; i<answers.size(); i++)
        {
            if(answers.get(i).getAnswer().equalsIgnoreCase(guess))
            {
                index = i;
                break;
            }
        }
        return index;
    }

}
