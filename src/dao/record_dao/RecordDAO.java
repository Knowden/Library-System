package dao.record_dao;

/**
 * 21-oo
 * Created on:      2019-03-21 16:31
 * Original author: Nocturne
 */

import main.Record;

import java.util.ArrayList;

interface RecordDAO {

    public void addRecord(Record record);

    public void deleteRecord(Record record);

    public ArrayList<Record> checkOneRecords(int userId);

}
