package underground.university.com.lithium;


public class Building {
    int id;
    char letter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Building() {

    }

    public Building(char letter) {
        this.letter = letter;
    }

    public Building (int id, char letter) {
        this.id = id;
        this.letter = letter;
    }
}
