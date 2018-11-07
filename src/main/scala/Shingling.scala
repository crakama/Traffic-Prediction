import org.jsoup.Jsoup
import org.jsoup.nodes.{Document, Element}
import Control._

object Shingling {
  var noofDocs = 0
  def main(args: Array[String]): Unit = {
    var dataset = " "
    val documentsCorpus = new java.io.File("data").listFiles.filter(_.getName.endsWith(".sgm"))
    timed( for (doc <- documentsCorpus) {
      using(io.Source.fromFile(doc,"iso-8859-1")) { source => {
       // println(source.mkString)
        dataset = dataset + source.mkString
        noofDocs = noofDocs +1
      }}})

    print ("Transforming data...")
    getAllLinks(dataset)

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

  def getAllLinks(content: String) = {
    ////println(content)
    var documents = List[String]()
    val doc: Document = Jsoup.parse(content, "iso-8859-1")
    val bodyWords = doc.select("body").first().text()
    val replace: String = bodyWords.replaceAll("[^\\w\\s]", " ").toLowerCase()
      //val replace = bodyWords.replaceAll("[\\.$|,|;|:|'|=|\\|^\\\"|\\\"$ |\\[ |\\]|}|\\( \\) ]", " ").toLowerCase
      documents = replace :: documents
    println(documents)
  }

}