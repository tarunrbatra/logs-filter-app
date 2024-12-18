# Log Filter Program

This program filters application request log extracts by a number of different properties without using any external libraries.

## Log Extract File Format

A log extract file contains:
- **Header row**: Describes the columns in the file.
- **Data rows**: Zero or more rows of data, in comma-separated value (CSV) format.

### Columns
1. **Timestamp**: The time the request was made, in seconds.
2. **Country Code**: The country from which the request originated.
3. **Response Time**: The time, in milliseconds, which the request took to complete.

**Note**: The data rows are not sorted, grouped, or ordered.

### Example log file content
'''
    REQUEST_TIMESTAMP,COUNTRY_CODE,RESPONSE_TIME
    1433190845,US,539
    1432917066,GB,37
'''
