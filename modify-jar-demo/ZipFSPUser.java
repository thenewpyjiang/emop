import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class ZipFSPUser {
    public static void main(String [] args) throws Throwable {
        Map<String, String> env = new HashMap<>(); 
        env.put("create", "true");
        // locate file system by using the syntax 
        // defined in java.net.JarURLConnection
        URI uri = URI.create("jar:file:" + System.getProperty("user.dir") + File.separator + "TicTacToe.jar");

       try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            Path externalTxtFile = Paths.get(System.getProperty("user.dir") + File.separator + "instructions.txt");
            Path pathInZipfile = zipfs.getPath("/META-INF/MANIFEST.MF");          
            // copy a file into the zip file
            Files.copy( externalTxtFile,pathInZipfile, 
                    StandardCopyOption.REPLACE_EXISTING ); 
        } 
    }
}
