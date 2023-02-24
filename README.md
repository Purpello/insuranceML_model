# Overview

The purpose of this project was to use the [h2o.ai](https://docs.h2o.ai/h2o/latest-stable/h2o-docs/welcome.html#) Web UI to create an ML model, then [productionize](https://docs.h2o.ai/h2o/latest-stable/h2o-docs/productionizing.html?highlight=productionize) that model by exporting it as a set of java files that could be run in another environment (e.g. a web server) to generate predictions from data.

# Approach

The workflow for this project was:
1. Create a model to predict [insurance costs](https://github.com/stedy/Machine-Learning-with-R-datasets/blob/master/insurance.csv) from customer characteristics using the h2o.ai web UI running on my local machine (HP Dev One).  This same approach can also be run in the cloud.
2. Export the chosen model as a MOJO file with associated .jar file.
3. Create and compile a main.java file that reads one line of customer data from a .csv file and then predicts insurance costs based on that data.

This project was run on an HP Dev One linux based laptop.  The following Java resources were used locally:

- openjdk 11.0.17 2022-10-18
- OpenJDK Runtime Environment (build 11.0.17+8-post-Ubuntu-1ubuntu222.04)
- OpenJDK 64-Bit Server VM (build 11.0.17+8-post-Ubuntu-1ubuntu222.04, mixed mode, sharing)

The commands to compile and run the code were:

- javac -cp h2o-genmodel.jar -J-Xms2g main.java
- java -cp .:h2o-genmodel.jar main

# Model Description

The winning model was a GBM (Gradient Boosting Machine) model.  Below is a summary of metrics from 5 cross-validation rounds, followed by variable importances.  The mean **mae** in the first table tells that on average, our predictions are within about $2500 of actual costs.  The second table tells us that the two most important variables for predicting insurance costs are the ones we have the most control over: being a smoker (yes/no) and our BMI, with smoking being by far the strongest predictor.

![image](https://user-images.githubusercontent.com/7217660/221303800-ace82286-4904-4774-b424-e9143ab621b4.png)

![image](https://user-images.githubusercontent.com/7217660/221304131-b8d4e5ae-47b3-4755-b002-bf7d8e3acefe.png)





