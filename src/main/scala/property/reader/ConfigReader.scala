package property.reader

import java.io.File

import fastparse.core.Parsed
import property.ast.{AST, IR}
import property.ast.AST.PropertyContainer
import property.parser.StatementParser

import scala.io.Source

class ConfigReader {
  def parseFile(file: File): Configuration = {

    val code = Source.fromFile(file).getLines().mkString("\n")

    val propertyContainer: PropertyContainer = StatementParser.propertySourceParser.parse(code) match {
      case Parsed.Success(value, _) => value
      case Parsed.Failure(a, b, c) => throw new Exception("Failed parsing:" + a + ":" + b + ":" + c)
    }

    val ir = AST.propertyContainerToIR(propertyContainer)

    val properties = IR.flattenProperties(ir)

    new Configuration(properties)
  }
}
