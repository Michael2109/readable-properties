package property.reader

class Configuration(propertyMap: Map[String, String]) {

  def getString(key: String): String ={
    getValue(key)
  }

  def getLong(key: String): Long ={
    getValue(key).toLong
  }

  def getInt(key: String): Int ={
    getValue(key).toInt
  }

  def getDouble(key: String): Double ={
    getValue(key).toDouble
  }

  def getFloat(key: String): Float ={
    getValue(key).toFloat
  }

  private def getValue(key: String): String ={
    propertyMap.get(key) match {
      case Some(value) => value
      case None => throw new Exception("Key not found: " + key)
    }
  }
}
