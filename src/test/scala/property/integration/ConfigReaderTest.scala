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
      configuration.getString("numbers.integer") shouldBe("50132")
      configuration.getString("numbers.double") shouldBe("10.33456")
      configuration.getString("arrays.array") shouldBe("this is a string,1,2.034")
    }
  }
}
