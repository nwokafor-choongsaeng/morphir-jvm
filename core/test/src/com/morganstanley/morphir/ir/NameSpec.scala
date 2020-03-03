package com.morganstanley.morphir.ir

import zio.test._
import zio.test.Assertion._
import zio.test.environment._

object NameSpec
    extends DefaultRunnableSpec(
      suite("NameSpec")(
        suite("Make a Name from a string and check that:")(
          suite("Name should be creatable from a single word that:")(
            test("Starts with a capital letter") {
              assert(
                Name.fromString("Marco"),
                equalTo(Name(List("marco")))
              )
            },
            test("Is all lowercase") {
              assert(Name.fromString("polo"), equalTo(Name(List("polo"))))
            }
          ),
          suite("Name should be creatable from compound words that:")(
            test("Are formed from a snake case word") {
              assert(
                Name.fromString("super_mario_world"),
                equalTo(Name(List("super", "mario", "world")))
              )
            },
            test("Contain many kinds of word delimiters") {
              val result = Name.fromString("fooBar_baz 123")
              assert(result, equalTo(Name(List("foo", "bar", "baz", "123"))))
            },
            test("Are formed from a camel-cased string") {
              val result = Name.fromString("valueInUSD")
              assert(result, equalTo(Name(List("value", "in", "u", "s", "d"))))
            },
            test("Are formed from a title-cased string") {
              val result = Name.fromString("ValueInUSD")
              assert(result, equalTo(Name(List("value", "in", "u", "s", "d"))))
            },
            test("Complete and utter nonsense") {
              assert(Name.fromString("_-%"), equalTo(Name(List.empty)))
            }
          )
        ),
        suite("Name should be convertible to a title-case string:")(
          test(
            "When the name was originally constructed from a snake-case string"
          ) {
            val sut = Name.fromString("snake_case_input")
            assert(Name.toTitleCase(sut), equalTo("SnakeCaseInput"))
          },
          test(
            "When the name was originally constructed from a camel-case string"
          ) {
            val sut = Name.fromString("camelCaseInput")
            assert(Name.toTitleCase(sut), equalTo("CamelCaseInput"))
          }
        ),
        suite("Name should be convertible to a camel-case string:")(
          test(
            "When the name was originally constructed from a snake-case string"
          ) {
            val sut = Name.fromString("snake_case_input")
            assert(Name.toCamelCase(sut), equalTo("snakeCaseInput"))
          },
          test(
            "When the name was originally constructed from a camel-case string"
          ) {
            val sut = Name.fromString("camelCaseInput")
            assert(Name.toCamelCase(sut), equalTo("camelCaseInput"))
          }
        )
      )
    )