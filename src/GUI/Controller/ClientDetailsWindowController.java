package GUI.Controller;

import GUI.View.ClientDetailsWindow;

public class ClientDetailsWindowController {
    private ClientDetailsWindow clientDetailsWindow;

    public ClientDetailsWindowController() {
        this.clientDetailsWindow = new ClientDetailsWindow();

    }

    public ClientDetailsWindow getClientDetailsWindow() {
        return clientDetailsWindow;
    }
}
