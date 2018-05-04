package application;

import application.state.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Stack;

public class ApplicationManager extends Application {
    final private long tickrate = 32;
    final private long tickInterval = 1_000_000_000L / tickrate;
    final private int maxSkippedFrames = 5;
    final private Stack<ApplicationState> statesStack = new Stack<>();

    public static void main(String... args) {
        launch(args);
    }

    public void initialize(Stage primaryStage) {
        pushState(SplashScreenState.getInstance());
    }

    public void dispose(Stage primaryStage) {
        while (!statesStack.empty()) {
            popState();
        }
    }

    public void changeState(ApplicationState newState) {
        if (newState != null) {
            if (newState.getClass().equals(MenuState.class)) {
                popState();
                pushState(MenuState.getInstance());
            }

            if (newState.getClass().equals(PlayingState.class)) {
                while (!statesStack.empty()) {
                    popState();
                }

                pushState(PlayingState.getInstance());
            }

            if (newState.getClass().equals(ExitingState.class)) {
                while (!statesStack.empty()) {
                    popState();
                }

                pushState(ExitingState.getInstance());
            }
        }
    }

    public void pushState(ApplicationState newState) {
        if (newState != null) {
            statesStack.push(newState);
            newState.enter(this);
        }
    }

    public void popState() {
        statesStack.peek().exit();
        statesStack.pop();
    }

    public void handleInput() {
        changeState(statesStack.peek().handleInput(this, null));
    }

    public void update() {
        statesStack.peek().update(this);
    }

    public void render() {
        statesStack.peek().render();
    }

    public boolean isRunning() {
        return !statesStack.peek().getClass().equals(ExitingState.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize(primaryStage);

        long nextTick = System.nanoTime();

        while (isRunning()) {
            handleInput();

            for (int loops = 0; loops < maxSkippedFrames && System.nanoTime() > nextTick; ++loops, nextTick += tickInterval) {
                //System.out.println(System.nanoTime());
                update();
            }

            render();
        }

        dispose(primaryStage);
    }
}
