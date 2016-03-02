package underground.university.com.lithium;

public class Floor {

    int id;
    int number;
    public Floor() {

    }
    public Floor(int id, int number) {
        this.id = id;
        this.number = number;
    }

    public Floor(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
