/**
 * Creates a Host which can be used for a game show or any other show/event
 *
 * @author Stefan Gligorevic
 */
public class Host {

    private String name;

    public Host() { name = ""; }

    public Host(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void changeName(String name){
        this.name=name;
    }

}
