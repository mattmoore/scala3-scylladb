object Embroidery {
  import embroidery.*

  def genTitle(title: String): String =
    title.toAsciiArt(art = '#', spaces = 1)

  def getLogo: String =
    logo.asciiArt("project/images/scylladb_logo.png")
}
