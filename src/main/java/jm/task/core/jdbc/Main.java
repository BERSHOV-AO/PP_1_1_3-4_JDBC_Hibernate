package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Bilbo", "Baggins", (byte) 25);
        userService.saveUser("Samwise", "Gamgee", (byte) 29);
        userService.saveUser("Peregrin", "Took", (byte) 31);
        userService.saveUser("Meriadoc", "Brandybuck", (byte) 27);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
