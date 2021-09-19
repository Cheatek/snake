package pl.sdacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

public class SnakeGameJavaFxPrinter implements SnakeGamePrinter {
    private GraphicsContext graphicsContext;
    private int pointSize;

    public SnakeGameJavaFxPrinter(GraphicsContext graphicsContext, int pointSize) {
        this.graphicsContext = graphicsContext;
        this.pointSize = pointSize;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public int getPointSize() {
        return pointSize;
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }

    public void drawPoint(Point point, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(point.getX()*pointSize, point.getY()*pointSize, pointSize, pointSize);
    }

    public void print(SnakeGame snakeGame) throws InterruptedException {
        Snake snake = snakeGame.getSnake();
        Point apple = snakeGame.getApple();
        Point snakeHead = snake.getHead();
        graphicsContext.setFill(Color.LIGHTBLUE);
        graphicsContext.fillRect(0, 0, snakeGame.getxBound()*pointSize+pointSize, snakeGame.getyBound()*pointSize+pointSize);
        for(Point body: snake.getBody()){
            drawPoint(body, Color.GREEN);
        }
        drawPoint(snakeHead, Color.BROWN);
        drawPoint(apple, Color.RED);
    }

}
