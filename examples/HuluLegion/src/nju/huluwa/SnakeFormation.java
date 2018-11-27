package nju.huluwa;

public class SnakeFormation extends Formation {
    public SnakeFormation(String matter) {
        super(7, 2);
        for (int i = 0; i < 7; i++) {
            this.content[i][0] = matter;
        }
    }
}
