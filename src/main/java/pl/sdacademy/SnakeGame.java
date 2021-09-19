package pl.sdacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {
    private int xBound;
    private int yBound;
    private Point apple;
    private Snake snake;
    private int eatenApples = 0;
    private boolean gameOn;
    private SnakeGamePrinter printer;
    Random rand = new Random();

    public SnakeGame(int xBound, int yBound, Point snakeHead, List<Point> snakeBody, Dir direction, SnakeGamePrinter snakeGamePrinter) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = new Snake(snakeHead, snakeBody, direction);
        this.printer = snakeGamePrinter;
    }

    public SnakeGame(int xBound, int yBound, Point snakeHead, Dir direction, SnakeGamePrinter snakeGamePrinter) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = new Snake(snakeHead, new ArrayList<Point>(), direction);
        this.printer = snakeGamePrinter;
    }

    public SnakeGame(int xBound, int yBound, SnakeGamePrinter snakeGamePrinter) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = new Snake(new Point(0, 0), new ArrayList<Point>(), Dir.RIGHT);
        this.printer = snakeGamePrinter;
    }

    public Snake getSnake() {
        return snake;
    }

    public int getxBound() {
        return xBound;
    }

    public int getyBound() {
        return yBound;
    }

    public Point getApple() {
        return apple;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void start() {

        gameValidator();
        generateApple();
        gameOn = true;
        try {
            while (true) {
                if (!appleValidator()) {
                    generateApple();
                    continue;
                }
                if (ifSnakeMeetsApple()) {
                    snake.expand();
                    generateApple();
                    eatenApples++;
                } else {
                    snake.expand();
                    snake.cutTail();
                }
                if (moveAtXValidator()) {
                    gameOn = false;
                    System.out.println("WYSZEDłES POZA PLANSZE");
                    break;
                } else if (moveAtXYValidator()) {
                    gameOn = false;
                    System.out.println("WYSZEDłES POZA PLANSZE");
                    break;
                }
                try {
                    Thread.sleep(150);
                    printer.print(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IllegalMoveException e) {
            gameOn = false;
            System.out.println("NIELEGALNY RUCH, GRA ZAKONCZONA");
        }
    }

    public void generateApple() {
        int randomX = rand.nextInt(xBound);
        int randomy = rand.nextInt(yBound);
        apple = new Point(randomX, randomy);
    }

    public boolean appleValidator() {
        return !snake.getHead().equals(apple) || !snake.getBody().contains(apple);
    }

    public boolean moveAtXValidator() {
        return (snake.getHead().getX() > xBound || snake.getHead().getX() < 0);
    }

    public boolean moveAtXYValidator() {
        return (snake.getHead().getY() > yBound || snake.getHead().getY() < 0);
    }

    public boolean ifSnakeMeetsApple() {
        return snake.getHead().getX() == apple.getX() && snake.getHead().getY() == apple.getY();
    }

    public void gameValidator() {
        this.snake.bodyValidator();
        if (xBound < 0 || yBound < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        Point tempPoint;
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < yBound; y++) {
            result.append("\n");
            for (int x = 0; x < xBound; x++) {
                tempPoint = new Point(x, y);
                if (snake.headConatins(tempPoint)) {
                    result.append("H");
                } else if (snake.bodyContains(tempPoint)) {
                    result.append("B");
                } else if (apple.equals(tempPoint)) {
                    result.append("A");
                } else {
                    result.append(".");
                }
            }
        }
        return result.toString();
    }
}
