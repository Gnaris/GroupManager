package sperias.group.Entity.Group;

public class Grade extends Group {

    public boolean isStaff;

    public Grade(int id, String name, String prefix, String color, boolean isStaff) {
        super(id, name, prefix, color);
        this.isStaff = isStaff;
    }

    public boolean isStaff() {
        return isStaff;
    }
}
