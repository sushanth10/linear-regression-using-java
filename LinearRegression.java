import java.util.*;

public class LinearRegression{

    // arraylist to store the coordinates 
    ArrayList<double[]> al = new ArrayList<double[]>();
    ArrayList<double[]> train_points = new ArrayList<double[]>();
    ArrayList<double[]> test_points = new ArrayList<double[]>();
    static double avg_x=0, avg_y=0; // variables to store the means 
    double sd_x=0, sd_y=0; // variables to store the standard deviations
    static double sum_x=0, sum_y=0, sum_XY=0; // store the sums of numbers
    static double sum_X_sq=0, sum_Y_sq=0;
    double coef_ = 0, slope_ =0; // store the calculated slope and coefficient of correlation
    double intercept_ = 0; // stores the intercept value for the equation
    double mean_squared_error_ = 0; // stores the mean squared error value
    double r_square_score = 0; //stores the r-square score
    double sum_squared_resid = 0; // stores squared sum of residuals    


    void addCoordinate(double[] coord){
        // adds each coordinate to the arraylist as and when input received
        this.al.add(coord);
    }

    void calcMeans(){
        
        for(double[] coord: this.train_points){
            // calculates the sum value for the variables 
            sum_x+= coord[0];
            sum_y+=coord[1];
        }

        avg_x = sum_x/train_points.size();
        avg_y = sum_y/train_points.size();
    }

    void calcStandardDeviation(){
        // nothing to calculate if there are no datapoints and end the program
        if(al.size()==0){ 
            System.out.println("No datapoints to calculate regression");
            System.exit(0);
        }

        // iterate through the list of datapoints to calculate sum squares of
        for(double[] coord : this.train_points){
            sum_X_sq += (coord[0]-avg_x)*(coord[0]-avg_x);
            sum_Y_sq += (coord[1]-avg_y)*(coord[1]-avg_y);
            sum_XY += (coord[0]-avg_x)*(coord[1]-avg_y);
        }

        this.sd_x = Math.pow(sum_X_sq/train_points.size(), 0.5);
        this.sd_y = Math.pow(sum_Y_sq/train_points.size(), 0.5);

        // System.out.println("Sum squares : "+sum_X_sq+" "+sum_Y_sq);

    }

    void calcCorrelation(){
        // calculates coefficient of correlation 
        this.coef_ = sum_XY/(this.train_points.size()*sd_x*sd_y);

        // calculates m and c values for the equation y=mx+c    
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

        if(this.r_square_score>0.5){
            System.out.println("Very good R^2 score.");
        }else{
            System.out.println("R^2 score not very good for model creation");
        }
        System.out.println("R Square Score for the model : "+this.r_square_score);
    }

    void fit(){
        // calculates all the required values for the model
        this.train_test_split();
        this.calcMeans();
        this.calcStandardDeviation();
        this.calcCorrelation();
        this.calcMeanSquaredError();
        this.calcRSquareScore();
        this.displayResults();
    }

    void train_test_split(){
        int num_train_points = (int)Math.floor(0.7*al.size());
        this.test_points = new ArrayList<>(this.al);
        for(int i=0; i<num_train_points; i++){
            double[] pt = al.get((int)Math.floor(Math.random()*num_train_points));
            this.train_points.add(pt);
            this.test_points.remove(pt);
        }
    }

    double predict(double X){
        // predicts the value of dependent variable on the basis of the X value 
        double Y = this.slope_*X+this.intercept_;
        return Y;
    }

    void calcMeanSquaredError(){
        // finds sum of squares of errors
        this.sum_squared_resid = 0;
        for(double[] coord : this.test_points){
            this.sum_squared_resid += Math.pow(coord[1]-(this.slope_*coord[0]+this.intercept_),2);
        }
        // finds mean of squared errors 
        this.mean_squared_error_ = sum_squared_resid/this.test_points.size();
    }

    void calcRSquareScore(){
        // finds R^2 score 
        this.r_square_score = 1-(this.sum_squared_resid)/(sum_Y_sq);
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
                // takes all the datapoints as input one by one from the user 
                // throws exception when the required input is not received
                System.out.print("Would you like to put one more data point? y/n : ");
                char ch = sc.next().charAt(0);
                if(ch=='y'){
                    System.out.print("Enter coordinate : ");
                    double a = sc.nextDouble();
                    double b = sc.nextDouble();
                    double[] coord = {a,b};
                    lm.addCoordinate(coord);
                }else{
                    if(ch=='n'){
                        break;
                    }else{
                        throw new Exception("Not a valid input");
                    }
                }
            }

            if(lm.al.size()<4){
                throw new Exception("Not enough datapoints to split the dataset");
            }
            // finds all the required metrics 
            lm.fit();

            // PREDICTION PART
            System.out.print("\nEnter value of X for prediction : ");
            double X = sc.nextDouble();
            double Y = lm.predict(X);
            System.out.println("Predicted Y value : "+Y);

        }catch(Exception e){
            System.out.println("Invalid input.");
        }

        sc.close();

        System.out.println("-----END OF PROGRAM-----");
    }
}