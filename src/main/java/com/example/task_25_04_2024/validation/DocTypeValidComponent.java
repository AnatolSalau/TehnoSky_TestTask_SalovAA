package com.example.task_25_04_2024.validation;

import com.example.task_25_04_2024.entity.enums.DocType;

public interface DocTypeValidComponent {
      boolean compareDocumentTypes(DocType docType1, DocType docType2);

}
