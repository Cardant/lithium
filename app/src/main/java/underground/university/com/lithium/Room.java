package underground.university.com.lithium;

public class Room {
    int id;
    String code;

    public Room() {
    }

    public Room(String code) {
        this.code = code;
    }

    public Room(String code, int id) {
        this.code = code;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
