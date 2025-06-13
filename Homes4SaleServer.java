import java.net.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Homes4SaleServer {
    public static boolean connectionMade = false;
    public static String logUsername;
    public static String logPassword;
    public static String logRole;
    public static boolean loggedIn = false;
    public static boolean atWebsite = false;
    public static int lineCount = 0;
    public static ArrayList<String> accList = new ArrayList<>();

    public static void main (String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            JOptionPane.showMessageDialog(null, "Server created!\nHost: \"localhost\"\nPort: 1234",
                    "Server", JOptionPane.INFORMATION_MESSAGE);
            Socket socket = serverSocket.accept();
            serverSocket.close();
            socket.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Couldn\'t create server...",
                    "Server", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void signUp() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File("accounts.txt")));
            boolean accCheck = false;
            String username;
            String password;
            String line;
            String role;
            while (true) {
                username = JOptionPane.showInputDialog(null, "Enter a username.",
                        "Homes4Sale", JOptionPane.QUESTION_MESSAGE);
                password = JOptionPane.showInputDialog(null, "Enter a password.",
                        "Homes4Sale", JOptionPane.QUESTION_MESSAGE);
                while ((line = bfr.readLine()) != null) {
                    if (line.contains(username)) {
                        JOptionPane.showMessageDialog(null, "Account already exists!",
                                "Homes4Sale",
                                JOptionPane.ERROR_MESSAGE);
                        accCheck = true;
                        break;
                    }
                }
                if (accCheck == false) {
                    break;
                } else {
                    accCheck = false;
                }
            }
            FileWriter fw = new FileWriter(new File("accounts.txt"), true);
            while (true) {
                role = JOptionPane.showInputDialog(null, "Enter a role.",
                        "Homes4Sale", JOptionPane.QUESTION_MESSAGE);
                role = role.toLowerCase();
                if ((role.equals("buyer")) || (role.equals("seller"))) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid role!", "Homes4Sale",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            String info = username + "," + password + "," + role;
            fw.write(info + "\n");
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "Account created! Please log in again.",
                    "Home4Sale", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logIn() {
        try {
            // gets every account in the file registry
            BufferedReader bfr = new BufferedReader(new FileReader(new File("accounts.txt")));
            lineCount = 0;
            while (bfr.readLine() != null) {
                lineCount++;
            }
            bfr.close();
            BufferedReader bfr2 = new BufferedReader(new FileReader(new File("accounts.txt")));
            for (int i = 0; i < lineCount; i++) {
                accList.add(bfr2.readLine());
            }
            bfr2.close();

            while (true) {
                logUsername = JOptionPane.showInputDialog(null, "Enter your username.",
                        "Homes4Sale", JOptionPane.QUESTION_MESSAGE);
                logPassword = JOptionPane.showInputDialog(null, "Enter your password.",
                        "Homes4Sale", JOptionPane.QUESTION_MESSAGE);
                if (accList.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Sorry! No accounts registerd. Aborting...",
                            "Homes4Sale", JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    for (int i = 0; i < accList.size(); i++) {
                        String[] userInfo = accList.get(i).split(",");
                        if (userInfo[0].equals(logUsername) && userInfo[1].equals(logPassword)) {
                            loggedIn = true;
                            logRole = userInfo[2];
                            JOptionPane.showMessageDialog(null, "You are now logged in as \"" + logUsername + "\"!",
                                    "Home4Sale", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    if (!(loggedIn)) {
                        JOptionPane.showMessageDialog(null, "Account does not exist!", "Homes4Sale",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changePassword() {
        try {
            // to do
            atWebsite = true;
            while (true) {
                String pass = JOptionPane.showInputDialog(null,
                        "Please enter your current password: ", "Homes4Sale",
                        JOptionPane.QUESTION_MESSAGE);
                if (pass.equals(logPassword)) {
                    String new_pass = JOptionPane.showInputDialog(null,
                            "Enter your new password: ", "Homes4Sale",
                            JOptionPane.QUESTION_MESSAGE);
                    if (!(new_pass.equals(pass))) {
                        for (int i = 0; i < lineCount; i++) {
                            String[] info = accList.get(i).split(",");
                            if (info[0].equals(logUsername)) {
                                info[1] = new_pass;
                                accList.set(i, info[0] + "," + info[1] + "," + info[2]);
                                break;
                            }
                        }
                        FileWriter fw2 = new FileWriter(new File("accounts.txt"));
                        fw2.write("");
                        fw2.close();
                        FileWriter fw3 = new FileWriter(new File("accounts.txt"), true);
                        for (int i = 0; i < accList.size(); i++) {
                            fw3.write(accList.get(i) + "\n");
                        }
                        fw3.close();
                        break;
                    } else {
                        JOptionPane.showInputDialog(null,
                                "Uh oh! New password cannot be the same as old password!", "Homes4Sale",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showInputDialog(null,
                            "Uh oh! Incorrect password!", "Homes4Sale", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAcc() {
        try {
            // to do
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
