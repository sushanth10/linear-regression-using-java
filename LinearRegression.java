import java.util.*;
import java.math.*;

public class LinearRegression{
    ArrayList<Long[]> al = new ArrayList<Long[]>();
    static long avg_x=0, avg_y=0;
    double sd_x=0, sd_y=0;
    static long n = 0;
    static long sum_x=0, sum_y=0, sum_XY=0;
    double coef_ = 0, slope_ =0;
    double intercept_ = 0;

    void addCoordinate(Long[] coord){
        this.al.add(coord);
        n++;
        sum_x+= coord[0];
        sum_y+=coord[1];
        avg_x = avg_x + (coord[0]-avg_x)/n;
        avg_y = avg_y + (coord[1]-avg_y)/n;
    }

    void calcStandardDeviation(){
        long sum_X_sq=0, sum_Y_sq=0;
        for(Long[] coord : al){
            sum_X_sq += (coord[0]-avg_x)*(coord[0]-avg_x);
            sum_Y_sq += (coord[1]-avg_y)*(coord[1]-avg_y);
            sum_XY += (coord[0]-avg_x)*(coord[1]-avg_y);
        }
        this.sd_x = Math.pow(sum_X_sq, 0.5);
        this.sd_y = Math.pow(sum_Y_sq, 0.5);

    }

    void calcCorrelation(){
        this.coef_ = sum_XY/(n*sd_x*sd_y);
        this.slope_ = coef_*sd_y/sd_x;
        this.intercept_ = avg_y - (this.slope_*avg_x);
    }

    void displayResults(){
        
    }

    public static void main(String args[]){
        System.out.println("-----PROGRAM STARTED------\n");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of coordinates : ");
        long n = sc.nextLong();
        LinearRegression lm = new LinearRegression();

        for(long i=0; i<n; i++){
            System.out.println("Enter coordinate : ");
            Long a = sc.nextLong();
            Long b = sc.nextLong();
            Long[] coord = {a,b};
            lm.addCoordinate(coord);
        }

        
        sc.close();
    }
}