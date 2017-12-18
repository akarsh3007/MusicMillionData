package songs

import com.typesafe.config.ConfigFactory


object Config {
  val config = ConfigFactory.load()
  val appName = config.getString("music.appname")
  val inputDir = config.getString("music.inputdir")
  val master = config.getString("music.master")
  val year = config.getInt("music.yearfortracks")
  val maxIter = config.getInt("music.maxIter")
  val regparam = config.getDouble("music.regparam")
  val elasticnetparam = config.getDouble("music.elasticnetparam")

}
