package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.StoreRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class StoreGQControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void getStoreWithAddressByCountry() {
        String query = """
                {
                    getStoreWithAddressByCountry(country: "Canada") {
                        id
                        address {
                            addressLine
                        }
                    }
                }
                """;
        List<StoreRecord> stores = graphQlTester.document(query)
                .execute()
                .path("data.getStoreWithAddressByCountry")
                .entityList(StoreRecord.class)
                .get();
        assertFalse(stores.isEmpty());
        Assertions.assertNotNull(stores.getFirst().id());
        Assertions.assertNotNull(stores.getFirst().address());
    }

    @Test
    void getStaffByStoreId() {
    }
}