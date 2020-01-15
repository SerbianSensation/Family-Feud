/**
 * Every player gives an answer when asked a question
 *
 * Answers contain a string, as well as a point value
 * Also a boolean to indicate if the answer has already been given
 *
 * @author Stefan Gligorevic
 */

public class Answer {

    private String answer;
    private int value;
    private boolean found;

    //constructors
    public Answer(){
        answer="";
        value=0;
        found=false;
    }

    public Answer(String answer){
        this.answer = answer;
        value=0;
        found=false;
    }

    public Answer(String answer, int val){
        this.answer = answer;
        value = val;
        found=false;
    }

    //getters and setters
    public String getAnswer() { return answer; }
    public int getValue() { return value; }
    public void setValue(int val) { value = val; }
    public void setAnswer(String answer) { this.answer = answer; }
    public void setFound() { found = true; }
    public boolean isFound() { return found; }

}
