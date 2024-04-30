package com.example.task_25_04_2024.validation.imp;

import com.example.task_25_04_2024.entity.enums.DocType;
import com.example.task_25_04_2024.validation.DocTypeValidComponent;
import org.springframework.stereotype.Component;

@Component
public class DocTypeValidComponentImp implements DocTypeValidComponent {
      @Override
      public boolean compareDocumentTypes(DocType docType1, DocType docType2) {
            return docType1 == docType2;
      }
}
