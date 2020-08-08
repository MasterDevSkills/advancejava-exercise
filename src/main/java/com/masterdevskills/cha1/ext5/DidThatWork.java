package com.masterdevskills.cha1.ext5;

public class DidThatWork {
    public static void main(String[] args) {
        Log logger = Logger.getLogger();

        logger.enableLogging();
        User user = new User("Bazlur", "Rahman");
        // logger.info("user status:  {}", getUserStatus(user));
        logger.info("user status:  {}", () -> new String[]{getUserStatus(user)});
    }

    private static String getUserStatus(final User user) {
        System.out.println("Preparing user status");

        return user.toString();
    }

    static class User {
        final String firstName;
        final String lastName;

        public User(final String firstName, final String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {

            return firstName + " " + lastName;
        }
    }
}
