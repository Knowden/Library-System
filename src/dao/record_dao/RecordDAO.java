package dao.record_dao;

/**
 * 21-oo
 * Created on:      2019-03-21 16:31
 * Original author: Nocturne
 */

import base_data.Record;

import java.util.ArrayList;

interface RecordDAO {

    public void addRecord(Record record);

    public void deleteRecord(int userId, int bookId);

    public ArrayList<Record> checkOneRecords(int userId);

}
