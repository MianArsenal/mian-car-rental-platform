package com.mian.car.rental.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS("2000", "success"),

    DATE_STRING_FORMAT_ERROR("4100", "exist invalid format date string"),

    DATABASE_FAIL("5100", "database operation exception"),
    DATABASE_INSERT_FAIL("5101", "failed to insert in database"),
    DATABASE_QUERY_FAIL("5102", "failed to query in database"),
    DATABASE_QUERY_NOTHING("5103", "no records were queried out in database"),
    DATABASE_DELETE_FAIL("5104", "failed to delete in database"),
    ;

    String code;
    String codeDescription;

}
