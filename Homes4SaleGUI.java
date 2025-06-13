import java.net.*;
import javax.swing.*;

public class Homes4SaleGUI {
    private static int parsed = 0;
    private static String port;
    private static Socket socket;
    private static int choice = 0;
    private static String[] buttons;

    public static void main (String[] args) {
        // gets the port number to connect to
        do {
            port = JOptionPane.showInputDialog(null, "Enter Port Number",
                    "Homes4Sale", JOptionPane.QUESTION_MESSAGE);

            if ((port == null) || (port.isEmpty())) {
                JOptionPane.showMessageDialog(null, "Invalid Port number!", "Homes4Sale",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    parsed = Integer.parseInt(port);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid Port Number!", 
                        "Homes4Sale", JOptionPane.ERROR_MESSAGE);
                }
            }
        } while ((port == null) || (port.isEmpty()) || (parsed == 0));

        // creates the socket to connect to
        try {
            socket = new Socket("localhost", parsed);
            Homes4SaleServer.connectionMade = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not establish connection! Aborting...", "Homes4Sale",
                        JOptionPane.ERROR_MESSAGE);
        }

        // starts the log in process
        if (Homes4SaleServer.connectionMade) {
            JOptionPane.showMessageDialog(null, "Connection established! Welcome to Homes4Sale!",
                    "Home4Sale", JOptionPane.INFORMATION_MESSAGE);
            logInChoice();
        }

        // starts the account change process
        if (Homes4SaleServer.loggedIn) {
            accountInfoChange();
        }

        // actually puts you at the website
        if (Homes4SaleServer.atWebsite) {
            if (Homes4SaleServer.logRole.equals("seller")) {
                sellerActions();
            } else {
                buyerActions();
            }
        }

        // closes the socket and the program as a whole
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logInChoice() {
        buttons = new String[2];
        buttons[0] = "Log In";
        buttons[1] = "Sign Up";
        while (true) {
            choice = JOptionPane.showOptionDialog(null, "What would you like to do?", 
                        "Homes4Sale", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
                        buttons, buttons[0]);
            if (choice == JOptionPane.YES_OPTION) {
                Homes4SaleServer.logIn();
                break;
            } else if (choice == JOptionPane.NO_OPTION) {
                Homes4SaleServer.signUp();
            } else {
                JOptionPane.showMessageDialog(null, "Goodbye! Thank you for using Homes4Sale!",
                    "Home4Sale", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    public static void accountInfoChange() {
        buttons = new String[3];
        buttons[0] = "Take to Website";
        buttons[1] = "Change Password and Take to Website";
        buttons[2] = "Delete Account";
        choice = JOptionPane.showOptionDialog(null, "Please select an account option", "Homes4Sale",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
        if (choice == 0) {
            JOptionPane.showMessageDialog(null, "Okay! Thank you for using Homes4Sale!",
                "Home4Sale", JOptionPane.INFORMATION_MESSAGE);
            Homes4SaleServer.atWebsite = true;
        } else if (choice == 1) {
            Homes4SaleServer.changePassword();
        } else if (choice == 2) {
            Homes4SaleServer.deleteAcc();
        } else {
            JOptionPane.showMessageDialog(null, "Goodbye! Thank you for using Homes4Sale!",
                "Home4Sale", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void sellerActions() {
        /* to do
        * should just be whatever project 4 had
        * the functions for each option should be in the server so that it gets processed there
        * this is a requirement for this project
        * i tried my best with that with the logging in functionality */
    }

    public static void buyerActions() {
        /* to do
        * should just be whatever project 4 had
        * the functions for each option should be in the server so that it gets processed there
        * this is a requirement for this project
        * i tried my best with that with the logging in functionality */
    }
}
