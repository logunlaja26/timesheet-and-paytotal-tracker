import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.lyomann.service.GoogleSheetsService;
import com.lyomann.wrapper.GoogleSheetsWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class GoogleSheetsServiceTest {

    private final GoogleSheetsWrapper mockGoogleSheets = Mockito.mock(GoogleSheetsWrapper.class);

    private final GoogleSheetsService googleSheetsService = new GoogleSheetsService(mockGoogleSheets);

    @Test
    void createNewTab_createsANewTabWithCorrectTabName() {
        String expectedTabName = "Tab 1";
        googleSheetsService.createNewTab("Tab 1");

        // TODO: Use Argument Captor of class BatchUpdateSpreadsheetRequest
        ArgumentCaptor<BatchUpdateSpreadsheetRequest> batchUpdateSheetArgumentCaptor = ArgumentCaptor.forClass(BatchUpdateSpreadsheetRequest.class);;
        // TODO: Find title inside sheet properties which is inside BatchUpdateSpreadsheetRequest
        Mockito.verify(mockGoogleSheets).updateSpreadsheetWithNewTab(any(BatchUpdateSpreadsheetRequest.class));
        BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest = batchUpdateSheetArgumentCaptor.getValue();
        Assertions.assertEquals(expectedTabName, batchUpdateSpreadsheetRequest.getRequests());
    }

    @Test
    void writeDaysOfTheWeek_writesFiveDaysOfTheWeek() {
        List<String> expectedDays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

        googleSheetsService.writeDaysOfTheWeek("Tab 1");

        ArgumentCaptor<ValueRange> valueRangeCaptor = ArgumentCaptor.forClass(ValueRange.class);

        Mockito.verify(mockGoogleSheets).insertDataIntoSheetTab(eq("Tab 1"), valueRangeCaptor.capture());
        ValueRange valueRange = valueRangeCaptor.getValue();
        Assertions.assertEquals(5, valueRange.getValues().get(0).size());
        Assertions.assertEquals(expectedDays, valueRange.getValues().get(0));
        System.out.println(valueRange.getValues().get(0));
    }
}
