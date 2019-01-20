package org.itstep.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {

    private @NonNull String errorMessage;
}
