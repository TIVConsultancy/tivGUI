/*
 * Copyright 2020 TIVConsultancy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tivconsultancy.tivGUI.startup;

import com.tivconsultancy.opentiv.datamodels.SQL.PostgreSQL;
import com.tivconsultancy.opentiv.datamodels.SQL.SQLDatabase;
import com.tivconsultancy.tivGUI.StaticReferences;
import com.tivconsultancy.tivGUI.controller.subControllerSQL;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author TZ ThomasZiegenhein@TIVConsultancy.com +1 480 494 7254
 */
public class StartUpSubControllerSQL implements subControllerSQL {

    protected PostgreSQL sqlData;
    
    @Override
    public SQLDatabase getDatabase(String ident) {
        return sqlData;
    }

    @Override
    public String connect(String user, String password, String database, String host) {
        sqlData = new PostgreSQL("jdbc:postgresql://"+host+"/" + database, user, password);
        sqlData.connect();
        if( sqlData.getStatus() == null){
            StaticReferences.getlog().log(Level.SEVERE, "SQL Status: " + sqlData.getStatus(), new Throwable());
        }else{
            StaticReferences.getlog().log(Level.INFO, "SQL Status: " + sqlData.getStatus());
        }
        
        return sqlData.getStatus();
    }

    @Override
    public List<String> getColumnEntries(String schemaName, String tableName, String columnName) {
        return sqlData.getColumnEntries(schemaName, tableName, columnName);
    }

    @Override
    public void importCSVfile(String sDir) {
        //To change body of generated methods, choose Tools | Templates.
    }
}
