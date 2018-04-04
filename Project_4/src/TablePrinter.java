import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TablePrinter
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/3/18
 *
 */

public class TablePrinter {
    private int MAXLINEWIDTH = 40;
    
    private List<String[]> contents;
    private int[] lengths;
    private int[] maxwidths;
    
    private int cols;
    
    public TablePrinter(int columns) {
        this(columns, new int[columns]);
        
        Arrays.fill(maxwidths, -1);
    }
    
    public TablePrinter(int columns, int[] maxwidths) {
        cols = columns;
        contents = new ArrayList<>();
        
        lengths = new int[columns];
        
        assert maxwidths.length == columns;
        this.maxwidths = maxwidths;
    }
    
    public TablePrinter(String[] headers) {
        this(headers.length);
        
        add(headers);
    }
    
    public TablePrinter(String[] headers, int[] maxwidths) {
        this(headers.length, maxwidths);
        
        add(headers);
    }
    
    public boolean add(String[] row) {
        if(row.length != cols) return false;
        
        String[] toAdd = new String[cols];
        for(int i=0; i<cols; ++i) {
            String a = row[i];
            if(maxwidths[i] != -1 && a.length() > maxwidths[i]) {
                a = a.substring(0, maxwidths[i]-3) + "...";
            }
            if(a.length() > lengths[i]) lengths[i] = a.length();
            toAdd[i] = a;
        }
        
        contents.add(toAdd);
        return true;
    }
    
    private int getLineWidth() {
        int lw = 2;
        for(int l:lengths) {
            lw += l+3;
        }
        return lw-1;
    }
    
    private String makeTop() {
        int width = getLineWidth();
        char[] top = new char[width];
        Arrays.fill(top, '_');
        top[0] = ' ';
        top[width-1] = '\n';
        
        return String.valueOf(top);
    }
    private String makeBot() {
        int width = getLineWidth();
        char[] bot = new char[width];
        Arrays.fill(bot, '_');
        bot[0] = '|';
        int accum = 0;
        for(int l:lengths) {
            accum += l+3;
            bot[accum] = '|';
        }
        
        return String.valueOf(bot);
    }
    
    private String center(String s, int size) {
        if(s.length() > size) return null;
        if(s.length() == size) return s;
        
        int padSize = size - s.length();
        
        int rightPad = padSize/2;
        int leftPad = padSize-rightPad;
        
        char[] bla = new char[leftPad];
        Arrays.fill(bla, ' ');
        
        String ret = String.valueOf(bla) + s;
        if(rightPad == 0) {
            return ret;
        }
        
        bla = new char[rightPad];
        Arrays.fill(bla, ' ');
        ret += String.valueOf(bla);
        return ret;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(makeTop());
        
        for(String[] row : contents) {
            sb.append("| ");
            for(int i=0; i<cols; ++i) {
                sb.append(center(row[i], lengths[i]));
                sb.append(" | ");
            }
            sb.append('\n');
        }
        
        sb.append(makeBot());
        return sb.toString();
    }
    
    public static void main(String[] args) {
        int[] widths = {-1, 60};
        TablePrinter printer = new TablePrinter(2, widths);
        
        String[] l1 = {"id", "name"};
        printer.add(l1);
        
        String[] l2 = {"0", "axu"};
        printer.add(l2);
        
        String[] l3 = {"1", "lsdkjfa;alwkjdls;fkjaw;leksjfd;lkdsjf;laskdjfl;askjdfal;skdjfal;sdflkajshdlfkjas"};
        printer.add(l3);
        
        printer.add(l2);
    
        System.out.println(printer.toString());
    }
}