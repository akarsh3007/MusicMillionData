package songs

object Songs {

  case class Artist(
      artistId:String,
      artistName:String,
      artistLocation:String,
      artistLongitude:Double,
      artistLatitude:Double,
      artistHotness:Double,
      artistFamiliarity:Double
   )

  case class Track(

      trackId:String,
      title:String,
      release:String,
      songHotness:Double,
      year:Int,
      songId:String,
      songLoudness:Double,
      songDuration:Double,
      songDancebility:Double,
      songEnergy:Double,
      mode:Int,
      songTempo:Double,
      songGenre:String
)

  case class Song(
      artist:Artist,
      track:Track
   )

}
