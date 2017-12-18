package songs



import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SaveMode}


object FindTopTen {


    def topTenSongs(tracks:DataFrame,year:Int):Unit = {

      val processedTracks = tracks.filter(!tracks("songHotness").isNaN && !tracks("year").isNaN && tracks("year")>0)
      val years = processedTracks.select("year").distinct()
      val trackDetails = processedTracks.select(processedTracks("title"), processedTracks("trackId"), processedTracks("songHotness"), processedTracks("year"))
      val filteredTracks = trackDetails.filter(trackDetails("year") === year)
      val orderedTracks = filteredTracks.dropDuplicates("trackId").orderBy(desc("songHotness")).limit(10)
      orderedTracks.show()
      orderedTracks.write.mode("Overwrite").save(year + "-TopTracks.paraquet")
    }

  def topTenArtists(artist:DataFrame):Unit = {

    val processedArtists = artist.filter(!artist("artistHotness").isNaN && !artist("artistFamiliarity").isNaN)
    val artistDetails = processedArtists.select(processedArtists("artistName"),processedArtists("artistId"),(processedArtists("artistHotness")+processedArtists("artistFamiliarity")).alias("popularity"))
    val orderedArtists = artistDetails.dropDuplicates("artistId").orderBy(desc("popularity")).limit(10)
    orderedArtists.show()
    orderedArtists.write.mode("Overwrite").save("TopArtists.paraquet")

  }

}
