
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private int N;
    private int Vtop;
    private int Vbottom;
    private int openCount;
    private int [][] Grid;
    private boolean percolation;
    private WeightedQuickUnionUF findobj;
    private boolean implicitCall = false;
    
    public Percolation (int N) {
         if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Illegal arguments");
        }
        Grid = new int[N][N];
        percolation = false;
        findobj = new WeightedQuickUnionUF(N*N + 2);
        Vtop = N*N;
        this.N = N;
        Vbottom = N*N + 1;
        openCount = 0;

        int rand, i, j;
        
        while(!percolation) {    
        if (N == 1) {
            percolation = true;
            openCount = 1;
            continue;
        }
            
        rand = (StdRandom.uniform(1, N*N)) ;
        i = rand / N;
        j = rand % N;
        
        if ( j == 0 ) {
           j = j + N;
        }
        else {
            i = i+1;
        }
            open(i,j);
        }
            
    }
    
    public void open (int i, int j) {
        int current = 0, adj = 0;
     //   System.out.println("i = "+i+" j = "+j+" N = "+N);
        if (( i < 1) || (i > N) || (j < 1) || (j >N)) {
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        }
        
       
        implicitCall = true;
        current = convertToId(i,j);
        //System.out.println("isOpen(" + i+ "," + j + ") =" + isOpen(i,j));
        if (!isOpen(i,j)) {
             Grid[i-1][j-1] = 1;
             openCount++;
             if (i == 1) {
                 findobj.union(current, Vtop);
             }
             if (i == N) {
                 findobj.union(current, Vbottom);
             }
            // Site is not already Open
            implicitCall = true;
            if (isOpen(i-1,j)) {
                // Top neighbhor
                   adj = convertToId(i-1,j);
                   findobj.union(current, adj);
            }
            implicitCall = true;
            if (isOpen(i,j+1)) {
                //Right neighbhor
                adj = convertToId(i,j+1);
                findobj.union(current, adj);
            }
            implicitCall = true;
            if (isOpen(i+1,j)) {
                // Bottom Neighbhor
                adj = convertToId(i+1,j);
                findobj.union(current, adj);
            }
            implicitCall = true;
            if (isOpen(i,j-1)) {
                // Left Neighbhor
                adj = convertToId(i,j-1);
                findobj.union(current, adj);
            }     
        }
        implicitCall = true;
        if (isFull(N,j)) {
            percolation = true;
        }       
    }
    
    public boolean isFull (int i, int j) {
       if (( i < 1) || (i > N) || (j < 1) || (j >N)) {
           if (implicitCall == true) {
               implicitCall = false;
               return false;
           }
           throw new java.lang.IndexOutOfBoundsException("index out of bounds");
     
       }
       
       implicitCall = false; 
       
       int id = convertToId (i,j);
       if (findobj.connected(id, Vtop)) {
       //    System.out.println("\n\narray = \n");
           for (int l1=0; l1<N; l1++) {
              for (int l2=0; l2< N;l2++) {
         //         System.out.print(Grid[l1][l2] +" ");
              }
           //   System.out.println();
          }
          return true; 
          
          
              
       }
       return false;          
    }
    
    public boolean percolates() {
        if (percolation) {
            return true;
        }
        return false;
    }
        

    public boolean isOpen(int i, int j) {
        
        if (( i < 1) || (i > N) || (j < 1) || (j >N)) {
            if (implicitCall == true) {
                implicitCall = false;
                return false;
            }
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        }
        
        if (Grid[i-1][j-1] == 0)
            return false;
        
       return true;      
    }
    
    private int convertToId(int i, int j) {
        return ( (((i-1) * N) + j) - 1);
    }
    
    public int Count() {
        return openCount;
    }

    
    /*public static void main(String[] args) { 
        /*Percolation perlobj = new Percolation(5);
        perlobj.N = 5;
        

        
       // System.out.println ("Fraction Open on Perlocation = %d\n", openCount/(perlobj.N * perlobj.N));
            
    }*/
}