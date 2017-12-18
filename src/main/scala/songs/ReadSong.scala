package songs

import songs.Songs._
import HDF5._
import ch.systemsx.cisd.hdf5._

import scala.util._

object ReadSong {

  def buildPath(group: String, s: String): String = {
    val sb = new StringBuilder(512)
    sb.append('/')
    sb.append(group)
    sb.append('/')
    sb.append(s)
    sb.mkString
  }

  def meta(s: String): String = buildPath("metadata",s)
  def an(s: String): String = buildPath("analysis",s)
  def mbPath(s: String): String = buildPath("musicbrainz",s)

  def readSongs(f: IHDF5Reader): Try[Song] = Try{
    val metadata = getCompoundDS(f,"/metadata/songs")
    val analysis = getCompoundDS(f,"/analysis/songs")
    val mb = getCompoundDS(f,"/musicbrainz/songs")

    val danceability = analysis.get[Double](Constants.danceability)
    val duration = analysis.get[Double](Constants.duration)
    val energy = analysis.get[Double](Constants.energy)
    val loudness = analysis.get[Double](Constants.loudness)
    val tempo = analysis.get[Double](Constants.tempo)
    val artist_familiarity = metadata.get[Double](Constants.artist_familiarity)
    val artist_hotnesss = metadata.get[Double](Constants.artist_hotttnesss)
    val artist_latitude = metadata.get[Double](Constants.artist_latitude)
    val artist_longitude = metadata.get[Double](Constants.artist_longitude)
    val song_hotttnesss = metadata.get[Double](Constants.song_hotttnesss)
    val mode = analysis.get[Int](Constants.mode)
    val artist_7digitalid = metadata.get[Int](Constants.artist_7digitalid)
    val release_7digitalid = metadata.get[Int](Constants.release_7digitalid)
    val track_7digitalid = metadata.get[Int](Constants.track_7digitalid)
    val year = mb.get[Int](Constants.year)
    val genre = metadata.get[String](Constants.genre)
    val track_id = analysis.get[String](Constants.track_id)
    val artist_id = metadata.get[String](Constants.artist_id)
    val artist_location = metadata.get[String](Constants.artist_location)
    val artist_name = metadata.get[String](Constants.artist_name)
    val release = metadata.get[String](Constants.release)
    val song_id = metadata.get[String](Constants.song_id)
    val title = metadata.get[String](Constants.title)

    val artist = Artist(artist_id,artist_name,artist_location,artist_longitude,artist_latitude,
      artist_hotnesss,artist_familiarity
    )

    val track = Track(track_id,title,release,song_hotttnesss,year,song_id,loudness,duration,danceability,
      energy,mode,tempo,genre
    )

    Song(artist,track)
  }
}
