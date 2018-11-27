package nju.huluwa;

public class Main {

    public static void main(String[] args) {


        Ground g = new Ground(10);
        try{

            SnakeFormation snake = new SnakeFormation("🍎");
            GooseFormation goose = new GooseFormation("🐸");

            g.layout(snake, new Location(2,0));

            g.layout(goose, new Location(2,6));


            System.out.print(g);

        }catch ( Exception e){
            e.printStackTrace();
        }

    }
}
