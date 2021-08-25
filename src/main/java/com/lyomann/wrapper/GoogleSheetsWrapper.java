package com.lyomann.wrapper;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.lyomann.util.Constants;

import java.io.IOException;

public class GoogleSheetsWrapper {

    private final Sheets sheetsSDK;

    public GoogleSheetsWrapper(Sheets sheetsSDK) {
        this.sheetsSDK = sheetsSDK;
    }

    public void updateSpreadsheetWithNewTab(BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest) {
        try {
            sheetsSDK
                    .spreadsheets()
                    .batchUpdate(Constants.SPREADSHEET_ID, batchUpdateSpreadsheetRequest)
                    .execute();
        } catch (IOException e) {
            System.out.println("Error occurred adding new tab: " + e);
        }
    }

    public UpdateValuesResponse insertDataIntoSheetTab(String tabName, ValueRange body) {
        try {
            return sheetsSDK.spreadsheets().values()
                    .update(Constants.SPREADSHEET_ID, tabName + "!A1", body)
                    .setValueInputOption("RAW")
                    .execute();
        } catch (IOException e) {
            System.out.println("Error occurred inserting data in sheet tab: " + e);
        }

        return null;
    }
}
