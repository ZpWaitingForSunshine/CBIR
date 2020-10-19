package njust.tools;
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HSIhdr {
    private String name;
    private String path;
    private int row;
    private int col;
    private int bands;
    private short datatype;
    private String inter;

    public HSIhdr(String filename, String path) throws IOException {
        String[] t = filename.split("\\.");
        this.name = t[0] + ".hdr";
        this.path = path;
        this.ReadInformation();
    }

    public void ReadInformation() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(this.path + this.name), conf);
        DataInputStream din = fs.open(new Path(this.path + this.name));
        BufferedReader read = new BufferedReader(new InputStreamReader(din));

        String strline;
        try {
            while((strline = read.readLine()) != null) {
                int pos = strline.indexOf(61);
                if (pos >= 0) {
                    String strleft = strline.substring(0, pos).trim();
                    String strright = strline.substring(pos + 1, strline.length()).trim();
                    if (strleft.equals("samples")) {
                        this.col = Integer.parseInt(strright);
                    } else if (strleft.equals("lines")) {
                        this.row = Integer.parseInt(strright);
                    } else if (strleft.equals("bands")) {
                        this.bands = Integer.parseInt(strright);
                    } else if (strleft.equals("data type")) {
                        this.datatype = Short.parseShort(strright);
                    } else if (strleft.equals("interleave")) {
                        this.inter = strright;
                    }
                }
            }
        } catch (FileNotFoundException var9) {
            System.out.println("failed to read");
            var9.printStackTrace();
        }

    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getBands() {
        return this.bands;
    }

    public short getDatatype() {
        return this.datatype;
    }

    public String getInter() {
        return this.inter;
    }
}
