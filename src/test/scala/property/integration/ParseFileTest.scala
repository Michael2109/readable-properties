package property.integration

import org.junit.runner.RunWith
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner
import property.parser.StatementParser
import property.utils.TestUtil

import scala.io.Source

@RunWith(classOf[JUnitRunner])
class ParseFileTest extends FunSpec with Matchers {

  describe("Test parsing and reading from a file") {
    it("Should parse and read a file") {
      val code = Source.fromFile("src/test/resources/property/integration/parse_file_test.prop").mkString("\n")
      println(TestUtil.parse(code, StatementParser.propertySourceParser))
    }
  }
}
