posts = [
  {
    permalink: "http://my.blog.com/permalink1",
    title: "All About Google",
    dateTime: "July 12, 2013 00:00:00 PM",
    body: "Google is some awesome stuff.",
    comments: []
  },
  {
    permalink: "http://my.blog.com/permalink2",
    title: "All About Microsoft",
    dateTime: "July 11, 2013 00:00:00 PM",
    body: "Those players be hatin.",
    comments: [
      {
        author: "Bill Gates",
        body: "You're lame."
      },
      {
        author: "Ron Burgandy",
        body: "Hey everyone, come see how good I look!"
      }
    ]
  },
  {
    permalink: "http://my.blog.com/permalink3",
    title: "All about Bacon",
    dateTime: "July 10, 2013 00:00:00 PM",
    body: "It's delicious.",
    comments: [
      {
        author: "Matt Farmer",
        body: "I'm awesome."
      }
    ]
  }
]

".post".cssSel(posts.map { |post|
  ".post-link [href]".cssSel(post[:permalink]) &
  ".title *".cssSel(post[:title]) &
  ".date-time *".cssSel(post[:dateTime]) &
  ".body *".cssSel(post[:body]) &
  ".comment-count *".cssSel(post[:comments].length)
})
