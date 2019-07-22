package com.crud.framework.utilities;

import java.sql.Connection;
import java.util.Hashtable;

/**
 * Created by Ibi on 25/07/2018.
 */
public class ReportingUtil {

    public static void CreateTestCycle(Connection connection){

        Hashtable table = new Hashtable();
        table.put("AUT","Project Name");
        table.put("ExecutedBy","Ibikunle Awesu");
        table.put("RequestedBy","Ibi");
        table.put("BulidNo","1.0");
        table.put("ApplicationVersion","1.0");
        table.put("MachineName","Windows 10");
        table.put("TestType",1);

        DatabaseUtil.ExecuteStoredProc("sp_CreateTestCycleID", table, connection);
    }
}
