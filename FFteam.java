/**
 * Creates a team for Family Feud
 *
 * Each team has a teamName
 * and 5 players with names
 *
 * Each team also has a point count
 * and a number of strikes
 *
 * Also keeps track of a current player for guessing
 *
 * Each team can either win or lose the game
 *
 * @author Stefan Gligorevic
 */
public class FFteam {

    private String teamName;

    private String[] members;

    private int points, strikes, current;

    private boolean victory;

    /*Constructors for creating a team*/

    public FFteam(){
        teamName = "";
        points=0;
        victory=false;
        strikes=0;
        members = new String[5];
        current=0;
    }

    public FFteam(String name){
        teamName=name;
        points=0;
        strikes=0;
        victory=false;
        members = new String[5];
        current=0;
    }

    public FFteam(String name, int pointCount){
        teamName=name;
        points=pointCount;
        victory=false;
        strikes=0;
        members = new String[5];
        current=0;
    }

    public FFteam(String name, String playerOne,String playerTwo,String playerThree,String playerFour,String playerFive){
        teamName = name;
        members = new String[5];
        members[0]=playerOne;
        members[1]=playerTwo;
        members[2]=playerThree;
        members[3]=playerFour;
        members[4]=playerFive;
        points=0;
        strikes=0;
        victory=false;
        current=0;
    }

    //copy the stats of a given team to another
    public void copy(FFteam family)
    {
        teamName = family.getTeamName();
        members = new String[5];
        members[0]=family.getPlayerOne();
        members[1]=family.getPlayerTwo();
        members[2]=family.getPlayerThree();
        members[3]=family.getPlayerFour();
        members[4]=family.getPlayerFive();
        points=family.getPoints();
        strikes=family.getStrikes();
        victory=family.isWinner();
        current=family.currentInt();
    }

    //Getters and setters
    public int getPoints(){
        return points;
    }

    public void addPoints(int point){
        points+=point;
    }

    public void changeTeamName(String name){
        teamName=name;
    }

    public int getStrikes() { return strikes; }

    public void addStrike() { ++strikes; }

    public void resetStrikes() { strikes=0; }

    public boolean isWinner() {
        if(points >= 300)
            victory = true;

        return victory;
    }

    public void makeWinner() { victory = true; }

    public String getCurrent() { return members[current]; }

    public void incCurrent()
    {
        if(current < 4)
            ++current;
        else
            current=0;
    }

    public int currentInt() { return current; }

    public void setCurrent(int index) { current = index; }

    public String getTeamName(){
        return teamName;
    }

    public void setPlayerOne(String name){ members[0]=name; }

    public void setPlayerTwo(String name){
        members[1]=name;
    }

    public void setPlayerThree(String name){
        members[2]=name;
    }

    public void setPlayerFour(String name){
        members[3]=name;
    }

    public void setPlayerFive(String name){
        members[4]=name;
    }

    public String getPlayerOne(){
        return members[0];
    }

    public String getPlayerTwo(){
        return members[1];
    }

    public String getPlayerThree(){
        return members[2];
    }

    public String getPlayerFour(){
        return members[3];
    }

    public String getPlayerFive(){
        return members[4];
    }

}
