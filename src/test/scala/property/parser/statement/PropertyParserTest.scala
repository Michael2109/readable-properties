/*
 * Cobalt Programming Language Compiler
 * Copyright (C) 2017 Michael Haywood
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package property.parser.statement

import property.ast.AST._
import property.parser.StatementParser
import property.utils.TestUtil
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class PropertyParserTest extends FunSpec with Matchers {

  describe("Properties in whole file"){
    it("Should parse an empty file"){
      val code =
        """""".stripMargin.replace("\r", "")
      TestUtil.parse(code, StatementParser.propertySourceParser) shouldBe ArrayBuffer()
    }
  }

  describe("Property group parsers"){

    it("Should parse a property group with no properties"){
      val code =
        """groupName:
        """.stripMargin.replace("\r", "")
      TestUtil.parse(code, StatementParser.propertySourceParser) shouldBe ArrayBuffer(PropertyGroup(Identifier("groupName"),ArrayBuffer()))
    }

    it("Should parse a property group with 1 property"){
      val code =
        """groupName:
          |  x->1
        """.stripMargin.replace("\r", "")
      TestUtil.parse(code, StatementParser.propertySourceParser) shouldBe ArrayBuffer(PropertyGroup(Identifier("groupName"),ArrayBuffer(Property(Identifier("x"),IntConst(1)))))
    }
    it("Should parse a property group with multiple properties"){
      val code =
        """groupName:
          |  x->1
          |  y->2
          |  z->3
        """.stripMargin.replace("\r", "")
      TestUtil.parse(code, StatementParser.propertySourceParser) shouldBe ArrayBuffer(PropertyGroup(Identifier("groupName"),ArrayBuffer(Property(Identifier("x"),IntConst(1)), Property(Identifier("y"),IntConst(2)), Property(Identifier("z"),IntConst(3)))))
    }
    it("Should parse a property group with nested property groups"){
      val code =
        """groupName1:
          |  x->1
          |  groupName2:
          |    a->1
          |    b->2
          |    c-3
          |  y->2
          |  z->3
        """.stripMargin.replace("\r", "")
      TestUtil.parse(code, StatementParser.propertySourceParser) shouldBe ArrayBuffer(PropertyGroup(Identifier("groupName1"),ArrayBuffer(Property(Identifier("x"),IntConst(1)), PropertyGroup(Identifier("groupName2"),ArrayBuffer(Property(Identifier("a"),IntConst(1)), Property(Identifier("b"),IntConst(2)))))))
    }
  }

  describe("Property parsers") {
    it("Should parse an integer property") {
      TestUtil.parse("x->1", StatementParser.propertySourceParser) shouldBe ArrayBuffer(Property(Identifier("x"), IntConst(1)))
    }
    it("Should parse a double property") {
      TestUtil.parse("x->1.123456789", StatementParser.propertySourceParser) shouldBe ArrayBuffer(Property(Identifier("x"), DoubleConst(1.123456789)))
    }
    it("Should parse a string literal property") {
      TestUtil.parse("x->\"This is a string property\"", StatementParser.propertySourceParser) shouldBe ArrayBuffer(Property(Identifier("x"),StringLiteral("This is a string property")))
    }
  }
}
