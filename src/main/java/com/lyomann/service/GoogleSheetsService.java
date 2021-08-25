package com.lyomann.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.lyomann.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleSheetsService {
    public final Sheets sheetsSDK;

    public GoogleSheetsService(Sheets sheetsSDK) {
        this.sheetsSDK = sheetsSDK;
    }

    public void createNewSheetTab(String tabName) throws IOException {
        //Create a new AddSheetRequest
        AddSheetRequest addSheetRequest = new AddSheetRequest();
        SheetProperties sheetProperties = new SheetProperties();

        //Add the tabName to the sheetProperties
        addSheetRequest.setProperties(sheetProperties);
        addSheetRequest.setProperties(sheetProperties.setTitle(tabName));

        //Create batchUpdateSpreadsheetRequest
        BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest = new BatchUpdateSpreadsheetRequest();

        //Create requestList and set it on the batchUpdateSpreadsheetRequest
        List<Request> requestsList = new ArrayList<>();
        batchUpdateSpreadsheetRequest.setRequests(requestsList);

        //Create a new request with containing the addSheetRequest and add it to the requestList
        Request request = new Request();
        request.setAddSheet(addSheetRequest);
        requestsList.add(request);

        //Add the requestList to the batchUpdateSpreadsheetRequest
        batchUpdateSpreadsheetRequest.setRequests(requestsList);

        //Call the sheets API to execute the batchUpdate
        sheetsSDK.spreadsheets().batchUpdate(Constants.SPREADSHEET_ID, batchUpdateSpreadsheetRequest).execute();
    }

    public void updateNewSheetTab(String tabName) throws IOException {
        //Append cells to new tab
        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
                ));
        UpdateValuesResponse result = sheetsSDK.spreadsheets().values()
                .update(Constants.SPREADSHEET_ID, tabName + "!A1", body)
                .setValueInputOption("RAW")
                .execute();

        System.out.println(body);
        System.out.println(result);
    }

}
