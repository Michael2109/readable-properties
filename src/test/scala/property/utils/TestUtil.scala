package property.utils

import fastparse.core.Parsed
import fastparse.noApi.P
import property.ast.AST.PropertyContainer
import property.parser.StatementParser

import scala.util.Failure

object TestUtil {

  def parse(text: String, parser: P[_]) = {
    parser.parse(text) match {
      case Parsed.Success(value, _) => value
      case Parsed.Failure(a, b, c) => throw new Exception("Failed parsing:" + a + ":" + b + ":" + c)
    }
  }

  def parsePropertyString(text: String): PropertyContainer = {
    StatementParser.propertySourceParser.parse(text) match {
      case Parsed.Success(value, _) => value
      case Parsed.Failure(a, b, c) => throw new Exception("Failed parsing:" + a + ":" + b + ":" + c)
    }
  }

}
