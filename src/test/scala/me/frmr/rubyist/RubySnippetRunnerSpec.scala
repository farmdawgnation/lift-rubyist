package me.frmr.rubyist

import org.scalatest._

class RubySnippetRunnerSpec extends WordSpec {
  val testRunner = new RubySnippetRunner("src/test/resources")

  "RubySnippetRunner" should {
    "produce a correct (NodeSeq)=>NodeSeq for list-posts.rb" in {
      val listPostsSnippet = testRunner.evaluate("testsnippets", "list-posts")
      pending
    }

    "produce a correct (NodeSeq)=>NodeSeq for view-post.rb" in {
      pending
    }
  }
}
