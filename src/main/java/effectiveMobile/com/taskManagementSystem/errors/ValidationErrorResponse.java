package effectiveMobile.com.taskManagementSystem.errors;

import java.util.List;

/**
 * Validation error response
 * @param violations List of Violations
 */
public record ValidationErrorResponse(List<Violation> violations) {
}