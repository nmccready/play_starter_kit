
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "sonatype-public" at "https://oss.sonatype.org/content/groups/public"

resolvers += "repo.codahale.com" at "http://repo.codahale.com"

//for TL/SBT PLUGINS
resolvers += Resolver.url("Artifactory Online", url("http://repo.scala-sbt.org/scalasbt/repo"))(Resolver.ivyStylePatterns)

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.2")

// web plugins
addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.0.0")

//non play
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

addSbtPlugin("com.trafficland" % "sbt-plugins" % "0.13.0-20140404-180421")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.3.2")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")
