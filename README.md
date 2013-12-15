# Lift Rubyist

This porject is the result of a different, larger project that I decided to
scrap. The purpose of Lift Rubyist is to provide some glue that allows you
to create Lift CSS transforms in Ruby using the embedded JRuby runner.

It's not quite functional in this context yet, as is typical with things
that you rip out of a different project, but I hope to have some time in the
coming months to dedicate toward getting things in better shape. At the very
least this instance of it should save someone looking to script Lift snippets
in Ruby a lot of time in research.

## Example

There's a lot of documentation that's left to be done here, but in the event
you're wondering what CSS transforms in Ruby might look like, here's a pretty
good example:

```ruby
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
```
