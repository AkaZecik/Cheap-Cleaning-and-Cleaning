package application.state;

import application.ApplicationManager;

public interface ApplicationState {
    void enter(ApplicationManager am);

    ApplicationState handleInput(ApplicationManager am, String str);

    void update(ApplicationManager am);

    void render();

    void exit();
}
