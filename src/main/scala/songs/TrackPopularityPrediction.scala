package songs

import org.apache.spark.sql.DataFrame
import org.apache.spark.ml._
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.{Normalizer, VectorAssembler}
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.TrainValidationSplit

object TrackPopularityPrediction {


  def predictSongs(track:DataFrame):Unit = {

    val trackFeatures = track.select(track("songHotness"),track("songDuration"),track("songEnergy"),track("songTempo"),track("songLoudness"),track("songDancebility"))
          .filter(!track("songHotness").isNaN && !track("songDuration").isNaN && !track("songEnergy").isNaN && !track("songTempo").isNaN &&  !track("songLoudness").isNaN && !track("songDancebility").isNaN)

      val Array(train,test) = trackFeatures.randomSplit(Array(0.9,0.2))
      val assembler = new VectorAssembler()
            .setInputCols(Array("songHotness","songDuration","songEnergy","songTempo","songLoudness","songDancebility"))
            .setOutputCol("features")


      val normalizer = new Normalizer()
            .setInputCol("features")
            .setOutputCol("normalizedfeatures")
            .setP(2.0)


      val lr = new LinearRegression()
            .setLabelCol("songHotness")
            .setFeaturesCol("normalizedfeatures")
            .setMaxIter(Config.maxIter)
            .setRegParam(Config.regparam)
            .setElasticNetParam(Config.elasticnetparam)

      val pipleline = new Pipeline().setStages(Array(assembler,normalizer,lr))
      val lrModel = pipleline.fit(trackFeatures)

      lrModel.transform(test).write.mode("Overwrite").save("predictionsongpopularity.paraquet")

  }

}

