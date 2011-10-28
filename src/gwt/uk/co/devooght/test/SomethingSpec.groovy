package uk.co.devooght.test

class SomethingSpec extends grails.plugin.spock.UnitSpec {

  def "first test"() {
    when: "something"
      def edge = "wibble"

    then:
      edge=="wibble"
  }

}
