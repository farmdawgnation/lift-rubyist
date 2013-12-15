package me.frmr.rubyist

import scala.collection.JavaConversions._

import net.liftweb.http.SHtml

import org.jruby._

class RubySnippetHelpers {
  def rubyArrayToIterator(rubyArray: RubyArray): Iterator[_] = rubyArray.iterator

  def text(value: String, func: (String)=>Any) = {
    SHtml.text(value, func)
  }
}
