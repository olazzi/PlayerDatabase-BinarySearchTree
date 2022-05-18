
//-----------------------------------------------------
// Title: PlayerDatabase tester class
// Author: Enes YARDIM
// ID: 13213245090
// Section: 2
// Assignment: 3
// Description: This class provide implementing a football database system with binary search tree and test it
//-----------------------------------------------------
import java.util.Arrays;
import java.util.Scanner;

//Java program to demonstrate
//insert operation in binary
//search tree
class PlayerDatabase {

	/*
	 * Class containing left and right child of current node and key value
	 */
	class Node {
		int key;
		String name;
		String surname;
		Node left, right;

		public Node(String name1, String surname1, int item) {
			key = item;
			left = right = null;
			name = name1;
			surname = surname1;

		}
	}

	// Root of BST
	Node root;
	int rootkey = 0;
	String separator = "";
	String separator2 = "";
	static int smallest;

	// Constructor
	PlayerDatabase() {
		root = null;
	}

	PlayerDatabase(String name, String surname, int transferFee) {
		root = new Node(name, surname, transferFee);

	}

	// This method mainly calls insertRec()
	void insert1(String name, String surname, int transferFee) {
		root = insertRec(root, name, surname, transferFee);
	}

	/*
	 * A recursive function to insert a new key in BST
	 */
	Node insertRec(Node root, String name, String surname, int transferFee) {
		// --------------------------------------------------------
		// Summary: This method provide inserting football player to the tree
		// Precondition: Node root, String name, String surname, int transferFee
		// Postcondition: Given informations inserted to tree
		// --------------------------------------------------------

		/*
		 * If the tree is empty, return a new node
		 */
		if (root == null) {
			root = new Node(name, surname, transferFee);
			root.name = name;
			root.surname = surname;
			return root;
		}

		/* Otherwise, recur down the tree */
		if (transferFee < root.key) {
			root.left = insertRec(root.left, name, surname, transferFee);
		} else if (transferFee > root.key) {
			root.right = insertRec(root.right, name, surname, transferFee);
		} else {
			System.out.println("------------------------");
		}

		/* return the (unchanged) node pointer */
		return root;
	}

	void searchByRange(int minFee, int maxFee) {
		// --------------------------------------------------------
		// Summary: The functions prints all the keys which in the given range [k1..k2].
		// Precondition: node and ranges k1 k2
		// Postcondition: given range printed
		// --------------------------------------------------------
		Print(root, minFee, maxFee);
	}

	void Print(Node node, int k1, int k2) {
		// --------------------------------------------------------
		// Summary: The functions prints all the keys which in the given range [k1..k2].
		// Precondition: node and ranges k1 k2
		// Postcondition: given range printed
		// --------------------------------------------------------

		/* base case */
		if (node == null) {
			return;
		}

		/*
		 * Since the desired o/p is sorted, recurse for left subtree first If root->data
		 * is greater than k1, then only we can get o/p keys in left subtree
		 */
		if (k1 < node.key) {
			Print(node.left, k1, k2);
		}

		/* if root's data lies in range, then prints root's data */
		if (k1 <= node.key && k2 >= node.key) {

			System.out.print(separator + node.name + " " + node.surname);
			separator = ", ";
		}

		/* recursively call the right subtree */
		Print(node.right, k1, k2);
	}

	// This method mainly calls InorderRec()
	void inorder() {
		inorderRec(root);
	}

	void inorderRec(Node root) {
		// --------------------------------------------------------
		// Summary: The functions prints all the keys inorder.
		// Precondition: node root
		// Postcondition: bst printed in order
		// --------------------------------------------------------
		if (root != null) {
			inorderRec(root.left);
			System.out.print(separator2 + root.name + " " + root.surname + " " + root.key);
			separator2 = ", ";
			inorderRec(root.right);
		}
	}

	Node search(Node root, int key) {
		// --------------------------------------------------------
		// Summary: A utility function to search a given key in BST
		// Precondition: node root and key to be searched
		// Postcondition: searched if exist
		// --------------------------------------------------------
		// Base Cases: root is null or key is present at root
		if (root == null || root.key == key)
			return root;

		// Key is greater than root's key
		if (root.key < key)
			return search(root.right, key);

		// Key is smaller than root's key
		return search(root.left, key);
	}

	void removePlayer(String name, String surname) {

		root = deleteHelper(root, name, surname);

	}

	Node deleteHelper(Node root1, String name, String surname) {
		// --------------------------------------------------------
		// Summary: A utility function to delete a given key in BST
		// Precondition: node root and key name and surname to be searched
		// Postcondition: removed if exist
		// --------------------------------------------------------

		Node root2 = searchHelper(root1, name, surname);
		/* Base Case: If the tree is empty */
		if (root2 == null) {
			System.out.println("not exist");
		}
		int key = rootkey;

		if (root1 == null) {
			return root1;
		}

		/* Otherwise, recur down the tree */
		if (key < root1.key)
			root1.left = deleteHelper(root1.left, name, surname);
		else if (key > root1.key)
			root1.right = deleteHelper(root1.right, name, surname);

		// if key is same as root's
		// key, then This is the
		// node to be deleted
		else {
			// node with only one child or no child
			if (root1.left == null) {
				return root1.right;
			} else if (root1.right == null) {
				return root1.left;
			}

			// node with two children: Get the inorder
			// successor (smallest in the right subtree)
			root1.key = minValue(root1.right);
			root1.name = root1.right.name;
			root1.surname = root1.right.surname;

			// Delete the inorder successor

			root1.right = null;

		}

		return root1;

	}

	int minValue(Node root) {
		// --------------------------------------------------------
		// Summary: A utility function to find minimum value a given key in BST
		// Precondition: node root
		// Postcondition: minimum value founded
		// --------------------------------------------------------

		int minv = root.key;
		while (root.left != null) {
			minv = root.left.key;
			root = root.left;

		}
		return minv;
	}

	Node searchHelper(Node root, String name, String surname) {
		// --------------------------------------------------------
		// Summary: A utility function to search a given key in BST
		// Precondition: node root and key to be searched
		// Postcondition: searched if exist
		// --------------------------------------------------------

		if (root == null) {
			return null;
		} else {
			if (name.equals(root.name) && surname.equals(root.surname)) {

				rootkey = root.key;
				return root;
			}
			if (root.left != null) {
				return searchHelper(root.left, name, surname);
			} else {

				return searchHelper(root.right, name, surname);
			}
		}
	}

	void searchByName(String name, String surname) {
		// --------------------------------------------------------
		// Summary: A utility function to search a given key in BST
		// Precondition: node root and key to be searched
		// Postcondition: searched if exist
		// --------------------------------------------------------

		Node root1 = searchHelper(root, name, surname);
		if (root1 == null) {
			System.out.println("False");
		} else {
			System.out.println("True");
		}

	}

	static int count = 0;

	public void findKSmallest(int k) {
		Node nd = kthSmallest(root, k);
		System.out.println(nd.name + " " + nd.surname);
		count = 0;
	}

	public static Node kthSmallest(Node root, int k) {
		// --------------------------------------------------------
		// Summary: A utility function to search kth smallest value in BST
		// Precondition: node root and key to be searched
		// Postcondition: kth smalles value in BST
		// --------------------------------------------------------

		if (root == null)
			return null;

		// search in left subtree
		Node left = kthSmallest(root.left, k);

		// if k'th smallest is found in left subtree, return it
		if (left != null)
			return left;

		// if current element is k'th smallest, return it
		count++;
		if (count == k)
			return root;

		// else search in right subtree
		return kthSmallest(root.right, k);
	}

	// Driver Code
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PlayerDatabase tree = new PlayerDatabase();
		int control = 1;

		String[] ply1 = new String[500];
		int tfee = 0;
		int sel;
		int a = 0;
		sel = scan.nextInt();
		scan.nextLine();
		while (control != 0) {

			a++;
			switch (sel) {
			case 1:

				String players = scan.nextLine();

				String[] namesList = players.split(",");
				// System.out.println(Arrays.toString(namesList));
				for (int i = 0; i < namesList.length; i++) {
					namesList[i] = namesList[i].trim();

				}
				for (int i = 0; i < namesList.length; i++) {
					ply1 = namesList[i].split(" ");
					tfee = Integer.parseInt(ply1[2]);
					tree.insert1(ply1[0], ply1[1], tfee);
					ply1 = null;
				}
				// tree.inorder();
				sel = scan.nextInt();
				scan.nextLine();
				break;

			case 3:
				String srcPlayer = scan.nextLine();
				String[] srcList = srcPlayer.split(" ");

				tree.searchByName(srcList[0], srcList[1]);
				sel = scan.nextInt();
				scan.nextLine();
				break;
			case 2:
				String rmvPlayer = scan.nextLine();
				String[] rmvList = rmvPlayer.split(" ");

				tree.removePlayer(rmvList[0], rmvList[1]);

				sel = scan.nextInt();
				scan.nextLine();
				break;
			case 5:
				tree.inorder();
				System.out.print("\n");

				sel = scan.nextInt();
				scan.nextLine();
				break;
			case 4:
				String srcRange = scan.nextLine();
				String[] rngList = srcRange.split(" ");
				int nmMin = Integer.parseInt(rngList[0]);
				int nmMax = Integer.parseInt(rngList[1]);
				tree.searchByRange(nmMin, nmMax);
				System.out.print("\n");

				sel = scan.nextInt();
				scan.nextLine();
				break;
			case 6:

				smallest = scan.nextInt();
				tree.findKSmallest(smallest);

				sel = scan.nextInt();
				scan.nextLine();
				break;
			case 7:
				control = 0;
				break;
			}

		}
	}
}
