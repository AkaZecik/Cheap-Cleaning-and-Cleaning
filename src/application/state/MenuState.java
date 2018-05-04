package application.state;

import application.ApplicationManager;

public class MenuState implements ApplicationState {
    protected MenuState() {
    }

    public static MenuState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(ApplicationManager am) {

    }

    @Override
    public ApplicationState handleInput(ApplicationManager am, String str) {
        return null;
    }

    @Override
    public void update(ApplicationManager am) {

    }

    @Override
    public void render() {

    }

    @Override
    public void exit() {

    }

    private static class InstanceHolder {
        public static MenuState instance = new MenuState();
    }
}
