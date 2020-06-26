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
package com.tivconsultancy.tivGUI.controller;

import com.tivconsultancy.opentiv.datamodels.SQL.PostgreSQL;
import com.tivconsultancy.opentiv.datamodels.SQL.SQLDatabase;
import java.util.List;

/**
 *
 * @author Thomas Ziegenhein
 */
public interface subControllerSQL {

    public SQLDatabase getDatabase(String ident);

    public String connect(String user, String password, String database, String host);

    public List<String> getColumnEntries(String schemaName, String tableName, String columnName);

    public void importCSVfile(String sDir);
    
}
