package snakeandladder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private int size;
    private int dimension;
    private int[][] board;
    private Queue<Player> players;
    private List<Player> winners;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    Game(int n) {
        this.size = n;
        this.dimension = n*n;
        this.board = new int[n][n];
        this.players = new LinkedList<>();
        this.winners = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
    }

    private void initializeBoard() {
        // initialize the board with the values from 1 to n^2
        int c = -1, num = 1;
        for (int r=size-1;r>=0;r--) {
            if (c == -1) {
                c++;
                while (c < size) {
                    board[r][c] = num++;
                    c++;
                }
            } else {
                c--;
                while (c >= 0) {
                    board[r][c] = num++;
                    c--;
                }
            }
        }
    }

    public void addPlayers(List<Player> players) {
        this.players.addAll(players);
    }

    public void addSnakes(List<Snake> snakes) {
        this.snakes.addAll(snakes);
    }

    public void addLadders(List<Ladder> ladders) {
        this.ladders.addAll(ladders);
    }

    private void printBoard() {
        for (int r=0; r<size; r++) {
            for (int c=0; c<size; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    public void startGame() {
        initializeBoard();
        System.out.println("******* Board ***********\n\n");
        printBoard();

        while (players.size() > 1) {
            Player currPlayer = players.poll();
            String name = currPlayer.getName();
            // roll the dice
            int value = DiceService.rollDice();
            int currPos = currPlayer.getPosition();
            int newPos = currPos + value;
            if (newPos > dimension) {
                System.out.println(name + " cannot move to the new position " + newPos);
                players.offer(currPlayer);
            } else {
                // check if there is a snake at this position
                newPos = checkSnakePresent(newPos);
                // check if there is a ladder at this position
                newPos = checkLadderPresent(newPos);
                currPlayer.setPosition(newPos);
                System.out.println(name + " rolled a " + value + " and moved from " + currPos + " to " + newPos);
                if (newPos == this.dimension) {
                    System.out.println(name + " wins the game !!!");
                    winners.add(currPlayer);
                } else {
                    players.offer(currPlayer);
                }
            }
        }
    }

    private int checkSnakePresent(int position) {
        for (Snake snake : snakes) {
            if (snake.getHead() == position) {
                return snake.getTail();
            }
        }
        return position;
    }

    private int checkLadderPresent(int position) {
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position) {
                return ladder.getEnd();
            }
        }
        return position;
    }
}
