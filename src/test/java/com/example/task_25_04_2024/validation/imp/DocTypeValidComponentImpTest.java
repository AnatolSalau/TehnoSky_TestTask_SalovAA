package com.example.task_25_04_2024.validation.imp;

import com.example.task_25_04_2024.entity.enums.DocType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocTypeValidComponentImpTest {

      private DocTypeValidComponentImp validator;

      @BeforeEach
      void initValidator() {
            validator = new DocTypeValidComponentImp();
      }

      @Test
      void testCompareDocumentTypesWhenTypesEqual() {
            DocType docType1 = DocType.PASSPORT;
            DocType docType2 = DocType.PASSPORT;

            boolean isEqual = validator.compareDocumentTypes(docType1, docType2);

            Assertions.assertTrue(isEqual);
      }

      @Test
      void testCompareDocumentTypesWhenTypesDifferent() {
            DocType docType1 = DocType.PASSPORT;
            DocType docType2 = DocType.DRIVER_LICENSE;

            boolean isEqual = validator.compareDocumentTypes(docType1, docType2);

            Assertions.assertFalse(isEqual);
      }
}