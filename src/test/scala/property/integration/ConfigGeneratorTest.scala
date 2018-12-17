package property.integration

import java.io.File

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}
import property.reader.ConfigGenerator

@RunWith(classOf[JUnitRunner])
class ConfigGeneratorTest extends FunSpec with Matchers {

  describe("Test reading a config file") {
    it("Should parse and read a  config file") {
      val configuration = ConfigGenerator.parseFile("property/integration/config_reader_test.prop")
      configuration.getString("number_group.integer") shouldBe("50132")
      configuration.getString("number_group.double") shouldBe("10.33456")
      configuration.getString("array_group.array") shouldBe("this is a string,1,2.034")
    }
  }
}
