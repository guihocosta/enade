import java.util.ArrayList;

public class Sistema {
    private ArrayList<Area> areas;

    public Sistema() {
        this.areas = new ArrayList<>();
    }

    public void cadastrarArea(Area area) {
        this.areas.add(area);
    }

}
