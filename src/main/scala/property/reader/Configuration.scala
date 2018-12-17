package property.reader

class Configuration(propertyMap: Map[String, String]) {

  def getString(key: String): String = {
    getValue(key)
  }

  def getLong(key: String): Long = {
    getValue(key).toLong
  }

  def getInt(key: String): Int = {
    getValue(key).toInt
  }

  def getDouble(key: String): Double = {
    getValue(key).toDouble
  }

  def getFloat(key: String): Float = {
    getValue(key).toFloat
  }

  def getBoolean(key: String): Boolean = {
    getValue(key).toBoolean
  }

  def entrySet(): Map[String, String] = {
    propertyMap
  }

  private def getValue(key: String): String = {
    println(propertyMap)
    propertyMap.get(key) match {
      case Some(value) => value
      case None => throw new Exception("Key not found: " + key)
    }
  }
}
