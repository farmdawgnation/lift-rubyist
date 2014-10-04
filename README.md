# Lift Rubyist

This project is the result of a different, larger project that I decided to
scrap. The purpose of lift-rubyist is to provide some glue that allows you
to create Lift CSS transforms in Ruby using the embedded JRuby runner.

I was working on a side-project at the time that was intended to be a Lift
powered Content Management System Platform. Essentially, a platform on which
consultancies and other organizations could build their own Content Management
System on top of, and augment whatever functionality I provided by default from
Scala-land with functionality they implement in Ruby-land and upload to a UI.
This was the component that would have taken that code written in Ruby-land and
put it in a format the Lift pipeline could have used to manipulate HTML.

This code should not be considered "production ready" and certainly shouldn't
be considered "secure" such as it is. This was primarily a proof of concept
implementation that was intended to demonstrate the vaibility of Ruby-powered
transforms.

## Usage

There's a lot of documentation that I could probably do here, but in the event
you're wondering what CSS transforms in Ruby might look like, here's a pretty
good example, provided you have some Ruby hash named `post` with some
appropreate fields:

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

The `cssSel` method is one that's provided for you and is attached onto
the String implementation in Ruby-land. It promotes the string to a CSS selector
much like the implicit conversions in the Scala compiler would in Scala-land.
The rest is pretty much regular Lift code invoked Ruby-style. SHtml and JsCmds
and a few other things are brought into scope before your script is executed,
meaning you should have access to everything you need to build a working Lift
snippet. `helpers.text` above is an alias to `SHtml.text` which, for some
reason couldn't be referenced directly when I was first working on the project.

Also worth noting above, Ruby `Proc`s do work like normal Scala functions as callbacks.
