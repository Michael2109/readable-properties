package property.parser.expression

import property.ast.AST._
import property.parser.ExpressionParser
import property.utils.TestUtil
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class BExprParserTest extends FunSpec with Matchers
{
  describe("Boolean statementParser parsers")
  {
    it("Should parse boolean constant `true`")
    {
      TestUtil.parse("true", ExpressionParser.expressionParser) shouldBe Identifier("true")
    }
    it("Should parse boolean constant `false`")
    {
      TestUtil.parse("false", ExpressionParser.expressionParser) shouldBe Identifier("false")
    }
  }
}
