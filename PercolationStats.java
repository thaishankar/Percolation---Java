
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import static java.lang.Math.sqrt;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
   
    private Percolation percobj;
    private StdStats stats;
    private  double [] arr;
    private double mean;
    private double deviation;
    private int N;
    private int T;
    
    public PercolationStats(int N, int T) {
        if ((N <= 0) || (T <= 0)) {
            throw new java.lang.IllegalArgumentException("Illegal arguments");
        }
        this.N = N;
        this.T = T;
        arr = new double[T];
        for (int i=0; i<T; i++) {
            percobj = new Percolation(N);
            arr[i] = (double)percobj.Count() / (N*N) ;
        }
    }
     
    public double mean() {
        for (int i= 0; i<T; i++) {
            //System.out.println("arr["+i+"] ="+arr[i]);
        }
        return(mean = stats.mean(arr));
    }
       
    public double stddev() {
        return(deviation = stats.stddev(arr));
    }
        
    public double confidenceLo() {
       return(mean - (1.96 * deviation) /sqrt(T));
    }
           
    public double confidenceHi() {
       return(mean + (1.96 * deviation) /sqrt(T));
    }

     public static void main(String[] args)  {  
         
         int N =0 , T =0 ;
         
         if (args.length > 1) {
             N = Integer.parseInt(args[0].trim());
             T = Integer.parseInt(args[1].trim());
         }
       
        PercolationStats statsobj = new PercolationStats(N,T);

        StdOut.println("mean                    = "+ statsobj.mean());
        StdOut.println("stddev                  = "+ statsobj.stddev());
        StdOut.println("95% confidence interval = "+ statsobj.confidenceLo()+", "+statsobj.confidenceHi());                   
     }
   
}