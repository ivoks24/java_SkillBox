import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class FileAccess {
    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     * for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) {


    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws IOException {

        Main.getHDFS().createNewFile((Path) Paths.get(path));
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException {

        Main.getHDFS().append((Path) Paths.get(path));
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {

        return null;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {

        Main.getHDFS().deleteOnExit((Path) Paths.get(path));
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException {

        return Main.getHDFS().isDirectory((Path) Paths.get(path));
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws IOException {

        return Main.getHDFS().listXAttrs((Path) Paths.get(path));
    }
}
