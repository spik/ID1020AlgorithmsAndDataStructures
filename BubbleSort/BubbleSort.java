package lab2;

import java.util.Scanner;

public class LinkedList {
	 
	public Node first;
	static int numberOfSwaps = 0;
	static int numberOfInversions = 0; 

	public static void main(String[] args) 
	{
		
		LinkedList List = new LinkedList();
		
		Scanner in = new Scanner (System.in);
		
		int data = 0;
		int numberOfItems = 0;
		
		System.out.println("Enter numbers for list. To exit input a negative number");
		
		while (data >= 0)
		{
			data = in.nextInt();
			
			if (data >= 0)
			{
				List.addToFront (data);
				numberOfItems++;
			}
		}
		List.inversionCount(List);
		System.out.println("\n");
		
		System.out.print("List before sort: ");
		List.display();
		System.out.println("");
		
		List.bubbleSort(List, numberOfItems);
		
		System.out.print("List after sort: ");
		List.display();
		
		System.out.println();
		System.out.println("Number of swaps: " + numberOfSwaps);
		System.out.println("Number of Inversions: " + numberOfInversions);
		
		in.close();
	}
	
	
	public void addToFront (int data)
	{
		
		//We call the constructor "Node" with data being the data part of the node, and next being the address part of the node, 
		//now containing null, i.e. this node is not pointing to another node yet. 
		Node newNode = new Node (data, null);
		
		//Get the "next" part of the node, i.e. the address part, to point to the first node in the list. 
		newNode.next = first;
		
		//Update first so that it points to the new node "newNode". Now the new node is the first node in the list.
		first = newNode;
		
	}
	
	public void display ()
	{
		//Node is now the first node
		Node node = first;
		
		//Exit loop if node is null. 
		while (node != null)
		{	
			//Call displayNode from class "Node" to print out a single node
			node.displayNode();
			
			//Now set node to be the next node in the list so that the value is updated for the next loop. 
			node = node.next;
		}
	}
	
	void inversionCount (LinkedList List)
	{
		
		Node n;
		Node n2;
		
		for (n = List.first; n != null; n = n.next)
		{	
			for (n2 = n.next; n2 != null; n2 = n2.next)
			{
				if ( n.data > n2.data)
				{
					numberOfInversions++;
					System.out.print("(" + n + "," + n2 + ")	");
				}
			}
		}
	}
	
	void bubbleSort (LinkedList List, int numberOfItems)
	{
		boolean isSwapped = true;
		
		while (numberOfItems >= 0 && isSwapped == true)
		{
			isSwapped = false;
			Node n = List.first;
			Node n2 = n.next;
			
			for (int i = 0; i < numberOfItems; i++)
			{				
				if(n.next == null)
					break;
				
				if ( n.data > n2.data)
				{
					swap(List, n, n2);
					numberOfSwaps++;
					isSwapped = true;
				}
				n = n.next;
				n2 = n.next;
			}
		}
	}
     
    static void swap(LinkedList List, Node n, Node n2)
    {
        int temp = n.data;
        n.data = n2.data;
        n2.data = temp;        
    }

}
