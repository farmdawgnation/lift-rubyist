require 'java'

java_import Java::scala::Function1
java_import Java::scala::collection::immutable::List
java_import Java::scala::xml::Text
java_import Java::scala::xml::NodeSeq

java_import Java::net::liftweb::http::SHtml
java_import Java::net::liftweb::http::js::JsCmds
java_import Java::net::liftweb::util::Helpers
java_import Java::net::liftweb::util::CanBind
java_import Java::net::liftweb::util::ClearClearable

java_import Java::me::frmr::rubyist::RubySnippetHelpers

class ApplyHolder
  def initialize(held)
    @held = held
  end

  def apply
    @held
  end
end

class String
  def cssSel(newValue)
    helpers = RubySnippetHelpers.new

    if newValue.is_a? Fixnum
      newValue = newValue.to_s
    end

    canBindHandler =
      if newValue.java_kind_of? NodeSeq
        CanBind.toNodeSeqTransform { |t|
          newValue
        }
      elsif newValue.java_kind_of? Function1
        CanBind.nodeSeqFuncTransform { |t|
          newValue
        }
      elsif newValue.is_a? Array
        CanBind.iterableNodeFuncTransform { |array|
          helpers.rubyArrayToIterator(array)
        }
      else
        CanBind.stringTransform
      end

    Helpers.strToCssBindPromoter(self).send(:'#>', ApplyHolder.new(newValue), canBindHandler)
  end
end
