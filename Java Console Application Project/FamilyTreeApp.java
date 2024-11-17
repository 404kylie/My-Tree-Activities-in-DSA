import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    String name;
    List<Person> children;

	// Constructor to initialize a Person with a name
    public Person(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
	// Method to add a child to this person
    public void addChild(Person child) {
        children.add(child);
    }
	// Method to get the list of children
    public List<Person> getChildren() {
        return children;
    }
	// Method to get the name of the person
    public String getName() {
        return name;
    }
}
	// Class to represent the family tree structure
class FamilyTree {
    private Person root; // Root person of the family tree

	// Constructor to initialize the family tree with a root person
    public FamilyTree(String rootName) {
        root = new Person(rootName); // Create the root person
    }
	// Method to get the root person
    public Person getRoot() {
        return root; // Return the root of the family tree
    }
	// Method to add a child to a specified parent
    public void addChild(String parentName, String childName) {
        Person parent = findPerson(root, parentName); // Find the parent by name
        if (parent != null) {
            parent.addChild(new Person(childName)); // Add the child if parent is found
        } else {
            System.out.println("Parent not found."); // Print error if parent is not found
        }
    }
	// Recursive method to find a person by name in the tree
    private Person findPerson(Person current, String name) {
        if (current.getName().equals(name)) {
            return current; // Return the current person if found
        }
        for (Person child : current.getChildren()) {
            Person found = findPerson(child, name); // Search in child
            if (found != null) {
                return found; // Return the found person
            }
        }
        return null; // Return null if person is not found
    }

	// Method to display the family tree starting from the root
    public void displayTree() {
        displayTree(root, 0); // Call the recursive display method
    }
	// Recursive method to display the family tree structure
    private void displayTree(Person current, int level) {
        if (current == null)
            return; // Print the current person's name with indentation based on level
        System.out.println("  ".repeat(level) + current.getName()); // Recursively display each child's tree
        for (Person child : current.getChildren()) {
            displayTree(child, level + 1); // Increase level for children
        }
    }
}

public class FamilyTreeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input
        System.out.print("Enter the name of the root person: "); // Prompt for root person's name
        String rootName = scanner.nextLine(); // Read the root person's name
        FamilyTree familyTree = new FamilyTree(rootName); // Create a new FamilyTree with the root

        while (true)// Display options to the user
        	{
            System.out.println("\nOptions:");
            System.out.println("1. Add Child");
            System.out.println("2. Display Family Tree");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1: // Case for adding a child
                    System.out.print("Enter parent name: "); // Prompt for parent name
                    String parentName = scanner.nextLine(); // Read the parent name
                    System.out.print("Enter child name: "); // Prompt for child name
                    String childName = scanner.nextLine(); // Read the child name
                    familyTree.addChild(parentName, childName); // Add the child to the family tree
                    break; // Exit the switch case
                case 2: // Case for displaying the family tree
                    System.out.println("\nFamily Tree:");
                    familyTree.displayTree();
                    break;// Exit the switch case
                case 3: // Case for exiting the application
                    System.out.println("Exiting. . .");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again."); // Inform user of invalid input
            }
        }
    }
}