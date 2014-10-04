package me.frmr.rubyist

import scala.xml.NodeSeq
import scala.io.Source

import net.liftweb._
  import common._
  import util._
    import Helpers._

import org.jruby.embed._

/**
 * The RubySnippetRunner allows you to run a Ruby script and retrieve a
 * (NodeSeq)=>NodeSeq function that can be executed like any other Lift
 * snippet method.
 *
 * The constructor takes two parameters: a modulesPath from which ruby
 * scripts will be loaded, and a ScriptingContainer that defines the
 * ScriptingContainer object you want to use to execute the scripts with.
 *
 * The modulesPath will be read from the Java prop modules.path by default
 * and the ScriptingContainer just uses the default settings. As a part of
 * instantiation, the ClassLoader for the ScriptingContainer will be set to
 * this class's ClassLoader, as the default one seems to lack access to the
 * neccicary Scala components. This means that, for now, you shouldn't
 * allow untrusted users to define snippets. They could do mischevious things.
 *
 * @param modulesPath The path from which to load modules.
 * @param container The ScriptingContainer inside of which ruby scripts will execute.
**/
class RubySnippetRunner(
  modulesPath: String = Props.get("modules.path").openOr(""),
  container: ScriptingContainer = new ScriptingContainer
) extends Loggable {
  container.setClassLoader(getClass.getClassLoader)

  /**
   * Evaluate a ruby snippet, returning the result. This method will resolve the
   * snippet path using the format `modulesPath/moduleName/snippetName`. So, if
   * you have a module named "posts" and a ruby snippet named "view" you would
   * execute something like `evaluate("posts", "view")` and this method would
   * load `<modulesPath>/posts/view.rb`.
   *
   * This method will pull in and evaluate the defined base.rb that sets up some
   * required helpers for CSS transforms to work in Ruby land.
   *
   * @param moduleName The name of the module that the Ruby Snippet resides in.
   * @param snippetName The name of the ruby snippet, minus the ".rb" on the end.
   * @return A (NodeSeq)=>NodeSeq.
  **/
  def evaluate(moduleName: String, snippetName: String): (NodeSeq)=>NodeSeq = {
    val moduleFilename = s"$modulesPath/$moduleName/$snippetName.rb"

    val baseModuleContent = getClass.getClassLoader.getResourceAsStream("base.rb")
    container.runScriptlet(baseModuleContent, "base.rb")

    val rubyDefinedTransformRunner = container.parse(PathType.RELATIVE, moduleFilename)
    val rubyDefinedTransform = rubyDefinedTransformRunner.run

    rubyDefinedTransform.toJava(classOf[(NodeSeq)=>NodeSeq]).asInstanceOf[(NodeSeq)=>NodeSeq]
  }
}
