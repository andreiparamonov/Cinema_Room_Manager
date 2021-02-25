package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    private static char[][] cinemaMap;
    private static final Scanner scanner = new Scanner(System.in);
    private static int totalTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;


    public static void main(String[] args) {
        // Write your code here
       initMap();
       runApp();
       scanner.close();
    }

    private static void runApp() {
        boolean isRunning = true;
        while (isRunning){
            System.out.println("\n1. Show the seats" +
                               "\n2. Buy a ticket" +
                               "\n3. Statistics" +
                               "\n0. Exit");
            int menuItem = scanner.nextInt();
            switch (menuItem){
                case 0: isRunning = false;
                break;
                case 1: printMap();
                break;
                case 2: buyTicket();
                break;
                case 3: showStatistics();
            }
        }
    }

    private static void showStatistics() {

        double fillPercent = (double) totalTickets / (cinemaMap.length * cinemaMap[0].length) * 100;
        System.out.println();
        System.out.printf("Number of purchased tickets: %d\n", totalTickets);
        System.out.printf("Percentage: %.2f%%\n", fillPercent);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    private static void buyTicket() {

        System.out.println();
        boolean notBayed = true;
        do{
            System.out.println("Enter a row number:");
            int rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNum = scanner.nextInt();
            try {
                if (cinemaMap[rowNum - 1][seatNum - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    cinemaMap[rowNum - 1][seatNum - 1] = 'B';
                    totalTickets++;
                    currentIncome += getTicketPrice(rowNum, seatNum);
                    System.out.printf("%s%d\n", "Ticket price: $", getTicketPrice(rowNum, seatNum));
                    notBayed = false;
                }
            }catch (Exception exception){
                System.out.println("\nWrong input!");
            }
        } while (notBayed);
    }

    private static int getTicketPrice(int rowNum, int seatNum) {
        return cinemaMap.length * cinemaMap[0].length <= 60 ? 10 : rowNum <= cinemaMap.length / 2 ? 10 : 8;
    }

    private static void printMap() {
        System.out.println("\nCinema:");
        System.out.print(" " + " ");
        for (int i = 0; i < cinemaMap[0].length; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < cinemaMap.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinemaMap[i].length; j++) {
                System.out.print(" " + cinemaMap[i][j]);
            }
            System.out.println();
        }
    }

    private static void initMap() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        setCinemaMap(new char[rows][seatsPerRow]);


        for (char[] seats: cinemaMap) {
            Arrays.fill(seats, 'S');
        }

        for (int i = 1; i < cinemaMap.length + 1; i++) {
            for (int j = 1; j < cinemaMap[0].length + 1; j++) {
                totalIncome += getTicketPrice(i, j);
            }
        }
    }

    public static void setCinemaMap(char[][] cinemaMap) {
        Cinema.cinemaMap = cinemaMap;
    }
}