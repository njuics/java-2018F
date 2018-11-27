public class Position {

    private int x;



    @Deprecated
    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
    }

    private Creature holder;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Position(int x){
        super();
        this.x = x;
    }
}
