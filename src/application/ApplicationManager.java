package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationManager extends Application {
    final private long tickrate = 32;
    final private long tickInterval = 1_000_000_000L / tickrate;
    final private int maxSkippedFrames = 5;

    public static void main(String... args) {
        launch(args);
    }

    public void initialize(Stage primaryStage) {
    }

    public void dispose(Stage primaryStage) {
    }

    public void handleInput() {
    }

    public void update() {
    }

    public void render() {
    }

    public boolean isRunning() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize(primaryStage);

        long nextTick = System.nanoTime();

        while (isRunning()) {
            handleInput();

            for (int loops = 0; loops < maxSkippedFrames && System.nanoTime() > nextTick; ++loops, nextTick += tickInterval) {
                System.out.println(Logger.getReadableTime());
                update();
            }

            render();
        }

        dispose(primaryStage);
    }
}
