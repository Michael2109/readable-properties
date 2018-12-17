package property.parser.expression

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}
import property.ast.AST._
import property.parser.ExpressionParser
import property.utils.TestUtil

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class ArrayParserTest extends FunSpec with Matchers
{
  describe("Array parser")
  {
    it("Should parse arrays with string literal")
    {
      TestUtil.parse("[\"a\"]", ExpressionParser.arrayParser) shouldBe ValueArray(ArrayBuffer(StringLiteral("a")))
    }

    it("Should parse mixed arrays")
    {
      TestUtil.parse("[\"a\", bt, 10, 1.03]", ExpressionParser.arrayParser) shouldBe ValueArray(ArrayBuffer(StringLiteral("a"), Identifier("bt"), IntConst(10), DoubleConst(1.03)))
    }
  }
}
