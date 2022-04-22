# Linear Regression Using Java

## Thoughts

1995 : Writes 10 lines of code for the computer to display "Hello world" on screen.

2022 : Writes 10 lines of code and the computer pretty much accurately predicts prices of stocks for the coming few days. *T&C applied xD

Python is a beautiful language. Developers have made complex things look so easy using python that programs look a lot more simpler than they actually are behind the scenes. To appreciate this fact, I planned to implement the linear regression model in java.

## The Program

This program replicates the functions of python sklearn's Linear Regression model and also provides functions to check the statistical metrics (which is provided in the scipy stats library in python) like mean-square error, standard deviation, correlation, and R-square score.

The file datapoints.csv contains the points from a dataset that I found on the internet. The program assumes that the first column belongs to the independent variable (generally denoted by X) and the second column being the values of dependent variable (denoted by Y). 

The program reads the datapoints from the csv file, splits the dataset into training and testing data using the random function (70% for training and 30% for testing), calculates the correlation coefficient between X and Y and devises a simple single linear equation for the given data. 

This equation is then used to predict values for the testing dataset on which the errors and R-square score is calculated to measure the accuracy of the equation. 

Just to replicate the predict function of python's Linear Model, the program also provides the predict function to predict the value for one datapoint which can be input by the user.

NOTE : THIS PROGRAM WORKS ONLY FOR UNIVARIATE DATASET THAT IS WHEN THE INDEPENDENT VARIABLE, Y, IS DEPENDENT ON ONLY ONE VARIABLE, X.

To execute the program:
1. clone the repository and open the directory
2. Run the commands

        javac LinearRegression.java
        java LinearRegression
        
You can use this on your own dataset for any two columns of your choice by putting the values in the respective columns. More the data, better will be the accuracy of the program.

HAPPY CODING!!
