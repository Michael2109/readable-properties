package property.reader

import java.io.File

import fastparse.core.Parsed
import property.ast.AST.PropertyContainer
import property.ast.{AST, IR}
import property.parser.StatementParser

import scala.io.Source

object ConfigGenerator {

  /**
    * Gets an empty configuration.
    *
    * @return
    */
  def empty(): Configuration = {
    new Configuration(Map.empty)
  }

  /**
    * Reloads any cached configs, picking up changes to system properties for example.
    *
    * @return
    */
  def invalidateCaches(): Configuration = {
    null
  }

  def parseFile(file: File): Configuration = {
    parseFile(file.getAbsolutePath)
  }

  def parseFile(filePath: String): Configuration = {

    val code = Source.fromResource(filePath).getLines().mkString("\n")

    val propertyContainer: PropertyContainer = StatementParser.propertySourceParser.parse(code) match {
      case Parsed.Success(value, _) => value
      case Parsed.Failure(a, b, c) => throw new Exception("Failed parsing:" + a + ":" + b + ":" + c)
    }

    val ir = AST.propertyContainerToIR(propertyContainer)

    val properties = IR.flattenProperties(ir)

    new Configuration(properties)
  }
}
