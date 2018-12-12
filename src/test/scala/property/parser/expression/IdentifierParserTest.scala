package property.parser.expression

import property.ast.AST.{Identifier}
import property.parser.{ExpressionParser, StatementParser}
import property.utils.TestUtil
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class IdentifierParserTest extends FunSpec with Matchers
{
  describe("Identifier parser")
  {
    it("Should parse identifiers")
    {
      TestUtil.parse("identifier", ExpressionParser.expressionParser) shouldBe Identifier("identifier")
    }
  }
}
