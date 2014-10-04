package me.frmr.rubyist

import scala.xml._
import org.scalatest._
import org.scalatest.Assertions._
import org.scalatest.StreamlinedXmlEquality._

import net.liftweb.mockweb.MockWeb._

class RubySnippetRunnerSpec extends WordSpec with ShouldMatchers {
  val testRunner = new RubySnippetRunner("src/test/resources")

  "RubySnippetRunner" should {
    "produce a correct (NodeSeq)=>NodeSeq for list-posts.rb" in {
      val listPostsSnippet = testRunner.evaluate("testsnippets", "list-posts")
      val templateMarkup =
        <ul class="posts">
          <li class="post">
            <h1 class="title"><a href="#" class="post-link">Title</a></h1>
            <span class="date-time">Genesis</span>
            <div class="body">Some body</div>
            <div class="comments-count">0</div>
          </li>
        </ul>

      val resultingNodes = listPostsSnippet.apply(templateMarkup)
      val expectedNodes =
        <ul class="posts">
          <li class="post">
            <h1 class="title">All About Google</h1>
            <span class="date-time">July 12, 2013 00:00:00 PM</span>
            <div class="body">Google is some awesome stuff.</div>
            <div class="comments-count">0</div>
          </li><li class="post">
            <h1 class="title">All About Microsoft</h1>
            <span class="date-time">July 11, 2013 00:00:00 PM</span>
            <div class="body">Those players be hatin.</div>
            <div class="comments-count">0</div>
          </li><li class="post">
            <h1 class="title">All about Bacon</h1>
            <span class="date-time">July 10, 2013 00:00:00 PM</span>
            <div class="body">It's delicious.</div>
            <div class="comments-count">0</div>
          </li>
        </ul>

      // Sucky way to have to run this test but something funky is up with
      // NodeSeq equality atm.
      resultingNodes.toString should equal(expectedNodes.toString)
    }
  }
}
