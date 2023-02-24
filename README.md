# Overview

The purpose of this project was to use the [h2o.ai](https://docs.h2o.ai/h2o/latest-stable/h2o-docs/welcome.html#) Web UI to create a model, then productionize that model by exporting it as a set of java files that could be run in another environment (e.g. a web server) to generate predictions from data.

The workflow for this project was:
1. Create a model to predict [insurance costs](https://github.com/stedy/Machine-Learning-with-R-datasets/blob/master/insurance.csv) using the h2o.ai web UI running on my local machine.
2. Export the chosen model as a MOJO file with associated .jar file.
3. Create a main.java file that reads one line of data from a .csv file and then predicts insurance costs based on that data.

This project was run on an HP Dev One linux based laptop.  The following Java resources were used locally:

- openjdk 11.0.17 2022-10-18
- OpenJDK Runtime Environment (build 11.0.17+8-post-Ubuntu-1ubuntu222.04)
- OpenJDK 64-Bit Server VM (build 11.0.17+8-post-Ubuntu-1ubuntu222.04, mixed mode, sharing)

The commands to compile and run the code were:

- javac -cp h2o-genmodel.jar -J-Xms2g main.java
- java -cp .:h2o-genmodel.jar main


