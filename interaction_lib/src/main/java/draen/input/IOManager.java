package draen.input;

public interface IOManager {

    void println(String str);
    void print(String str);

    void displayError(Exception e);
    void displayError(String str);

    String readLine();
}
