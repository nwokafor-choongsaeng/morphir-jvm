package morphir

import morphir.ir.{ Name, Path }
import morphir.ir.Name.Name
import morphir.ir.Path.Path

object Main {
  def main(args: Array[String]): Unit = {
    val nameStr    = "openSourceLCR"
    val name: Name = Name.fromString(nameStr)
    println(name)

    val pathStr    = "fooBar.Baz"
    val path: Path = Path.fromString(pathStr)
    println(path)

    val pathStr2    = "foo bar/baz"
    val path2: Path = Path.fromString(pathStr2)
    println(path2)
  }
}
