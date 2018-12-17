package property.integration

import org.junit.runner.RunWith
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner
import property.ast.{AST, IR}
import property.ast.AST.PropertyContainer
import property.parser.StatementParser
import property.utils.TestUtil

import scala.io.Source

@RunWith(classOf[JUnitRunner])
class ParseFileTest extends FunSpec with Matchers {

  describe("Test parsing and reading from a file") {
    it("Should parse and read a file") {
      val code = Source.fromResource("property/integration/parse_file_test.prop").getLines().mkString("\n")
      val propertyContainer: PropertyContainer = TestUtil.parsePropertyString(code)

      val ir = AST.propertyContainerToIR(propertyContainer)
      val properties = IR.flattenProperties(ir)

      properties.getOrElse("a.b.c", null) shouldBe "5"
      properties.getOrElse("a.d", null) shouldBe "10"
      properties.getOrElse("e.f", null) shouldBe "15"

    }
  }
}
