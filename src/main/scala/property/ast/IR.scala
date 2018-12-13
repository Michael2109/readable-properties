package property.ast

object IR {

  trait Expression

  trait PropertyIR

  case class PropertyElementIR(identifier: String, value: String) extends PropertyIR

  case class PropertyGroupIR(identifier: String, properties: Seq[PropertyIR]) extends PropertyIR

  def flattenProperties(properties: Seq[PropertyIR]): Map[String, String] = {
      null
  }

/*
  def other(properties: Seq[Property]): Unit = {
    properties.map(property => {
      astToPropertyMap(property)
    })
  }

  def propertyGroupToMap(propertyGroup: PropertyGroup): Map[String, Seq[String]] ={

  }

  def propertyElementToMap

  def astToPropertyMap(properties: Seq[Property]): Map[String, Seq[String]] = {

    property match {
      case group: PropertyGroup => propertyGroupToMap(group)
      case element: PropertyElement => HashMap(element.identifier -> List(element.value))
    }

  }*/

}
