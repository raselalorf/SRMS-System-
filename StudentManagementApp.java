import java.util.Scanner;

public class StudentManagementApp {

    public static void main(String[] args) {
        // 1. Initialize Core Components
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        
        System.out.println("===========================================");
        System.out.println("  Student Record Management System (SRMS)  ");
        System.out.println("===========================================");
        System.out.println("Loaded " + manager.countStudent() + " records from file.");

        // 2. Start the Auto-Save Thread
        AutoSaveThread autoSaver = new AutoSaveThread(manager);
        autoSaver.start();
        System.out.println("Auto-Save thread started (saves every minute).");
        
        // 3. Main Application Loop
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left over from nextInt()
                
                // Process the user's choice
                switch (choice) {
                    case 1:
                        // Add Student (We need to implement this method later)
                        System.out.println("Function: Add Student (Not yet fully implemented)");
                        break;
                    case 2:
                        // Remove Student
                        System.out.println("Function: Remove Student (Not yet fully implemented)");
                        break;
                    case 3:
                        // Update Student
                        System.out.println("Function: Update Student (Not yet fully implemented)");
                        break;
                    case 4:
                        // View All Students
                        System.out.println("\n--- All Students ---");
                        manager.printAllStudent();
                        break;
                    case 5:
                        // Search (by ID, Major, or Year)
                        System.out.println("Function: Search Students (Not yet fully implemented)");
                        break;
                    case 6:
                        // Exit
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        
        // 4. Clean up before exiting
        autoSaver.interrupt(); // Stops the auto-save thread
        System.out.println("\nSRMS shutting down. Finalizing save...");
        
        // Ensure one final save when the user quits
        try {
            StudentFileHandler.saveRecords(manager.getStudents());
        } catch (Exception e) {
            System.err.println("CRITICAL ERROR: Failed final save on exit.");
        }
        
        scanner.close();
        System.out.println("Goodbye!");
    }

    // Helper method to display the menu
    private static void displayMenu() {
        System.out.println("\n---------------- MENU ----------------");
        System.out.println("1. Add New Student");
        System.out.println("2. Remove Student by ID");
        System.out.println("3. Update Student Information");
        System.out.println("4. View All Students");
        System.out.println("5. Search Students (by ID/Major/Year)");
        System.out.println("6. Exit Application");
        System.out.println("--------------------------------------");
    }
}