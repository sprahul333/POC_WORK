package framework;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SQLConstants {

    public String updateQuery_ExecutionTable(String testCaseName,String executionStatus,String releaseNumber)
    {
        return """
                UPDATE [dbo].[execution_tracker]
                   SET [Execution_Status] = '%s'
                 WHERE [Test_Case_Name] = '%s'
                   AND [Release_Number] = '%s'
                   AND Execution_Status='In Progress'
                   AND Result_ID=(select MAX(Result_ID) from dbo.execution_tracker)
                """.formatted(executionStatus,testCaseName,releaseNumber);
    }

    public String insertQuery_ExecutionTable(String testCaseName,String executionStatus,String releaseNumber,String currentDateTime)
    {
        return """
                INSERT INTO execution_tracker ( Test_Case_Name, Execution_Status, Release_Number, ExecutionTimeStamp)
                VALUES ('%s','%s','%s','%s')
                
                """.formatted(testCaseName,executionStatus,releaseNumber,currentDateTime);
    }
}
