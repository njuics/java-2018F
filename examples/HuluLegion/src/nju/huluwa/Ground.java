package nju.huluwa;

import java.util.ArrayList;
import java.util.List;

public class Ground {

    public static final String PLACE_HOLDER = "\uD83C\uDF3F";
    private int sideLength;


    private List exists;

    public Ground(int n) {
        this.sideLength = n;

        exists = new ArrayList();


    }

    public void layout(Formation formation, Location location) throws TooCrowdedException {

        if ((location.getX() + formation.getWidth() > sideLength)
                || (location.getY() + formation.getHeight() > sideLength))
            throw new TooCrowdedException("Out of bounds"); //out of bounds


        Location loc = formation.getLocation();

        formation.setLocation(location);


        for (Object exist : exists) {
            if (conflict((Formation) exist, formation)) {
                formation.setLocation(loc);
                throw new TooCrowdedException("Conflicted");
            }

        }


        exists.add(formation);
    }

    private boolean conflict(Formation f1, Formation f2) {
        if (contains(f1, f2))
            return true;

        if (contains(f2, f1))
            return true;

        if (overlap(f1, f2))
            return true;

        if (overlap(f2, f1))
            return true;

        return false;
    }

    private boolean contains(Formation f1, Formation f2) {
        if ((f1.getLocation().getX() >= f2.getLocation().getX())
                && (f1.getLocation().getY() >= f2.getLocation().getY())
                && (f1.getLocation().getX() + f1.getWidth() <= f2.getLocation().getX() + f2.getWidth())
                && (f1.getLocation().getY() + f1.getWidth() <= f2.getLocation().getY() + f2.getHeight())) {
            return true;
        }
        return false;
    }

    private boolean overlap(Formation f1, Formation f2) {

        if (((f1.getLocation().getX() + f1.getWidth() >= f2.getLocation().getX()))
                && (f1.getLocation().getY() + f1.getHeight() >= f2.getLocation().getY())
                && (f1.getLocation().getX() <= f2.getLocation().getX())
                && (f1.getLocation().getY() <= f2.getLocation().getY()))
            return true;
        return false;
    }

    public String toString() {


        String[][] content = new String[sideLength][sideLength];

        for (int i = 0; i < this.sideLength; i++) {
            for (int j = 0; j < this.sideLength; j++) {
                content[i][j] = PLACE_HOLDER;
            }
        }
        for (Object o : exists) {
            Formation f = (Formation) o;

            for (int i = 0; i < f.getWidth(); i++) {
                for (int j = 0; j < f.getHeight(); j++) {
                    content[i + f.getLocation().getX()][j + f.getLocation().getY()] = f.getContent()[i][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < content.length; j++) {
            for (int i = 0; i < content[j].length; i++) {
                sb.append(content[i][j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString();

    }


}
