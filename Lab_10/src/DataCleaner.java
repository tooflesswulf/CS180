/**
 * Created by Sarah Rodenbeck on 3/20/2018.
 */

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

enum HEADER {
    room_id(0),
    host_id(1),
    room_type(2),
    borough(3),
    neighborhood(4),
    reviews(5),
    overall_satisfaction(6),
    accommodates(7),
    bedrooms(8),
    price(9),
    minstay(10),
    latitude(11),
    longitude(12),
    last_modified(13);
    
    public int v;
    HEADER(int v) {
        this.v = v;
    }
    
    public int v() { return v; }
}

public class DataCleaner {
    private ArrayList<InternalRepresentation> rows = new ArrayList<InternalRepresentation>();

    public InternalRepresentation parseAndClean(String s){
        
        //TODO get rid of the last 3 data points (latitude,longitude, last modified)
        String[] cols = s.split(",");
        InternalRepresentation ir = new InternalRepresentation(
                Integer.valueOf(cols[HEADER.room_id.v]),
                Integer.valueOf(cols[HEADER.host_id.v]),
                cols[HEADER.room_type.v],
                cols[HEADER.neighborhood.v],
                Integer.valueOf(cols[HEADER.reviews.v]),
                Double.valueOf(cols[HEADER.overall_satisfaction.v]),
                Integer.valueOf(cols[HEADER.accommodates.v]),
                Double.valueOf(cols[HEADER.bedrooms.v]),
                Double.valueOf(cols[HEADER.price.v]),
                Double.valueOf(cols[HEADER.minstay.v])
        );
        
        return ir;
    }

    public void openAndRead() throws IOException {
        //TODO open file and create BufferedReader
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Lab_10/src/airbnb_christchurch_2016.csv"));
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }

        //TODO read in each line from the file; parse/clean input and add result to ArrayList
        try {
            while (reader.ready()) {
                String line=reader.readLine();
                try {
                    rows.add(parseAndClean(line));
                } catch(Exception e) {
                    continue;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    public void writeCleanData() {
        //TODO create files and PrintWriters
        PrintWriter clean_writer = null;
        PrintWriter good_writer = null;
        try {
            clean_writer = new PrintWriter("Lab_10/src/Cleaned_Airbnb_Christchurch.csv");
            good_writer = new PrintWriter("Lab_10/src/GoodPrivate_Airbnb_Christchurch.csv");
        } catch(FileNotFoundException e) {
            if(clean_writer!=null) clean_writer.close();
            if(good_writer!=null) clean_writer.close();
            
            e.printStackTrace();
            return;
        }

        //TODO write to files based on conditions specified in handout
        for(InternalRepresentation rep : rows) {
            clean_writer.println(rep.PrintRowCSV());
            if(rep.getOverall_satisfaction() >= 4.0 && rep.getReviews() >= 10 && !rep.getRoomType().equals("Shared room")) {
                good_writer.println(rep.PrintRowCSV());
            }
        }
        
        clean_writer.close();
        good_writer.close();
    }


    public static void main(String[] args) throws IOException{
        DataCleaner d = new DataCleaner();
        d.openAndRead();
        d.writeCleanData();
    }
}
