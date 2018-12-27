import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Nio001 {

  public static void main(String[] args) throws IOException {

    //String path = "C:\\Temp\\JavaRush_test_files";
    //String path = "/root";
    String path;

    if (args.length < 1 || args[0].length() == 0) {
      System.out.println("Error.");
      return;
    }

    System.out.println(args[0]);

    path = args[0];

//    if (true) return;

    Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
          throws IOException
      {
        System.out.println("File: " + file.getFileName().toString() + " (" + (fileExt(file.getFileName().toString())) + ")");
        return FileVisitResult.CONTINUE;
      }
      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException e)
          throws IOException
      {
        if (e == null) {
          System.out.println("Dir: " + dir);
          return FileVisitResult.CONTINUE;
        } else {
          // directory iteration failed
          throw e;
        }
      }

      @Override
      public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        FileVisitResult fvr = super.visitFileFailed(file, exc);
        System.out.println("visitFileFailed(): FileVisitResult: " + fvr);
        return fvr;
      }

    });

  }

  private static String fileExt(String fileName) {
    int dot = fileName.lastIndexOf('.');
    return fileName.substring(dot+1);
  }

}

