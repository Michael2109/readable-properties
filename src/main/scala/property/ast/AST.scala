package property.ast

object AST {

  trait Expression
  case class Identifier(value: String) extends Expression
  case class StringLiteral(value: String) extends Expression
  case class BoolConst(value: Boolean) extends Expression
  case class Not() extends Expression
  case class IntConst(value: BigInt) extends Expression
  case class IntObject(value: Statement) extends Expression
  case class LongConst(value: BigInt) extends Expression
  case class DoubleConst(value: BigDecimal) extends Expression
  case class FloatConst(value: BigDecimal) extends Expression
  case class Neg(expression: Expression) extends Expression

  trait Statement
  case class Property(identifier: Identifier, value: Expression) extends Statement
  case class PropertyGroup(expression: Expression, statement: Seq[Statement]) extends Statement
}
