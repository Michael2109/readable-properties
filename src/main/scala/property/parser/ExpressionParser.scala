package property.parser

import fastparse.noApi._
import WsApi._
import property.ast.AST._
import property.ast.AST

object ExpressionParser {

  val expressionParser: P[Expression] = numberParser | identifierParser | stringLiteral

  def identifierParser: P[AST.Identifier] = LexicalParser.identifier.map(x => Identifier(x))

  def numberParser: P[Expression] = P(LexicalParser.floatnumber ~ P("F" | "f")).map(FloatConst) | P(LexicalParser.longinteger).map(LongConst) | P(LexicalParser.floatnumber).map(DoubleConst) | P(LexicalParser.integer).map(IntConst)

  def stringLiteral: P[StringLiteral] = LexicalParser.stringliteral.map(x => StringLiteral(x))
}
