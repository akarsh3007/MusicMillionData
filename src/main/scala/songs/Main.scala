package songs

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql._
import FindTopTen._
import org.slf4j.LoggerFactory
import songs.Songs._
import TrackPopularityPrediction._
object Main {

  def main(args: Array[String]): Unit = {


    val conf = new SparkConf().setAppName(Config.appName).setMaster(Config.master)
    val sc = new SparkContext(conf)

    val logger = LoggerFactory.getLogger(getClass.getName)

    val sqlContext = SparkSession.builder().enableHiveSupport().getOrCreate()
    import sqlContext.implicits._

    logger.info("Listing HDF5 input files")
    val files: Vector[String] = Files.getPaths(Config.inputDir)

    logger.info("Sending file list to cluster")
    val h5PathRDD = sc.parallelize(files)

    logger.info("Reading song features from input files")

    val songs: RDD[Song] = h5PathRDD.map(HDF5.open).flatMap(_.toOption)
      .map(ReadSong.readSongs).flatMap(_.toOption)

    val tracks = songs.map(x=>x.track)
    val artists = songs.map(x=>x.artist)

    topTenArtists(artists.toDF())
    topTenSongs(tracks.toDF(),Config.year)
    predictSongs(tracks.toDF())



    sc.stop()
  }
}
