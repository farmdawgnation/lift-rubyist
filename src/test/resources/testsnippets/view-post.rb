require 'modules/core/base'

helpers = ModuleHelpers.new

post = {
  permalink: "http://google.com/permalink",
  title: "All About Google",
  dateTime: "July 12, 2013 00:00:00 PM",
  body: "This is the body of the post.",
  comments: [
    {
      author: "Matt Farmer",
      body: "I'm awesome."
    },
    {
      author: "Ron Burgandy",
      body: "Hey everyone, come see how good I look!"
    }
  ]
}

commentAuthor = ""
comment = ""

SHtml.makeFormsAjax.andThen(
".post-link [href]".cssSel(post[:permalink]) &
".title *".cssSel(post[:title]) &
".date-time *".cssSel(post[:dateTime]) &
".body *".cssSel(post[:body]) &
".comment-count *".cssSel(post[:comments].length) &
".comment".cssSel(post[:comments].map { |comment|
  ".author *".cssSel(comment[:author]) &
  ".comment-body *".cssSel(comment[:body])
}) &
".new-comment-field".cssSel(helpers.text("", Proc.new {|newComment| comment = newComment})) &
"button".cssSel(SHtml.ajaxOnSubmit(Proc.new { JsCmds::Alert.new(comment + "!!!") }))
)
