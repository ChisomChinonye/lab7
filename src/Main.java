import java.util.Scanner;

public class Main {

    // Insert smaller values to the left and larger values to the right.
    // Return the root of the subtree. Duplicate values are ignored.
    static Node insert(Node root, char value) {
        if (root == null) {
            return new Node(value);
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        }

        return root;
    }

    // Inorder: left, root, right.
    static void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Preorder: root, left, right.
    static void preorder(Node root) {
        if (root == null) return;

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Postorder: left, right, root.
    static void postorder(Node root) {
        if (root == null) return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of values: ");
        int size = -1;
        while (size < 0) {
            if (input.hasNextInt()) {
                size = input.nextInt();
            } else {
                input.next();
            }
            if (size < 0) {
                System.out.print("Enter a nonnegative integer: ");
            }
        }

        // Character allows an array entry to represent a missing value as null.
        Character[] data = new Character[size];
        for (int i = 0; i < data.length; i++) {
            System.out.print("Enter value " + (i + 1) + " (one character or null): ");
            String value = input.next();

            while (value.length() != 1 && !value.equalsIgnoreCase("null")) {
                System.out.print("Enter one character or null: ");
                value = input.next();
            }

            if (value.length() == 1) {
                data[i] = value.charAt(0);
            }
        }

        // Insert the non-null entries in the same order the user entered them.
        Node root = null;
        for (Character value : data) {
            if (value != null) {
                root = insert(root, value);
            }
        }

        // Repeat the traversal menu until the user selects Exit.
        int choice;
        System.out.println();
        do {
            System.out.println("1. Inorder traversal");
            System.out.println("2. Preorder traversal");
            System.out.println("3. Postorder traversal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Enter an integer from 1 to 4.");
            }

            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Inorder: ");
                    inorder(root);
                    break;
                case 2:
                    System.out.print("Preorder: ");
                    preorder(root);
                    break;
                case 3:
                    System.out.print("Postorder: ");
                    postorder(root);
                    break;
                case 4:
                    System.out.println("Exiting.");
                    break;
                default:
                    System.out.println("Enter an integer from 1 to 4.");
            }
            System.out.println();
        } while (choice != 4);

        input.close();
    }
}

// Each node stores a character and links to its two children.
class Node {
    char data;
    Node left, right;

    Node(char data) {
        this.data = data;
    }
}
