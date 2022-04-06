import java.util.*;

public class LinearRegression{

    // arraylist to store the coordinates 
    ArrayList<double[]> al = new ArrayList<double[]>();
    static double avg_x=0, avg_y=0; // variables to store the means 
    double sd_x=0, sd_y=0; // variables to store the standard deviations 
    static long n = 0; // variable to store the number of coordinates
    static double sum_x=0, sum_y=0, sum_XY=0; // store the sums of numbers
    double coef_ = 0, slope_ =0; // store the calculated slope and coefficient of correlation
    double intercept_ = 0; // stores the intercept value for the equation
    double mean_squared_error_ = 0; // stores the mean squared error value
    double r_square_score = 0; //stores the r-square score
    double sum_squared_resid = 0;


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

        // System.out.println("Sum squares : "+sum_X_sq+" "+sum_Y_sq);

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
        System.out.println("MSE score for the model : "+this.mean_squared_error_);
    }

    void fit(){
        this.calcStandardDeviation();
        this.calcCorrelation();
        this.calcMeanSquaredError();
        this.displayResults();
    }

    double predict(double X){
        double Y = this.slope_*X+this.intercept_;
        return Y;
    }

    void calcMeanSquaredError(){
        this.sum_squared_resid = 0;
        for(double[] coord : al){
            this.sum_squared_resid += Math.pow(coord[1]-(this.slope_*coord[0]+this.intercept_),2);
        }
        this.mean_squared_error_ = sum_squared_resid/n;
    }

    void calcRSquareScore(){

    }

    public static void main(String args[]){
        System.out.println("-----PROGRAM STARTED------\n");
        Scanner sc = new Scanner(System.in);

        // USE THE FOLLOWING LINES IF THERE ARE FIXED NUMBER OF POINTS
        // System.out.print("Enter number of coordinates : ");
        // long N = sc.nextLong();

        LinearRegression lm = new LinearRegression();
        try{
            while(true){
                System.out.print("Would you like to put one more data point? y/n : ");
                char ch = sc.next().charAt(0);
                if(ch=='y'){
                    System.out.print("Enter coordinate : ");
                    double a = sc.nextDouble();
                    double b = sc.nextDouble();
                    double[] coord = {a,b};
                    lm.addCoordinate(coord);
                }else{
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("Invalid input.");
        }


        lm.fit();

        System.out.print("\nEnter value of X for prediction : ");
        double X = sc.nextDouble();
        double Y = lm.predict(X);
        System.out.println("Predicted Y value : "+Y);
        
        sc.close();
    }
}