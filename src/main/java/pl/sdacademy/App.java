package pl.sdacademy;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {
    private Canvas canvas;
    private EventHandler<KeyEvent> keyListener;
    public void start(Stage stage) {
        canvas = new Canvas(800, 800);
        HBox hBox = new HBox(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        SnakeGameJavaFxPrinter fxPrinter = new SnakeGameJavaFxPrinter(graphicsContext, 20);
        Point head = new Point(5, 5);
        List<Point> body = Arrays.asList(
                new Point(5,4),
               new Point(5,3),
               new Point(5,2)
        );
        SnakeGame snakeGame = new SnakeGame(20,20,head,body, Dir.DOWN, fxPrinter);
        keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                    snakeGame.getSnake().setDirection(Dir.UP);
                }else if(event.getCode() == KeyCode.DOWN){
                    snakeGame.getSnake().setDirection(Dir.DOWN);
                }else if(event.getCode() == KeyCode.LEFT){
                    snakeGame.getSnake().setDirection(Dir.LEFT);
                }else if(event.getCode() == KeyCode.RIGHT){
                    snakeGame.getSnake().setDirection(Dir.RIGHT);
                }
                event.consume();
            }
        };
        Scene scene = new Scene(hBox);
        scene.setOnKeyPressed(keyListener);
        stage.setScene(scene);
        stage.show();
        Thread thread = new Thread(snakeGame::start);
        thread.setDaemon(true);
        thread.start();
    }

    public static void main(String[] args) {
        launch();
    }

}
