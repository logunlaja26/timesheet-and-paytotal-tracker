package com.lyomann.service;

import com.google.api.services.sheets.v4.model.*;
import com.lyomann.wrapper.GoogleSheetsWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoogleSheetsService {
    public final GoogleSheetsWrapper googleSheetsWrapper;

    public GoogleSheetsService(GoogleSheetsWrapper googleSheetsWrapper) {
        this.googleSheetsWrapper = googleSheetsWrapper;
    }

    public void createNewTab(String tabName) {
        AddSheetRequest addSheetRequest = createAddSheetRequest(tabName);

        BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest = createUpdateSpreadsheetRequest(addSheetRequest);

        googleSheetsWrapper.updateSpreadsheetWithNewTab(batchUpdateSpreadsheetRequest);
    }

    private AddSheetRequest createAddSheetRequest(String tabName) {
        AddSheetRequest addSheetRequest = new AddSheetRequest();
        SheetProperties sheetProperties = new SheetProperties();
        addSheetRequest.setProperties(sheetProperties);
        addSheetRequest.setProperties(sheetProperties.setTitle(tabName));
        return addSheetRequest;
    }

    private BatchUpdateSpreadsheetRequest createUpdateSpreadsheetRequest(AddSheetRequest addSheetRequest) {
        BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest = new BatchUpdateSpreadsheetRequest();
        List<Request> requestsList = new ArrayList<>();

        Request request = new Request();
        request.setAddSheet(addSheetRequest);

        requestsList.add(request);
        batchUpdateSpreadsheetRequest.setRequests(requestsList);
        return batchUpdateSpreadsheetRequest;
    }

    public void writeDaysOfTheWeek(String tabName) {
        ValueRange body = new ValueRange()
                .setValues(Collections.singletonList(
                        Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
                ));

        UpdateValuesResponse result = googleSheetsWrapper.insertDataIntoSheetTab(tabName, body);

        System.out.println(body);
        System.out.println(result);
    }
}
