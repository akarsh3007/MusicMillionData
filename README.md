# MusicMillionData

# Spark version 2.1.0, scala 2.11.8

# Description
This project provides an example of loading a dataset from HDF5 and converting to a case class and then to a Spark DataFrame for use with Spark SQL and Spark mlib. Use of Spark SQL to Perform Analysis. Use of Spark Mlib for Linear Regression on Predicting the Popularaity of Songs based on song features.
# HDF5 Libraries
1.Download libraries for HDF5 from https://wiki-bsse.ethz.ch/download/attachments/26609237/sis-jhdf5-14.12.6-r36356.zip?version=1&modificationDate=1462044819824&api=v2

# Engineering
1. Reading HDF5 file format and convert them to scala objects and then to SparkRDD and Spark DataFrame
2. Processing Data removing and Replacing NAN values.
3. SparkSQL query to find top 10 songs for the year provided
4. SparkSQL query to find top 10 artist of all time
5. A Linear Regression Using SparkMLib and DataFrame to predict the chances of a song gaining popularity.
6. Saving all ouptput files as paraquet file.

# Conifg
Config Contains an application.conf file where you can provide input directory, master, appname, yearoftrackforwhich, you want the Top Songs. maxIter,regparam,elasticnetparam for Linear regression. Update this file before building Jar

# Setup
1. All the external jars and added in the project and compiled by SBT. external jars are added to lib folder for sbt to detect and build.
2.Make native libraries available to Spark, Configure your Spark-Conf.env.sh or default conf to load Native Jars of sis-jhdf5
3.Download the Project and run sbt assembly

# Submit
spark-submit --class "songs.Main" --master local[*] /home/username/millionmusicdatanalysis/target/scala-2.11.8/songs.jar

