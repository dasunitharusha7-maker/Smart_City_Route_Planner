package com.group17.smartcity;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static String inputNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Empty input is not allowed. Try again.");
        }
    }

    public static void main(String[] args) {
        LocationManager manager = new LocationManager();
        LocationList locList = new LocationList();

        while (true) {
            System.out.println("\n--- Smart City Route Planner ---");
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections");
            System.out.println("6. Display all locations (linked list)");
            System.out.println("7. BFS traversal from a location (queue)");
            System.out.println("8. DFS traversal from a location (stack)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": {
                    String name = inputNonEmpty("Enter location name: ");
                    if (manager.addLocation(name)) {
                        locList.insert(name);
                        System.out.println("Location '" + name + "' added.");
                    } else {
                        System.out.println("Location '" + name + "' already exists.");
                    }
                    break;
                }
                case "2": {
                    String name = inputNonEmpty("Enter location to remove: ");
                    if (manager.removeLocation(name)) {
                        locList.remove(name);
                        System.out.println("Location '" + name + "' removed.");
                    } else {
                        System.out.println("Location '" + name + "' not found.");
                    }
                    break;
                }
                case "3": {
                    String a = inputNonEmpty("Enter first location: ");
                    String b = inputNonEmpty("Enter second location: ");
                    System.out.println(manager.addRoad(a, b));
                    break;
                }
                case "4": {
                    String a = inputNonEmpty("Enter first location: ");
                    String b = inputNonEmpty("Enter second location: ");
                    System.out.println(manager.removeRoad(a, b));
                    break;
                }
                case "5":
                    System.out.println("\nConnections:");
                    manager.displayConnections();
                    break;
                case "6":
                    System.out.println("\nAll locations (linked list):");
                    locList.display();
                    break;
                case "7": {
                    String start = inputNonEmpty("Enter start location for BFS: ");
                    List<String> order = manager.bfs(start);
                    if (order.isEmpty()) System.out.println("Start location not found or no reachable locations.");
                    else System.out.println("BFS order: " + String.join(" -> ", order));
                    break;
                }
                case "8": {
                    String start = inputNonEmpty("Enter start location for DFS: ");
                    List<String> order = manager.dfs(start);
                    if (order.isEmpty()) System.out.println("Start location not found or no reachable locations.");
                    else System.out.println("DFS order: " + String.join(" -> ", order));
                    break;
                }
                case "9":
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
        }
    }
}
