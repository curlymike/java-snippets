import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class FileSHA1 {

  public static void main(String args[]) throws Exception {

    //String datafile = "c:\\INSTLOG.TXT";
    
    if (args.length < 1 || args[0].length() == 0) {
      System.out.println("Error (1)");
      return;
    }

    
    File file = new File(args[0]);

    if (!file.exists()) {
      System.out.println("File " + args[0] + " does not exist.");
      return;
    }

    String datafile = args[0];

    MessageDigest md = MessageDigest.getInstance("SHA1");
    FileInputStream fis = new FileInputStream(datafile);
    byte[] dataBytes = new byte[1024];

    int nread = 0;

    while ((nread = fis.read(dataBytes)) != -1) {
      md.update(dataBytes, 0, nread);
    };

    byte[] mdbytes = md.digest();

    //convert the byte to hex format
    StringBuffer sb = new StringBuffer("");
    for (int i = 0; i < mdbytes.length; i++) {
    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
    }

    System.out.println("Digest(in hex format):: " + sb.toString());

  }
}
