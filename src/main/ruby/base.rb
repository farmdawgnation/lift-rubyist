require 'java'

import Java::scala::Function1
import Java::scala::collection::immutable::List
import Java::scala::xml::Text
import Java::scala::xml::NodeSeq

import Java::net::liftweb::http::SHtml
import Java::net::liftweb::http::js::JsCmds
import Java::net::liftweb::util::Helpers
import Java::net::liftweb::util::CanBind
import Java::net::liftweb::util::ClearClearable

import Java::me::frmr::rubyist::ModuleHelpers

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
    helpers = ModuleHelpers.new

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
