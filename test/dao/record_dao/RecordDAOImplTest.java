package dao.record_dao;

import base_data.Date;
import base_data.Record;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RecordDAOImplTest {

    private static RecordDAOImpl impl = new RecordDAOImpl();

    @Test
    public void addRecord() {
        impl.addRecord(new Record(1,1, new Date("2019-3-22")));
        impl.addRecord(new Record(1,1, new Date("2019-4-1")));
        impl.addRecord(new Record(1,2, new Date("2019-3-22")));
        impl.addRecord(new Record(-1,2, new Date("2019-3-22")));
        impl.addRecord(new Record(-1,-1, new Date("2019-3-22")));
        impl.addRecord(new Record(2,1, new Date("2019-3-22")));
    }

    @Test
    public void deleteRecord() {
        impl.addRecord(new Record(1,1, new Date("2019-3-22")));
        impl.addRecord(new Record(1,1, new Date("2019-4-1")));
        //impl.deleteRecord(new Record(1,1, new Date("2019-3-22")));
        //impl.deleteRecord(new Record(1,1, new Date("2019-4-1")));
        //impl.deleteRecord(new Record(3,1, new Date("2019-3-22")));
    }

    @Test
    public void checkOneRecords() {
        impl.addRecord(new Record(17231122,1, new Date("2019-3-22")));
        impl.addRecord(new Record(17231122,1, new Date("2019-4-1")));
        ArrayList<Record> records = impl.checkOneRecords(17231122);
        for (Record record : records) {
            System.out.println(record);
            System.out.println("----------------------");
        }
    }
}