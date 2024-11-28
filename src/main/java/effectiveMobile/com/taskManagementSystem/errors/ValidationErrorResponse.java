package effectiveMobile.com.taskManagementSystem.errors;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}