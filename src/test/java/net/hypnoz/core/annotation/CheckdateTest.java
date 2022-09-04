package net.hypnoz.core.annotation;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckdateTest {
    @CheckDate
    LocalDate localDate;

    @Test
   void shouldBeInvalidDateOfDateOfMonth(){
       DateTimeException exception = assertThrows(DateTimeException.class,()->{
           localDate = LocalDate.of(985,12,35);
       });

        assertEquals("Invalid value for DayOfMonth (valid values 1 - 28/31): 35",exception.getMessage());
   }
    @Test
    void shouldBeInvalidDateOfMonthYear(){
        DateTimeException exception = assertThrows(DateTimeException.class,()->{
            localDate = LocalDate.of(985,15,1);
        });

        assertEquals("Invalid value for MonthOfYear (valid values 1 - 12): 15",exception.getMessage());
    }


}
