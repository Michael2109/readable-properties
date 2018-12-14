package property.config

import property.reader.{ConfigReader, Configuration}

object ApplicationConfig {
    val configuration: Configuration = new ConfigReader().parseFile("config/config.prop")

    val PropertySeparator: String = configuration.getString("property_separator")
}
