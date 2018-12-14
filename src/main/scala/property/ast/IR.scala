package property.ast

import property.config.ApplicationConfig

object IR {

  trait Expression

  trait PropertyIR

  case class PropertyElementIR(identifier: String, value: String) extends PropertyIR

  case class PropertyGroupIR(identifier: String, properties: Seq[PropertyIR]) extends PropertyIR

  case class PropertyContainerIR(properties: Seq[PropertyIR])

  def flattenProperties(propertyContainerIR: PropertyContainerIR): Map[String, String] = {
    val flattenedPropertyMaps = propertyContainerIR.properties.map(property => flattenProperty(property, Seq.empty))
    val combinedMaps = flattenedPropertyMaps.flatten
    combinedMaps.map(entry => entry._1.mkString(ApplicationConfig.PropertySeparator) -> entry._2).toMap
  }

  def flattenProperty(property: PropertyIR, previousKeys: Seq[String]): Map[Seq[String], String] ={
    property match {
      case groupIR: PropertyGroupIR => groupIR.properties.map(p => flattenProperty(p, previousKeys :+ groupIR.identifier)).reduce(_ ++ _)
      case elementIR: PropertyElementIR => Map((previousKeys :+ elementIR.identifier) -> elementIR.value)
    }
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
