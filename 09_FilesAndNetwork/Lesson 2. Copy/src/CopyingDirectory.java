import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyingDirectory extends SimpleFileVisitor<Path> {

    Path originalPath;
    Path copyPath;

    public CopyingDirectory(Path originalPath, Path copyPath) {

        this.originalPath = originalPath;
        this.copyPath = copyPath;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Files.createDirectories(copyPath.resolve(originalPath.relativize(dir)));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.copy(file, copyPath.resolve(originalPath.relativize(file)));
        return FileVisitResult.CONTINUE;
    }
}
