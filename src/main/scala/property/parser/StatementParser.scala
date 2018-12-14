package property.parser

import property.ast.AST._
import property.parser.WsApi._
import fastparse.noApi._

object StatementParser extends Statements(0)

class Statements(indent: Int) {

  val space = P(CharIn(" \n"))
  val NEWLINE: P0 = P("\n" | "\r\n" | End)
  val ENDMARKER: P0 = P(End)
  val indents = P(NEWLINE ~~ " ".repX(indent))
  val spaces = P((LexicalParser.nonewlinewscomment.? ~~ "\n").repX(1))
  val space_indents = P(spaces.repX ~~ " ".repX(indent))
  val endLine = P("\n" ~~ (" " | "\t").repX(indent + 1).!.map(_.length) ~~ LexicalParser.comment.!.?)

  val propertyGroup: P[Property] = P(ExpressionParser.identifierParser ~ LexicalParser.kw(":") ~ indentedBlock).map(x => PropertyGroup(x._1, x._2))

  val propertyElement: P[Property] = P(ExpressionParser.identifierParser ~ "=" ~ ExpressionParser.expressionParser).map(x => PropertyElement(x._1, x._2))

  val commentParser: P[_] = P(LexicalParser.comment)

  val statementParser: P[Property] = P(!commentParser ~ (propertyGroup | propertyElement))

  val propertySourceParser: P[PropertyContainer] =  (statementParser ~ !endLine).repX(0).map(PropertyContainer)

  val indentedBlock: P[Seq[Property]] = {
    val deeper: P[Int] = {
      val commentLine = P("\n" ~~ LexicalParser.nonewlinewscomment.?.map(_ => 0)).map((_, Some("")))
      P(LexicalParser.nonewlinewscomment.? ~~ (endLine | commentLine).repX(1)).map {
        _.collectFirst { case (s, None) => s }
      }.filter(_.isDefined).map(_.get)
    }
    val indented: P[Seq[Property]] = P(deeper.flatMap { nextIndent =>
      new Statements(nextIndent).statementParser.repX(1, spaces.repX(1) ~~ (" " * nextIndent | "\t" * nextIndent)).map(x => x)
    })
    indented | (" ".rep ~ statementParser.rep())
  }
}
