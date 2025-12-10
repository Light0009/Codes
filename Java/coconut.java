import java.util.*;
import java.io.*;

public class coconut{
	public static void main(String[] args){
		Kattio io=new Kattio(System.in);
		int s=io.getInt();
		int n=io.getInt();
		TailedLinkedList list = new TailedLinkedList();
		for (int i=1;i<=n;i++){
			list.addBack(new Player(i,"fold"));
		}
		while (list.size()>1){
			int count=s;
			for(int i=1;i<s;i++){
				list.addBack(list.removeFront());
			}
			Player cur=list.removeFront();
			if (cur.gethand()=="fold"){
				int num=cur.getnum();
				list.addFront(new Player(num,"fist"));
				list.addFront(new Player(num,"fist"));
			}
			if (cur.gethand()=="fist"){
				int num=cur.getnum();
				list.addBack(new Player(num,"down"));
			}
		}
		System.out.println(list.getFirst().getnum());
		io.close();
	}
}

class Player {
	public int number;
	public String hand;

	public Player(int number, String hand) {
		this.number = number;
		this.hand = hand;
	}

	public int getnum() {
		return number;
	}

	public String gethand() {
		return hand;
	}
}


class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}

interface ListInterface {
	public boolean isEmpty(); // Return true if list is empty; otherwise return false.
	public int     size(); // Return number of items in the list

  //  access items in the list
  public int     indexOf(Player item); // return index of item if item is found in the list, otherwise return -1
	public boolean contains(Player item); // return true if item is in the list false otherwise
	public Player    getItemAtIndex(int index); // get item at index
	public Player     getFirst(); // get first item
	public Player     getLast(); //get last item
    
  // add items to the list
  public void    addAtIndex(int index, Player item); // add item at position index, 
                                                  // shifting all current items from index onwards to the right by 1 
                                                  // add item to the back if index == size() 
	public void    addFront(Player item); // add item to front of list
	public void    addBack(Player item); // add item to back of list

	// remove items from the list
	public Player    removeAtIndex(int index); // remove item at index and return it                              
	public Player     removeFront(); // remove 1st item and return it
	public Player     removeBack(); // remove last item and return it

}

class TailedLinkedList implements ListInterface {
  // attributes
  public ListNode head;
  public ListNode tail;
  public int num_nodes;

  // interface methods

  // Return true if list is empty; otherwise return false.
  public boolean isEmpty() { return (num_nodes == 0); }

  // Return number of items in list
  public int size() { return num_nodes; }

  // return index of item if item is found in the list, otherwise return -1
  public int indexOf(Player item) {
    int index = 1;

    for (ListNode cur = head; cur != null; cur = cur.getNext()) {
      if (cur.getItem() == item) 
        return index;
      else 
        index++;
    }
    return -1;
  }

  // return true if item is in the list false otherwise
  public boolean contains(Player item) {
    if (indexOf(item) != -1)
      return true;
    return false;
  }

  // get item at index
  public Player getItemAtIndex(int index) {
    int counter = 1;
    Player item=new Player(1,"");
    if (index == size()){
      item = tail.getItem();
    }
    else {
      for (ListNode cur = head; cur != null; cur = cur.getNext(), counter++) {
        if (counter == index) {
          item = cur.getItem();
          break;
        }
      }
    }
    return item;
  }

  // Return first item
  public Player getFirst() { return getItemAtIndex(1); }

  // Return last item
  public Player getLast() { return getItemAtIndex(size()); }

  // add item at position index, shifting all current items from index onwards to the right by 1 
  // pre: 0 <= index <= size()
  public void  addAtIndex(int index, Player item) {
    ListNode cur;
    ListNode newNode = new ListNode(item);

    if (index >= 1 && index <=size()+1) {
      if (index == 1) // insert in front
        insert(null,newNode);
      else if (index == size()+1) // insert at the back, don't have to move all the way to the back
        insert(tail,newNode);
      else {
        cur = getNodeAtIndex(index-1); // access node at index-1
        insert(cur,newNode);
      }
    }
  } 

  // Add item to front of list
  public void addFront(Player item) { addAtIndex(1,item); }

  // Add item to back of list
  public void addBack(Player item) { addAtIndex(size()+1,item); }

  // remove item at index and return it
  // pre: 0 <= index < size()
  public Player removeAtIndex(int index) {
    ListNode cur;
    Player item=new Player(1,"");

    // index within bounds and list is not empty
    if (index >= 1 && index < size()+1) {
      if (index == 1) // remove 1st item
        item = remove(null);
      else {
        cur = getNodeAtIndex(index-1); // access node at index-1
        item = remove(cur);
      }
    }
    return item;
  }

  // Remove first node of list
  public Player removeFront() { return removeAtIndex(1); }

  // Remove last node of list
  public Player removeBack() { return removeAtIndex(size()); }

  // non-interface helper methods
  public ListNode getHead() { return head; }
  public ListNode getTail() { return tail; }

  /* return the ListNode at index */
  public ListNode getNodeAtIndex(int index) {
    int counter = 1;
    ListNode node = null;
    if (index == size()) // return tail if index == size()-1
      return tail;
    for (ListNode cur = head; cur != null; cur = cur.getNext()) {
      if (counter == index) {
        node = cur;
        break;
      }
      counter++;
    }
    return node;
  }

  // insert newNode after the node referenced by cur 
  public void insert(ListNode cur, ListNode n) {
    // insert in front
    if (cur == null) {
      n.setNext(head);
      head = n; // update head
      if (tail == null) // update tail if list originally empty
        tail = head;
    }
    else { // insert anywhere else
      n.setNext(cur.getNext());
      cur.setNext(n);
      if (cur == tail) // update tail if inserted new last item
        tail = tail.getNext();
    }
    num_nodes++;
  }

  // remove the node referenced by cur.next, and return the item in the node 
  // if cur == null, remove the first node 
  public Player remove(ListNode cur) {
    Player value=new Player(1,"");

    if (cur == null) { // remove 1st node
      value = head.getItem();
      head = head.getNext(); // update head
      if (num_nodes == 1) // update tail to null if only item in list is removed
        tail = null;
    }
    else { // remove any other node
      value = cur.getNext().getItem();
      cur.setNext(cur.getNext().getNext());
      if (cur.getNext() == null) // update tail if last item is removed
        tail = cur;
    }
    num_nodes--;

    return value;
  }
}

class ListNode {
	/* attributes */
	public Player item;
	public ListNode next;

	/* constructors */
	public ListNode(Player val) { this(val, null); }

	public ListNode(Player val, ListNode n) { 
		item = val; 
		next = n; 
	}

	/* get the next ListNode */
	public ListNode getNext() { return next; }

	/* get the item of the ListNode */
	public Player getItem() { return item; }

  /* set the item of the ListNode */
  public void setItem(Player val) { item = val; }

	/* set the next reference */
	public void setNext(ListNode n) { next = n; }
}

