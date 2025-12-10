import java.util.*;
import java.io.*;

public class teque{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n=Integer.parseInt(br.readLine());
		QueueArr left= new QueueArr();
		QueueArr right= new QueueArr();
		for (int i=0;i<n;i++){
			String[] commandline=br.readLine().split(" ");
			String command=commandline[0];
			int num=Integer.parseInt(commandline[1]);
			int size=left.getttsize()+right.getttsize();
			if (command.equals("push_back")){
				if (size%2==0){
					right.pushback(num);
					left.pushback(right.removefront());
				}
				else{
					right.pushback(num);
				}
			}
			else if(command.equals("push_front")){
				if(size%2==0){
					left.pushfront(num);
				}
				else{
					left.pushfront(num);
					right.pushfront(left.removeback());
				}
			}
			else if(command.equals("push_middle")){
				if (size%2==0){
					left.pushback(num);
				}
				else{
					right.pushfront(num);
				}
			}
			else if(command.equals("get")){
				if(num<left.getssize()){
					pw.println(left.getstack()[(left.getssize()-1)+left.getbottom()-num]);
				}
				else if(num<left.getttsize()){
					if ((num-(left.getssize()-1)+left.getfront()-1)>=left.getqcap()){
						pw.println(left.getqueue()[num-(left.getssize()-1)+left.getfront()-left.getqcap()-1]);
					}
					else{
						pw.println(left.getqueue()[num-(left.getssize()-1)+left.getfront()-1]);
					}
				}
				else if(num<(left.getttsize()+right.getssize())){
					pw.println(right.getstack()[(left.getttsize()+right.getssize()-1)+right.getbottom()-num]);
				}
				else{
				    if ((num-(left.getttsize()+right.getssize()-1)+right.getfront()-1)>=right.getqcap()){
				        pw.println(right.getqueue()[num-(left.getttsize()+right.getssize()-1)+right.getfront()-right.getqcap()-1]);
				    }
				    else{
					    pw.println(right.getqueue()[num-(left.getttsize()+right.getssize()-1)+right.getfront()-1]);
				    }
				}
			}
		}
		pw.close();
		br.close();
	}
}

// This implementation uses solution 2 to resolve full/empty state
class QueueArr implements QueueADT {
	public int[] queue;
	public int[] stack;
	public int top, bottom, front, back;
	public int qcapacity;
	public int scapacity;
	public int qsize;
	public int ssize;
	public final int sINITSIZE = 1;
	public final int qINITSIZE = 1;
	public int totalsize;

	public QueueArr() {
		queue = new int[qINITSIZE]; // create array of integers
		stack = new int[sINITSIZE]; // creating array of type E
		top = -1;  // empty stack - thus, top is not on an valid array element
		bottom=0;
		front = 0; // the queue is empty
		back = 0;
		qcapacity = qINITSIZE;
		scapacity=sINITSIZE;
	}

	public int getqsize(){
		return qsize;
	}
	public int getssize(){
		return ssize;
	}
	public int getttsize(){
		int totalsize=qsize+ssize;
		return totalsize;
	}
	public int getfront(){
		return front;
	}
	public int getback(){
		return back;
	}
	public int getbottom(){
		return bottom;
	}
	public int getqcap(){
		return qcapacity;
	}
	public int[] getstack(){
		return stack;
	}
	public int[] getqueue(){
		return queue;
	}

	public boolean sempty() { 
		return (top < 0); 
	}

	public boolean qempty() { 
		return (front == back); 
	}

	public Integer peek() {
		if (!sempty()) 
		  return stack[top];
		return null; // use null to represent empty stack
	}

	public Integer removefront() {
		Integer item = peek();
		if (item != null){
		 	top--;
		 	ssize--;
		}
		else{
			item = queue[front];
			front = (front + 1) % qcapacity;
			qsize--;
		}
		return item;
	}

	public Integer removeback(){
		int item;
		if(qempty()){
			item=stack[(top+1)-ssize];
			ssize--;
			bottom++;
		}
		else{
			item=queue[back-1];
			back=(back-1)%qcapacity;
			qsize--;
		}
		return item;
	}

	public void pushback(Integer item) {
		queue[back] = item;
		back = (back + 1) % qcapacity;
		qsize++;
		if (((back)%qcapacity) == front) // array is full
		  qenlargeArr();
	}

	public void pushfront(Integer item){
		if (top >= scapacity - 1) 
		  senlargeArr();
		top++;
		stack[top] = item;
		ssize++;
	}

	public boolean qenlargeArr() {
		int newSize = qcapacity * 2;
		int[] temp = new int[newSize];
		if (temp == null) { // i.e. no memory allocated to array of E objects
		  System.out.println("Not enough memory");
      System.exit(1);
    }
		for (int j=0; j < qcapacity; j++) {
			// copy the front (1st) element, 2nd element, ...,  in the 
			// original array to the 1st (index 0), 2nd (index 1), ...,
			// position in the enlarged array
			temp[j] = queue[(front+j) % qcapacity];
		}
		front = 0; 
		back = qcapacity;
		queue = temp;
		qcapacity = newSize;
		return true;
	}

	public void senlargeArr() {
	  int newSize = scapacity * 2; // double size
    int[] temp = new int[newSize];

    if (temp == null) { // i.e. not enough memory to create an array of newSize
      System.out.println("run out of memory!");
      System.exit(1);
    }
    // copy the original array to the new array
    for (int j=0; j <= top; j++)
      temp[j] = stack[j];
    stack = temp; // point arr to the new array
    scapacity = newSize;
	}
}

interface QueueADT {
	// return true if queue has no elements
	public boolean  sempty();
	public boolean  qempty();

	public int getqsize();
	public int getssize();
	public int getttsize();
	public int getfront();
	public int getback();
	public int getbottom();
	public int getqcap();

	public int[] getqueue();
	public int[] getstack();

	// return the front of the queue 
	public Integer  peek();

	// remove and return the front of the queue
	public Integer  removefront(); // also commonly known as dequeue
	public Integer  removeback();

	// add item to the back of the queue
	public void pushback(Integer item); // also commonly known as enqueue
	public void pushfront(Integer item);
}