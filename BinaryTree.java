// Node class representing each node in the binary tree
class Node {
    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
        left = right = null;
    }
}

// BinaryTree class
class BinaryTree {
    Node root;

    // Method to insert a new value in the binary tree
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // Recursive method to insert a new value
    private Node insertRec(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    // Method for in-order traversal of the tree
    public void inOrderTraversal() {
        inOrderRec(root);
    }

    // Recursive method for in-order traversal
    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    // Main method to test the BinaryTree class
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Inserting values
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.print("In-order traversal of the binary tree:");
        tree.inOrderTraversal(); // Output: 20 30 40 50 60 70 80
        System.out.println(" ");
        System.out.println(" ");

    }
}