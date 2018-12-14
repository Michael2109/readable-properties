package property.reader

class Configuration(propertyMap: Map[String, String]) {

  def getString(key: String): String ={
    propertyMap.get(key) match {
      case Some(value) => value
      case None => throw new Exception("Key not found: " + key)
    }
  }

}
