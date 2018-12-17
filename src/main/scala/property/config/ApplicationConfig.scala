package property.config

import property.reader.{ConfigGenerator, Configuration}

object ApplicationConfig {
    val configuration: Configuration = ConfigGenerator.parseFile("config/config.prop")

    val PropertySeparator: String = configuration.getString("property_separator")

    val Debug: Boolean = configuration.getBoolean("debug")
}
