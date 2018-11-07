
import Control._


object Shingling {
  var noofDocs = 0
  def main(args: Array[String]): Unit = {
    var dataset = " "
    val documentsCorpus = new java.io.File("data").listFiles.filter(_.getName.endsWith(".txt"))
    timed( for (doc <- documentsCorpus) {
      using(io.Source.fromFile(doc)) { source => {
        println(doc)
        dataset = dataset + source
        noofDocs = noofDocs +1
      }}})


    print ("Transforming data...")

  }


  //=============================================================================
  //               Convert Documents To Sets of Shingles of k-sizes
  // =============================================================================

  //print("Please Enter lenght of K-Shingles:")
  //val kGram = Console.readLine()

  // measures time taken by a block of code
  def timed[A](block: => A) = {
    val t0 = System.currentTimeMillis
    val result = block

    println(" Reading " + noofDocs + " documents took " + (System.currentTimeMillis - t0) + " ms")
    result
  }

}