package lab2;

public class Node {
	
	/*
	 * A node contains two parts, one part that holds the data (4 bytes in memory) and one part that holds the address of the 
	 * next node (also 4 bytes memory space, so a node takes a total of 8 bytes memory space). 
	 * Therefore I create the in "data", which holds the value of the node, and the node next, which holds the information
	 * about the next node. 
	 */
	
	//The data part of the node that will hold the values of the elements in the list. 
	public int data;
	
	//next is a pointer to the type Node
	public Node next;
	
	//Next is the second part of the node that contains the address of the next element in the list. 
	public Node (int data, Node next){
		this.data = data;
		this.next = next;
	}
	
	//As I am asking the String-method to return a int, I have to add "" so that a String is returned. 
	public String toString(){
		return data + "";
	}
	
	//Prints out the data part of the node, i.e. the nodes value. 
	public void displayNode (){
		System.out.print(data + " ");
	}

}
