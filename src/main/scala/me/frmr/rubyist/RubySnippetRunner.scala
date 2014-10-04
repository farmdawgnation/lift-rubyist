package me.frmr.rubyist

import scala.xml.NodeSeq

import net.liftweb._
  import common._
  import util._
    import Helpers._

import org.jruby.embed._

class RubySnippetRunner(
  modulesPath: String = Props.get("modules.path").openOr(""),
  container: ScriptingContainer = new ScriptingContainer
) extends Loggable {
  container.setClassLoader(getClass.getClassLoader)
  val baseModuleContent = getClass.getClassLoader.getResourceAsStream("base.rb")

  def evaluate(moduleName: String, snippetName: String): (NodeSeq)=>NodeSeq = {
    val moduleFilename = s"$modulesPath/$moduleName/$snippetName.rb"

    container.runScriptlet(baseModuleContent, "base.rb")
    val rubyDefinedTransformRunner = container.parse(PathType.RELATIVE, moduleFilename)
    val rubyDefinedTransform = rubyDefinedTransformRunner.run

    rubyDefinedTransform.toJava(classOf[(NodeSeq)=>NodeSeq]).asInstanceOf[(NodeSeq)=>NodeSeq]
  }
}
