import java.util.*;

public class LinearRegression{
    ArrayList<double[]> al = new ArrayList<double[]>();
    static double avg_x=0, avg_y=0;
    double sd_x=0, sd_y=0;
    static long n = 0;
    static double sum_x=0, sum_y=0, sum_XY=0;
    double coef_ = 0, slope_ =0;
    double intercept_ = 0;

    void addCoordinate(double[] coord){
        this.al.add(coord);
        n++;
        sum_x+= coord[0];
        sum_y+=coord[1];
        avg_x = avg_x + (coord[0]-avg_x)/n;
        avg_y = avg_y + (coord[1]-avg_y)/n;
    }

    void calcStandardDeviation(){
        double sum_X_sq=0, sum_Y_sq=0;
        for(double[] coord : al){
            sum_X_sq += (coord[0]-avg_x)*(coord[0]-avg_x);
            sum_Y_sq += (coord[1]-avg_y)*(coord[1]-avg_y);
            sum_XY += (coord[0]-avg_x)*(coord[1]-avg_y);
        }
        this.sd_x = Math.pow(sum_X_sq/n, 0.5);
        this.sd_y = Math.pow(sum_Y_sq/n, 0.5);

        System.out.println("Sum squares : "+sum_X_sq+" "+sum_Y_sq);

    }

    void calcCorrelation(){
        this.coef_ = sum_XY/(n*sd_x*sd_y);
        this.slope_ = coef_*sd_y/sd_x;
        this.intercept_ = avg_y - (this.slope_*avg_x);
    }

    void displayResults(){

        // use following lines for checking other values
        // System.out.println("Coordinates : "+al);
        // System.out.println("Means x and y : "+avg_x+" "+avg_y);
        // System.out.println("Std x and y : "+sd_x+" "+sd_y);
        // System.out.println("Sums : "+sum_XY+" "+sum_x+" "+sum_y);

        if(this.coef_>0.5 || this.coef_<-0.5){
            System.out.println("Well co-related data. Worth creating the linear model");
        }else{
            System.out.println("Not very co-related data.");
        }
        System.out.println("Coefficient of correlation : "+this.coef_);
        System.out.println("Linear Model created with equation : y="+this.slope_+"x+"+this.intercept_);
    }

    void fit(){
        this.calcStandardDeviation();
        this.calcCorrelation();
        this.displayResults();
    }

    double predict(double X){
        double Y = this.slope_*X+this.intercept_;
        return Y;
    }

    public static void main(String args[]){
        System.out.println("-----PROGRAM STARTED------\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of coordinates : ");
        long N = sc.nextLong();
        LinearRegression lm = new LinearRegression();

        for(long i=0; i<N; i++){
            System.out.print("Enter coordinate : ");
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double[] coord = {a,b};
            lm.addCoordinate(coord);
        }
        lm.fit();

        System.out.print("Enter value of X for prediction : ");
        double X = sc.nextDouble();
        double Y = lm.predict(X);
        System.out.println("Predicted Y value : "+Y);
        
        sc.close();
    }
}