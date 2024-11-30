package effectiveMobile.com.taskManagementSystem.errors;

/**
 * Violation
 * @param fieldName field name
 * @param message error message
 */
public record Violation(String fieldName, String message) {
}