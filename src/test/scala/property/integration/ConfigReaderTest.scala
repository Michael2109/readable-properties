package property.integration

import java.io.File

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}
import property.reader.ConfigReader

@RunWith(classOf[JUnitRunner])
class ConfigReaderTest extends FunSpec with Matchers {

  describe("Test reading a config file") {
    it("Should parse and read a  config file") {
      val configuration = new ConfigReader().parseFile("property/integration/config_reader_test.prop")
      configuration.getString("a.b.c") shouldBe("5")
    }
  }
}
