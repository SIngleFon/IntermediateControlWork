package Java.View;

import java.io.FileWriter;
import java.util.Scanner;

import Java.Controller.Controller;
import Java.Data.Shop;
import Java.Data.Toys;

public class UserView implements View {
    private Controller controller;

    @Override
    public int promptInt(String msg) {
        Scanner in = new Scanner(System.in);
        int key = -1;
        try {
            System.out.print(msg);
            key = in.nextInt();
        } catch (Exception ex) {
            System.out.println("Exception InputMismatch. Enter please ONLY INTEGER");

        }
        return key;
    }

    @Override
    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    @Override
    public void run() {
        Shop shop = new Shop();
        shop.addProduct(new Toys("Cucla", 2, 564));
        shop.addProduct(new Toys("AirBus", 3, 23));
        System.out.println("\033\143");
        boolean flag = true;
        while (flag) {

            System.out.println("---------\nMenu:\n1. Add product in shop\n2. Add Product in lottery\n" +
                    "3. View Product list\n4. View Lottery list\n5. Time To LoTTeRy =)\n6.Exit\n---------");
            int key = promptInt("Enter: ");
            switch (key) {
                case 1:
                    String[] arr = new String[] { "Name", "Count", "Weight" };
                    String[] temp = new String[3];
                    for (int i = 0; i < arr.length; i++) {
                        temp[i] = prompt("Enter " + arr[i] + ": ");}
                    shop.addProduct(new Toys(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
                    break;
                case 2:
                    System.out.println("\033\143" + "\nAdd Product in lottery");
                    for (Toys line : shop.getProductList()) {
                        System.out.println(line);
                    }
                    int k = promptInt("Enter ID: ");
                    shop.addLotteryProduct(k);
                    break;
                case 3:
                    System.out.println("\033\143" + "\nView Product list");
                    for (Toys line : shop.getProductList()) {
                        System.out.println(line);
                    }
                    break;
                case 4:
                    System.out.println("\033\143" + "\nView Lottery list");
                    if (shop.getLotteryList().size() == 0) {
                        System.out.println("Is Empty. Add product!");
                    } else {
                        for (Toys line : shop.getLotteryList()) {
                            System.out.println(line);
                        }
                    }
                    break;
                case 5:
                    System.out.println("\033\143" + "\nTime To LoTTeRy =)");
                    if (shop.getLotteryList().size() == 0) {
                        System.out.println("Lottery list is empty, add product in lottery list for continue!");
                    } else {
                        Controller controller = new Controller(shop.getLotteryList());
                        String text = "WINNER: " + controller.LotteryTime();
                        System.out.println(text);
                        try (FileWriter writer = new FileWriter("Java/WinnerList.txt", true)) {
                            writer.write(text + "\n");
                        } catch (Exception ex) {
                            System.out.println("Exception" + ex);
                        }
                    }
                    break;
                case 6:
                    flag = false;
            }

        }
    }

}
