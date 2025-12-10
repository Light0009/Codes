import java.util.*;
import java.io.*;

public class nicknames{
  public static void main(String[] args)throws IOException{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int numofname=Integer.parseInt(br.readLine());
    HashMap<Character,AVL> treestore=new HashMap<Character,AVL>();
    for (int i=0;i<numofname;i++){
      String name=br.readLine();
      char first=name.charAt(0);
      if (treestore.containsKey(first)){
        treestore.get(first).insert(name);
      }
      else{
        AVL chartree=new AVL();
        chartree.insert(name);
        treestore.put(first,chartree);
      }
    }
    int numofnick=Integer.parseInt(br.readLine());
    HashMap<String,Integer> resultstorage=new HashMap<String,Integer>();
    for (int j=0;j<numofnick;j++){
      String nickname=br.readLine();
      int numofmatches=0;
      if(treestore.containsKey(nickname.charAt(0)) && nickname.length()==1){
        numofmatches=treestore.get(nickname.charAt(0)).root.size;
        resultstorage.put(nickname,numofmatches);
      }
      else if (resultstorage.containsKey(nickname)){
        numofmatches=resultstorage.get(nickname);
      }
      else{
        if(treestore.containsKey(nickname.charAt(0))){
          numofmatches=treestore.get(nickname.charAt(0)).check(nickname);
        }
        resultstorage.put(nickname,numofmatches);
      }
      pw.println(numofmatches);
    }
    pw.close();
    br.close();
  }
}
// Every vertex in this BST is a Java Class
class AVLVertex {
  AVLVertex(String v) { key = v; parent = left = right = null; height = 0; }
  // all these attributes remain public to slightly simplify the code
  public AVLVertex parent, left, right;
  public String key;
  public int height=0; // will be used in lecture on AVL
  public int size=1; // will be used in lecture on AVL
}

// This is just a sample implementation
// There are other ways to implement BST concepts...
class AVL {
  public AVLVertex root;

  public int getheight(AVLVertex T){
    if(T==null) return -1;
    else {
      return T.height;
    }
  }

  public void updateheight(AVLVertex T){
    if(T!=null){
        T.height=Math.max(getheight(T.left),getheight(T.right))+1;
    }
  }

  public int getsize(AVLVertex T){
    if (T==null) return 0;
    else {
      return T.size;
    }
  }

  public void updatesize(AVLVertex T){
    if(T!=null) {
        T.size=getsize(T.left)+getsize(T.right)+1;
    }
  }

  public int Balancefactor(AVLVertex T){
    if (T==null)return 0;
    else return getheight(T.left)-getheight(T.right);
  }

  public int check(String n){
    if (searchVertex(root,n)==null)return 0;
    else{
      return 1+leftsearch(n,searchVertex(root,n).left)+rightsearch(n,searchVertex(root,n).right);
    }
  }

  public int leftsearch(String n,AVLVertex T){
    if (T==null)return 0;
    int length=n.length();
    if (T.key.indexOf(n) == 0){
      return 1+leftsearch(n,T.left)+getsize(T.right);
    }
    else{
      return leftsearch(n,T.right);
    }
  }

  public int rightsearch(String n,AVLVertex T){
    if (T==null)return 0;
    int length=n.length();
    if (T.key.indexOf(n) == 0){
      return 1+rightsearch(n,T.right)+getsize(T.left);
    }
    else{
      return rightsearch(n,T.left);
    }
  }

  public AVLVertex rebalance(AVLVertex T){
    if (Balancefactor(T)==2) {
        if (Balancefactor(T.left) ==-1) {
            T.left = rotateLeft(T.left);
        }
        T = rotateRight(T);
    }
    if (Balancefactor(T)==-2) {
        if (Balancefactor(T.right)==1) {
            T.right = rotateRight(T.right);
        }
        T = rotateLeft(T);
    }
    return T;
}

  public AVL() { root = null; }

  // helper method to perform search
  public AVLVertex searchVertex(AVLVertex T, String v) {
    int length=v.length();
    if (T == null)  return null;                     // not found
    else if (T.key.indexOf(v) == 0) return T;                        // found
    else if (v.compareTo(T.key)>0) return searchVertex(T.right, v);       // search to the right
    else return searchVertex(T.left, v);        // search to the left
  }
  
  // public method called to find Minimum key value in BST
  public String findMin() { return findMin(root); }

  // helper method to perform findMin
  // Question: What happens if BST is empty?
  public String findMin(AVLVertex T) {
    if (T.left == null) return T.key;                    // this is the min
    else                return findMin(T.left);           // go to the left
  }

  // public method called to find Maximum key value in BST
  public String findMax() { return findMax(root); }

  // helper method to perform findMax
  // Question: Again, what happens if BST is empty?
  public String findMax(AVLVertex T) {
    if (T.right == null) return T.key;                   // this is the max
    else                 return findMax(T.right);        // go to the right
  }


  // public method called to perform inorder traversal
  public void inorder() { 
    inorder(root);
    System.out.println();
  }

  // helper method to perform inorder traversal
  public void inorder(AVLVertex T) {
    if (T == null) return;
    inorder(T.left);                               // recursively go to the left
    System.out.printf(" %d", T.key);                      // visit this BST node
    inorder(T.right);                             // recursively go to the right
  }

  // public method called to insert a new key with value v into BST
  public void insert(String v) { root = insert(root, v); }

  // helper recursive method to perform insertion of new vertex into BST
  public AVLVertex insert(AVLVertex T, String v) {
    if (T == null) {
      updatesize(T);
      updateheight(T);
      return new AVLVertex(v);  
    }        // insertion point is found

    if (v.compareTo(T.key)>0) {                                      // search to the right
      T.right = insert(T.right, v);
      T.right.parent = T;
    }
    else {                                                 // search to the left
      T.left = insert(T.left, v);
      T.left.parent = T;
    }
    updatesize(T);
    updateheight(T);
    return rebalance(T);                                          // return the updated BST
  }  
  public AVLVertex rotateLeft(AVLVertex T){
    if (T.right!=null){
      AVLVertex w=T.right;
      w.parent=T.parent;
      T.parent=w;
      T.right=w.left;
      if (w.left!=null) w.left.parent=T;
      w.left=T;
      w.size=T.size;
      updatesize(T);
      updateheight(T);
      updateheight(w);
      return w;
    }
    return T;
  }

  public AVLVertex rotateRight(AVLVertex T){
    if (T.left!=null){
      AVLVertex w=T.left;
      w.parent=T.parent;
      T.parent=w;
      T.left=w.right;
      if (w.right!=null) w.right.parent=T;
      w.right=T;
      w.size=T.size;
      updatesize(T);
      updateheight(T);
      updateheight(w);
      return w;
    }
    return T;
  }
}
