package nonogramy.entity;

public class Tile {
    private int x; //Pozycja x
    private int y; //Pozycja y
    private boolean checked; //Wartość zaznaczenia

    //Konstruktor
    public Tile(int x, int y, boolean checked) {
        this.x = x;
        this.y = y;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    } //Zwraca wartość zaznaczenia

    public void setTile(boolean checked) {
        this.checked = checked;
    }
}
