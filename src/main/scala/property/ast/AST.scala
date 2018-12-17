package property.ast

import property.ast.IR.{PropertyContainerIR, PropertyElementIR, PropertyGroupIR}

object AST {

  trait Expression

  case class Identifier(value: String) extends Expression

  case class ValueArray(value: Seq[Expression]) extends Expression

  case class StringLiteral(value: String) extends Expression

  case class BoolConst(value: Boolean) extends Expression

  case class Not() extends Expression

  case class IntConst(value: BigInt) extends Expression

  case class IntObject(value: Property) extends Expression

  case class LongConst(value: BigInt) extends Expression

  case class DoubleConst(value: BigDecimal) extends Expression

  case class FloatConst(value: BigDecimal) extends Expression

  case class Neg(expression: Expression) extends Expression

  trait Property

  case class PropertyElement(identifier: Identifier, value: Expression) extends Property

  case class PropertyGroup(identifier: Identifier, properties: Seq[Property]) extends Property

  case class PropertyContainer(properties: Seq[Property])

  def propertyContainerToIR(propertyContainer: PropertyContainer): PropertyContainerIR = {
    val propertyIRs = propertyContainer.properties.map(property => {
      property match {
        case group: PropertyGroup => propertyGroupToIR(group)
        case element: PropertyElement => propertyElementToIR(element)
      }
    })
    PropertyContainerIR(propertyIRs)
  }

  def propertyGroupToIR(propertyGroup: PropertyGroup): PropertyGroupIR = {
    PropertyGroupIR(propertyGroup.identifier.value, propertyGroup.properties.map(property => {
      property match {
        case group: PropertyGroup => propertyGroupToIR(group)
        case element: PropertyElement => propertyElementToIR(element)
      }
    }))
  }

  def propertyElementToIR(propertyElement: PropertyElement): PropertyElementIR = {
    PropertyElementIR(propertyElement.identifier.value, expressionToIR(propertyElement.value))
  }

  def expressionToIR(expression: Expression): String = {
    expression match {
      case identifier: Identifier => identifier.value
      case stringLiteral: StringLiteral => stringLiteral.value
      case intConst: IntConst => intConst.value.toString
      case doubleConst: DoubleConst => doubleConst.value.toString
      case valueArray: ValueArray => valueArray.value.map(expressionToIR).mkString(",")
    }
  }
}
