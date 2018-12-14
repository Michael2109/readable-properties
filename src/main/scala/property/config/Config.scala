package property.config

import property.reader.{ConfigReader, Configuration}

object Config {
    val configuration: Configuration = new ConfigReader().parseFile("config.prop")

    val PropertySeparator: String = "."
}
