package snakeandladder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Board size: ");
        int n = sc.nextInt();
        Game game = new Game(n);

        System.out.println("No. of players: ");
        int noOfPlayers = sc.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i=0;i<noOfPlayers;i++) {
            System.out.println("Enter Player's " + (i+1) + " name: ");
            String name = sc.next();
            Player p = new Player(name, 0);
            players.add(p);
        }
        game.addPlayers(players);

        System.out.println("No. of snakes: ");
        int noOfSnakes = sc.nextInt();
        List<Snake> snakes = new ArrayList<>();
        for (int i=0;i<noOfSnakes;i++) {
            System.out.println("Enter the head position of snake " + (i+1) + ": ");
            int head = sc.nextInt();
            System.out.println("Enter the tail position of snake " + (i+1) + ": ");
            int tail = sc.nextInt();
            Snake snake = new Snake(head, tail);
            snakes.add(snake);
        }
        game.addSnakes(snakes);

        System.out.println("No. of ladders");
        int noOfLadders = sc.nextInt();
        List<Ladder> ladders = new ArrayList<>();
        for (int i=0;i<noOfLadders;i++) {
            System.out.println("Enter the start position of ladder " + (i+1) + ": ");
            int start = sc.nextInt();
            System.out.println("Enter the end position of ladder " + (i+1) + ": ");
            int end = sc.nextInt();
            Ladder ladder = new Ladder(start, end);
            ladders.add(ladder);
        }
        game.addLadders(ladders);
        game.startGame();
    }
}
