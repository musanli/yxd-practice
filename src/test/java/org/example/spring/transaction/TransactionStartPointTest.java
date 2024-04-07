package org.example.spring.transaction;

import org.example.ExampleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest(classes = ExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionStartPointTest {

    @Autowired
    TransactionStartPoint transactionStartPoint;

    @Test
    public void addRow() throws ExecutionException, InterruptedException {
        transactionStartPoint.getJdbcThreadIdAsNewThread();
        transactionStartPoint.addRow();

    }
}